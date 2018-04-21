package YingYingMonster.LetsDo_Phase_II;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;
import YingYingMonster.LetsDo_Phase_II.daoImpl.CSVHandler;

public class Initializer {

	private ApplicationContext context=SpringUtils.getApplicationContext();
	private String root=context.getBean(String.class);
	private MockDB db=context.getBean(MockDB.class);
	private CSVHandler handler=context.getBean(CSVHandler.class);
	
	public void initialize(){
		System.out.println(System.getProperty("user.dir"));
		System.out.println("initializing...");
		System.out.println("===============");
		
		File database=new File(root);
		if(!database.exists()){
			System.out.println("making dir for root : "+root);
			database.mkdirs();
		}
		
		String tableDir=root+"/tables";
		File tables=new File(tableDir);
		if(!tables.exists()){
			System.out.println("making dir for tables : "+tableDir);
			tables.mkdirs();
		}
		
		String dataSetDir=root+"/dataSet";
		File dataSet=new File(dataSetDir);
		if(!dataSet.exists()){
			System.out.println("making dir for dataSet : "+dataSetDir);
			dataSet.mkdirs();
		}
		
		String workersDir=root+"/workers";
		File workers=new File(workersDir);
		if(!workers.exists()){
			System.out.println("making dir for workers : "+workersDir);
			workers.mkdirs();
		}
		
		String publishersDir=root+"/publishers";
		File publishers=new File(publishersDir);
		if(!publishers.exists()){
			System.out.println("making dir for publishers : "+publishersDir);
			publishers.mkdirs();
		}
		
		File date=new File(root+"/date.csv");
		if(!date.exists()){
			boolean res=false;
			System.out.println("creating date.csv");
			try {
				res=date.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(res){
				List<String[]>list=new ArrayList<String[]>();
				list.add(new String[]{"publisherId","projectId","start","end","state"});
				handler.writeCSV(list, date.getPath());
			}
		}
		
		String batchDir=System.getProperty("user.dir").replaceAll("\\\\", "/")+"/createTable.yym";
		File batch=new File(batchDir);
		if(batch.exists()){
			System.out.println("running batch");
			executeBatch(batch);
		}
		
	}
	
	private void executeBatch(File batch){
		try {
			BufferedReader br=new BufferedReader(new FileReader(batch));
			String line=null;
			while((line=br.readLine())!=null){
				/*
				 * create table [tableName]
				 * 每一行语句以一个空格分割
				 */
				 if(line.startsWith("create table")){
					 String[]tokens=line.split(" ");
					 String tableName=tokens[2];
					 System.out.println("creating table : "+tableName);
					 db.createTable(tableName);
				 }
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
