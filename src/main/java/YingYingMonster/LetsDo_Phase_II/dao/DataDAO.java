package YingYingMonster.LetsDo_Phase_II.dao;

public interface DataDAO {

	/**
	 * 上传数据集
	 * @param publiserId
	 * @param dataSetId
	 * @param dataSet
	 * @return
	 */
	public boolean uploadDataSet(String publiserId,String dataSetId,byte[]dataSet);
	
	/**
	 * 上传者下载工人的作业
	 * @param publisherId
	 * @param dataSetId
	 * @return
	 */
	public byte[] downloadTags(String publisherId,String dataSetId);
	
	/**
	 * 上传者查看项目进度
	 * 感觉项目进度应该是动态获取的，因此我把这个属性从Project对象里删去了
	 * @param publisherId
	 * @param projectId
	 * @return
	 */
	public double viewProjectProgress(String publisherId,String projectId);
}
