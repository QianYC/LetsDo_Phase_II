package YingYingMonster.LetsDo_Phase_II.model;

public class Project implements Persistent{

	private String publisherId,projectId;//发布者id，项目id
	
	private int maxWorkerNum,currWorkerNum,packageNum;//允许最大参加人数，当前人数,分包数
	
	private String startDate,endDate;//yyyy-MM-dd
	
	private String tagRequirement,workerRequirement;
	
	private int money;//任务赏金
	

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
	
}
