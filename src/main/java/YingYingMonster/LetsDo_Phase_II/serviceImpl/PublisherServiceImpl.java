package YingYingMonster.LetsDo_Phase_II.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import YingYingMonster.LetsDo_Phase_II.model.Project;
import YingYingMonster.LetsDo_Phase_II.service.PublisherService;

@Component
public class PublisherServiceImpl implements PublisherService {

	@Override
	public boolean creatProject(Project project, MultipartFile dataSet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateProject(String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Project> searchProjects(String publisherId, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> viewPushEvents(String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] downloadTags(String publisherId, String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recharge(int money) {
		// TODO Auto-generated method stub
		return false;
	}

}
