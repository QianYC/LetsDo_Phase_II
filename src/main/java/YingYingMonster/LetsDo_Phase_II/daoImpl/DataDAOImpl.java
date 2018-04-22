package YingYingMonster.LetsDo_Phase_II.daoImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import YingYingMonster.LetsDo_Phase_II.dao.DataDAO;
import YingYingMonster.LetsDo_Phase_II.dao.ProjectDAO;

@Component
public class DataDAOImpl implements DataDAO{
	
	@Autowired
	String ROOT;
	@Autowired
	ProjectDAO projectDAOImpl;
	@Autowired
	CSVHandler handler;
	
	/*
	 * 复制文件
	 */
//	private void copyFile(File fromFile,File toFile) throws IOException{
//        FileInputStream ins = new FileInputStream(fromFile);
//        FileOutputStream out = new FileOutputStream(toFile);
//        byte[] b = new byte[1024];
//        int n=0;
//        while((n=ins.read(b))!=-1){
//            out.write(b, 0, n);
//        }
//        
//        ins.close();
//        out.close();
//    }

	@Override
	public int uploadDataSet(String publisherId, String dataSetId, int packNum,byte[] dataSet) throws IOException {
		// TODO 自动生成的方法存根
        if(packNum<=0)
        	return -1;
        
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File dataSetDes=null;
		File dir=new File(ROOT+"/dataSet/"+publisherId+"_"+dataSetId);
		if(!dir.exists()&&!dir.isDirectory())
			dir.mkdirs();
		else{
			return -1;
		}
		dataSetDes=new File(dir.getPath()+"/"+dataSetId+".zip");
		try {
			fos=new FileOutputStream(dataSetDes);
			bos=new BufferedOutputStream(fos);
			bos.write(dataSet);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally{
				try {
					if(bos!=null)
						bos.close();

					if(fos!=null)
						fos.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
		
		//解压
		int picNum=0;
		String outPath=dir.getPath()+"/";
		ZipFile zipFile = new ZipFile(dataSetDes,"GBK");//压缩文件的实列,并设置编码  
        //获取压缩文中的所有项  
        for(Enumeration<ZipEntry> enumeration = zipFile.getEntries();enumeration.hasMoreElements();){  
            ZipEntry zipEntry = enumeration.nextElement();//获取元素  
            //排除空文件夹  
            if(!zipEntry.getName().endsWith(File.separator)){  
                System.out.println("正在解压文件:"+zipEntry.getName());//打印输出信息  
            	picNum++;
                OutputStream os = new FileOutputStream(new File(outPath+zipEntry.getName()));//创建解压后的文件  
                BufferedOutputStream bos1 = new BufferedOutputStream(os);//带缓的写出流  
                InputStream is = zipFile.getInputStream(zipEntry);//读取元素  
                BufferedInputStream bis = new BufferedInputStream(is);//读取流的缓存流  
                CheckedInputStream cos = new CheckedInputStream(bis, new CRC32());//检查读取流，采用CRC32算法，保证文件的一致性  
                byte [] b = new byte[1024];//字节数组，每次读取1024个字节  
                //循环读取压缩文件的值  
                while(cos.read(b)!=-1)  
                {  
                    bos1.write(b);//写入到新文件  
                }  
                cos.close();  
                bis.close();  
                is.close();  
                bos1.close();  
                os.close();  
            }  
            else{  
                //如果为空文件夹，则创建该文件夹  
                new File(outPath+zipEntry.getName()).mkdirs();  
            }  
        }  
        zipFile.close();  
        
        //分包
        if(picNum==0)
        	return 0;
        
        int index=0;
        File[] files=new File(outPath).listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File arg0, String arg1) {
				// TODO 自动生成的方法存根
				return arg1.endsWith(".jpg")||arg1.endsWith(".png")||arg1.endsWith(".txt")||arg1.endsWith(".JPG")||arg1.endsWith(".PNG");
			}	
        });
        
        for(int i=1;i<=packNum;i++){
        	File pac=new File(ROOT+"/dataSet/"+publisherId+"_"+dataSetId+"/pac"+i);
        	pac.mkdirs();
        	for(int j=0;j<Math.ceil(picNum/packNum);j++){
        		File f=new File(pac.getPath()+"/"+files[index].getName());
        		files[index].renameTo(f);
        		index++;
        		if(index==files.length)
        			break;
        	}
        }
        
        //建立publisher相关文件夹
        File publisherPushEvents=new File(ROOT+"/publishers/"+publisherId+"/"+dataSetId+"/pushEvents.csv");
        if(!publisherPushEvents.exists()){
        	publisherPushEvents.getParentFile().mkdirs();
        	publisherPushEvents.createNewFile();
        	handler.appendCSV(new String[]{"workerId","date","commitId"}, 
        			publisherPushEvents.getPath());
        }
        File publisherTags=new File(ROOT+"/publishers/"+publisherId+"/"+dataSetId+"/tags");
        if(!publisherTags.exists())
        	publisherTags.mkdirs();
        File publisherFork=new File(ROOT+"/publishers/"+publisherId+"/"+dataSetId+"/fork.csv");
        if(!publisherFork.exists()){
        	publisherFork.getParentFile().mkdirs();
        	publisherFork.createNewFile();
        	handler.appendCSV(new String[]{"workerId"}, publisherFork.getPath());
        }
		return picNum;
	}

	@Override
	public byte[] downloadTags(String publisherId, String dataSetId) {
		// TODO 自动生成的方法存根
		File tagsSrc=new File(ROOT+"/publishers/"+publisherId+"/"+dataSetId+"/tags");
		File tagsDes=new File(ROOT+"/publishers/"+publisherId+"/"+dataSetId+"/tags.zip");
		if(!tagsSrc.exists())
			return null;
		Project prj = new Project();
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        if(tagsSrc.isDirectory()) { //是目录
            fileSet.setDir(tagsSrc);
            fileSet.setIncludes("*.tag"); //包括tag文件
        } else {
            fileSet.setFile(tagsSrc);
        }
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(tagsDes);
        zip.setEncoding("gbk"); //以gbk编码进行压缩
        zip.addFileset(fileSet);
        zip.execute();
        
        //转换成byte数组
        ByteArrayOutputStream bos=null;  
        BufferedInputStream in=null;  
        try{   
            bos=new ByteArrayOutputStream((int)tagsDes.length());  
            in=new BufferedInputStream(new FileInputStream(tagsDes));  
            int buf_size=1024;  
            byte[] buffer=new byte[buf_size];  
            int len=0;  
            while(-1 != (len=in.read(buffer,0,buf_size))){  
                bos.write(buffer,0,len);  
            }  
            return bos.toByteArray();
        }
        catch(Exception e){  
            e.printStackTrace();  
            return null;  
        }  
        finally{  
            try{  
                if(in!=null){  
                    in.close();  
                }  
                if(bos!=null){  
                    bos.close();  
                }  
            }  
            catch(Exception e){   
                e.printStackTrace();    
            }  
        }
	}

	@Override
	public double viewProjectProgress(String publisherId, String projectId) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO 自动生成的方法存根
		File tagsSrc=new File(ROOT+"/publishers/"+publisherId+"/"+projectId+"/tags");
		if(!tagsSrc.exists())
			return -1;
		File[] fileList=tagsSrc.listFiles();
		ArrayList<String> nameList=new ArrayList<String>();
		for(File f:fileList){
			String s=f.getName().split("_")[1];
			if(nameList.isEmpty())
				nameList.add(s);
			else if(!nameList.contains(s))
				nameList.add(s);
		}
		//计算progress
		List<YingYingMonster.LetsDo_Phase_II.model.Project> projects=projectDAOImpl.publisherViewProjects(publisherId);
        int picNum=0;
        for(YingYingMonster.LetsDo_Phase_II.model.Project p:projects){
        	if(p.getProjectId().equals(projectId)){
        		picNum=p.getPackageNum();
        		break;
        	}
        }
        if(picNum==0)
        	return 0;
		return nameList.size()/picNum*1.0;
	}

	@Override
	public List<String[]> viewPushEvents(String publisherId, String projectId) {
		// TODO 自动生成的方法存根
		try {
			return handler.readCSV(ROOT+"/publishers/"+publisherId+"/"+projectId+"/pushEvents.csv");
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> viewWorkers(String publisherId, String projectId) {
		// TODO 自动生成的方法存根
		try {
			List<String[]> list=handler.readCSV(ROOT+"/publishers/"+publisherId+"/"+projectId+"/fork.csv");
			List<String> re=new ArrayList<>();
			for(String[] str:list){
				re.add(str[0]);
			}
			return re;
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String[]> readProjectsDate() {
		// TODO 自动生成的方法存根
		try {
			return handler.readCSV(ROOT+"/data.csv");
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean modifyDateStatus(List<String[]> Projects) {
		// TODO 自动生成的方法存根
		return handler.writeCSV(Projects, ROOT+"/data.csv");
	}
	
}
