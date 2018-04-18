package YingYingMonster.LetsDo_Phase_II.model;

public class Project implements Persistent{

	private String publisherId,projectId;//发布者id，项目id
	
	private int maxWorkerNum,currWorkerNum,packageNum,picNum;//允许最大参加人数，当前人数，分包数，图片数
	
	private int[]pkgs;//记录每个包参加人数，用于fork
	
	private String startDate,endDate;//yyyy-MM-dd
	
	private String tagRequirement,workerRequirement;
	
	private int money;//任务赏金

	public Project(){}

	public Project(String publisherId, String projectId, int maxWorkerNum, int packageNum,
				   int picNum, String startDate, String endDate,
				   String tagRequirement, String workerRequirement, int money) {
		this.publisherId = publisherId;
		this.projectId = projectId;
		this.maxWorkerNum = maxWorkerNum;
		this.packageNum = packageNum;
		this.picNum = picNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tagRequirement = tagRequirement;
		this.workerRequirement = workerRequirement;
		this.money = money;
		
		setPkgs();
	}
	
	private void setPkgs(){
		pkgs=new int[packageNum];
		int wkPpkg=maxWorkerNum/packageNum;
		for(int i=0;i<pkgs.length-1;i++){
			pkgs[i]=wkPpkg;
		}
		pkgs[pkgs.length-1]=maxWorkerNum-wkPpkg*(pkgs.length-1);
	}

	public int[] getPkgs() {
		return pkgs;
	}

	public void setPkgs(int[] pkgs) {
		this.pkgs = pkgs;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public int getMaxWorkerNum() {
		return maxWorkerNum;
	}

	public void setMaxWorkerNum(int maxWorkerNum) {
		this.maxWorkerNum = maxWorkerNum;
	}

	public int getCurrWorkerNum() {
		return currWorkerNum;
	}

	public void setCurrWorkerNum(int currWorkerNum) {
		this.currWorkerNum = currWorkerNum;
	}

	public int getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(int packageNum) {
		this.packageNum = packageNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTagRequirement() {
		return tagRequirement;
	}

	public void setTagRequirement(String tagRequirement) {
		this.tagRequirement = tagRequirement;
	}

	public String getWorkerRequirement() {
		return workerRequirement;
	}

	public void setWorkerRequirement(String workerRequirement) {
		this.workerRequirement = workerRequirement;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return publisherId+"_"+projectId;
	}
	
	public void setPicNum(int picNum){
		this.picNum=picNum;
	}
	
	public int getPicNum(){
		return picNum;
	}
}
