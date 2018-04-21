package YingYingMonster.LetsDo_Phase_II;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;
import YingYingMonster.LetsDo_Phase_II.dao.ProjectDAO;
import YingYingMonster.LetsDo_Phase_II.daoImpl.CSVHandler;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.Worker;

@Component
public class Jobs {
	
	@Autowired
	CSVHandler handler;
	@Autowired
	ProjectDAO projectDAO;
	@Autowired
	MockDB db;
	@Autowired
	String ROOT;
	
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	
	@Scheduled(cron="10 0 0 * * ?")
	//每日0点0分10秒时更新
	public void cronJob() throws ParseException, ClassNotFoundException, IOException{
		Calendar now=Calendar.getInstance();
		List<String[]> pros=handler.readCSV(ROOT+"/date.csv");
		List<String[]> newpros=new ArrayList<String[]>();
		for(String[] str:pros){
			if(format.parse(str[2]).after(now.getTime())){
				str[4]="false";
				newpros.add(str);
				continue;
			}
			if(format.parse(str[2]).before(now.getTime())&&format.parse(str[3]).after(now.getTime())){
				str[4]="true";
				newpros.add(str);
				continue;
			}
			if(format.parse(str[3]).before(now.getTime())){
				str[4]="false";
				Calendar yearago=Calendar.getInstance();
				yearago.set(now.get(Calendar.YEAR)-1, now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
				Calendar dayago=Calendar.getInstance();
				dayago.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)-1);
				if(format.parse(str[3]).before(yearago.getTime()))
					continue;
				else if(format.parse(str[3]).after(yearago.getTime())&&format.parse(str[3]).before(dayago.getTime())){
					newpros.add(str);
					continue;
				}
				else if(format.parse(str[3]).equals(format.format(dayago.getTime()))){
					//结算设定
					Project project=projectDAO.getAProject(str[0], str[1]);
					List<String[]> workers=handler.readCSV(ROOT+"/publishers/"+str[0]+"/"+str[1]+"/fork.csv");
					
					//更新worker
					for(String[] worker:workers){
						Worker wk=(Worker) db.retrieve("users", worker[0]);
						File[] tags=new File(ROOT+"/workers/"+worker[0]+"/"+str[0]+"_"+str[1]).listFiles();
						//经验值增加
						int exp=tags.length;
						if(exp+wk.getExp()>=wk.getGap()){
							wk.setLevel(wk.getLevel()+1);
							wk.setExp((exp+wk.getExp())%wk.getGap());
						}
						else{
							wk.setExp(wk.getExp()+exp);
						}
						//获得赏金
						wk.setMoney(wk.getMoney()+project.getMoney()/(project.getCurrWorkerNum()));
						
						db.modify("users", wk);
					}
					
					newpros.add(str);
					continue;
				}
				else
					continue;
			}
		}
		handler.writeCSV(newpros, ROOT+"/date.csv");
	}
	
}
