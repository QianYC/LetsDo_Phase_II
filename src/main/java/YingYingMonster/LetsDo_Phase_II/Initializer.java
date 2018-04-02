package YingYingMonster.LetsDo_Phase_II;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.context.ApplicationContext;

import YingYingMonster.LetsDo_Phase_II.dao.MockDB;

public class Initializer {

	private ApplicationContext context=SpringUtils.getApplicationContext();
	private String root=context.getBean(String.class);
	private MockDB db=context.getBean(MockDB.class);
	
	public void initialize(){
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
		
		String batchDir=root+"/createTable.yym";
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
				 * create table [tableName] [attribute1] [attribute2]...
				 * 每一行语句以空格分割
				 */
				 if(line.startsWith("create table")){
					 String[]tokens=line.split(" ");
					 String tableName=tokens[2];
					 String[]attributes=new String[tokens.length-3];
					 System.arraycopy(tokens, 3, attributes, 0, tokens.length-3);
					 boolean res=db.createTable(tableName, attributes);
					 System.out.println("creating table "+tableName+" "+res);
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
