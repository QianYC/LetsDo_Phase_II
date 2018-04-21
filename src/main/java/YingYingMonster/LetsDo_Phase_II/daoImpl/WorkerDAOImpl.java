package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;
import YingYingMonster.LetsDo_Phase_II.dao.WorkerDAO;
import YingYingMonster.LetsDo_Phase_II.model.Data;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.model.Tag;

@Component
public class WorkerDAOImpl implements WorkerDAO {

	@Autowired
	String root;
	
	@Autowired
	MockDB db;
	
	@Autowired
	SerializeHandler serialize;
	
	@Override
	public List<Project> viewWorkerProject(String workerId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<Project>list=new ArrayList<>();
		File wkDir=new File(root+"/workers/"+workerId);
		if(!wkDir.exists())
			return null;
		
		Iterator<String>keys=Stream.of(wkDir.list())
				.map(x->x.substring(0, x.lastIndexOf("_"))).iterator();
		
		while(keys.hasNext())
			list.add((Project) db.retrieve("projects", keys.next()));
			
		return list;
	}

	@Override
	public boolean fork(String workerId, String publisherId, String projectId) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		File folder=new File(root+"/workers/"+workerId);
		if(!folder.exists()){
			System.out.println("not exist");
			return false;
		}
		
		Iterator<String>it=Stream.of(folder.list())
				.filter(x->x.substring(0,x.lastIndexOf("_")).equals(publisherId+"_"+projectId))
				.iterator();
		
		if(it.hasNext()){
			System.out.println("duplicate");
			return false;//不能重复fork
		}
		
		File file=new File(root+"/dataset/"+publisherId+"_"+projectId);
		Project pj=(Project) db.retrieve("projects", publisherId+"_"+projectId);
		if(pj==null||!file.exists()||pj.getCurrWorkerNum()==pj.getMaxWorkerNum()){
			System.out.println("no obj or no dirs");
			System.out.println(pj==null);
			System.out.println(!file.exists());
			return false;//找不到project对象,文件遗失,人数已满
		}
		
		//分包
		int[]pkgs=pj.getPkgs();
		int ptr=0;
		while(ptr<pkgs.length&&pkgs[ptr]==0)
			ptr++;//找到人数未满的包
		
		if(ptr>=pkgs.length){
			System.out.println("full");
			return false;//人数已满，fork失败
		}
		
		pkgs[ptr]--;
		pj.setPkgs(pkgs);
		pj.setCurrWorkerNum(pj.getCurrWorkerNum()+1);
		db.modify("projects", pj);
				
		//在workers下建立文件夹
		File pjFolder=new File(root+"/workers/"+workerId+"/"+publisherId+"_"+projectId+"_"+(ptr+1));
		pjFolder.mkdirs();
		return true;
	}

	@Override
	public boolean uploadTag(String workerId,String publisherId, String projectId, String tagId, Tag tag) 
			throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		File folder=new File(root+"/workers/"+workerId);
		if(!folder.exists())
			return false;
		
		Iterator<String>it=Stream.of(folder.list())
				.filter(x->x.substring(0,x.lastIndexOf("_")).equals(publisherId+"_"+projectId))
				.iterator();
		
		if(!it.hasNext())
			return false;
		
		String pk=it.next();
		
		serialize.writeObj(root+"/workers/"+workerId+"/"+pk+"/"+tagId+".tag", tag);
		return true;
	}

	@Override
	public Tag downloadTag(String workerId,String publisherId, String projectId, String tagId) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		File folder=new File(root+"/workers/"+workerId);
		if(!folder.exists())
			return null;
		
		Iterator<String>it=Stream.of(folder.list())
				.filter(x->x.substring(0,x.lastIndexOf("_")).equals(publisherId+"_"+projectId))
				.iterator();
		
		if(!it.hasNext())
			return null;
		
		String pk=it.next();
		
		Tag tag=(Tag) serialize.readObj(root+"/workers/"+workerId+"/"+pk+"/"+tagId+".tag");
		return tag;
	}

	@Override
	public List<String> viewUndoData(String workerId,String publisherId, String projectId) {
		// TODO Auto-generated method stub
		String pkgId=pkgId(workerId, publisherId, projectId);
//		System.out.println(pkgId);
		
		File pj=new File(root+"/dataSet/"+publisherId+"_"+projectId+"/"+pkgId);
		List<String>list=Stream.of(pj.list()).map(x->x.split("\\.")[0]).collect(Collectors.toList());
		list.removeAll(viewDoneData(workerId,publisherId,projectId));
		return list;
	}

	@Override
	public List<String> viewDoneData(String workerId,String publisherId, String projectId) {
		// TODO Auto-generated method stub
		File wkfd=new File(root+"/workers/"+workerId);
		Iterator<String>it=Stream.of(wkfd.list())
				.filter(x->x.substring(0,x.lastIndexOf("_")).equals(publisherId+"_"+projectId))
				.iterator();
		if(!it.hasNext())
			return null;
		File pj=new File(wkfd.getPath()+"/"+it.next());
//		System.out.println(pj.getPath());
		return Stream.of(pj.list()).map(x->x.split("\\.")[0]).collect(Collectors.toList());
	}

	@Override
	public Data getAData(String workerId,String publisherId, String projectId, String dataId) throws IOException {
		// TODO Auto-generated method stub
		String pkgId=pkgId(workerId, publisherId, projectId);
		Data data=new Data();
		
		//获得图片尺寸
		BufferedImage bi=ImageIO.read(
				new File(root+"/dataSet/"+publisherId+"_"+projectId+"/"+pkgId));
		data.setWidth(bi.getWidth());
		data.setHeight(bi.getHeight());
		
		//获得byte数组
		FileImageInputStream fiis=new FileImageInputStream(
				new File(root+"/dataSet/"+publisherId+"_"+projectId+"/"+pkgId));
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		int btnum=0;
		byte[]buf=new byte[1024];
		while((btnum=fiis.read(buf))!=-1)
			baos.write(buf, 0, btnum);
		data.setData(baos.toByteArray());
		fiis.close();
		baos.close();
		
		return data;
	}

	/**
	 * 获得worker分得的包名
	 * @param wkId
	 * @param pubId
	 * @param pjId
	 * @return
	 */
	private String pkgId(String wkId,String pubId,String pjId){
		File wkfd=new File(root+"/workers/"+wkId);
		Iterator<String>it=Stream.of(wkfd.list())
				.filter(x->x.substring(0,x.lastIndexOf("_")).equals(pubId+"_"+pjId))
				.iterator();
		if(!it.hasNext())
			return null;
		String pkgId=it.next();
		pkgId="pac"+pkgId.substring(pkgId.lastIndexOf("_")+1);
		return pkgId;
	}
	
	@Override
	public int push(String workerId, String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		File wkfd=new File(root+"/workers/"+workerId);
		Iterator<String>it=Stream.of(wkfd.list())
				.filter(x->x.substring(0,x.lastIndexOf("_")).equals(publisherId+"_"+projectId))
				.iterator();
		if(!it.hasNext())
			return -1;
		
		int count=0;
		
		Project pj=(Project) db.retrieve("projects", publisherId+"_"+projectId);
		
		File pkg=new File(root+"/workers/"+workerId+"/"+it.next());
		
		String[]tags=pkg.list();
		for(String str:tags){
			Tag tag=(Tag)serialize.readObj(pkg.getPath()+"/"+str);
			//比较tag是否符合要求，若符合要求，才push
		}

		
		return count;
	}

}
