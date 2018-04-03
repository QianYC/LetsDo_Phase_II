package YingYingMonster.LetsDo_Phase_II.daoImpl;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import YingYingMonster.LetsDo_Phase_II.dao.DataDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataDAOImpl {
	
	@Autowired
	DataDAO dataDAOImpl;
	
//	@Test
//	public void testUploadDataSet(){
//		byte[] dataSet=null;
//		ByteArrayOutputStream bos=null;  
//		BufferedInputStream in=null;  
//		File des=new File("C:/Users/TF/Desktop/文件/中文test.zip");
//		try{   
//			bos=new ByteArrayOutputStream((int)des.length());  
//			in=new BufferedInputStream(new FileInputStream(des));  
//			int buf_size=1024;  
//			byte[] buffer=new byte[buf_size];  
//			int len=0;  
//			while(-1 != (len=in.read(buffer,0,buf_size))){  
//				bos.write(buffer,0,len);  
//			}  
//			dataSet=bos.toByteArray();
//			assertEquals(true,dataDAOImpl.uploadDataSet("11111", "中文test", dataSet));
//		}
//		catch(Exception e){  
//			e.printStackTrace();   
//		}  
//		finally{  
//			try{  
//				if(in!=null){  
//					in.close();  
//				}  
//				if(bos!=null){  
//					bos.close();  
//				}  
//			}  
//			catch(Exception e){   
//				e.printStackTrace();    
//			}  
//		}
//	}
	
//	@Test
//	public void testDownLoadTags(){
//		assertEquals(null,dataDAOImpl.downloadTags("11111", "中文test"));
//	}
	
//	@Test
//	public void testViewProgress(){
//		assertEquals(0.5,dataDAOImpl.viewProjectProgress("11111", "中文test"),0.01);
//	}
}
