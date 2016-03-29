package EisenhowerBox;

import java.util.Date;
import java.util.List;

public class ProjectManager extends User {

	private List<Project> project_list;
	private Project project;

	public ProjectManager(String member_first, String member_last, String member_username) {
		super(member_first, member_last, member_username);
	}

	public Project createProject() {
		project = new Project();

		return project;
	}

	public Project createProject(String project_name) {
		project = new Project(project_name);

		return project;
	}

	public Project createProject(String project_name, String importance, Date start_date, Date end_date) {
		project = new Project(project_name, start_date, end_date, Project.Utile.Importance.valueOf(importance) );

		return project;
	}

	public void setStartDate(Date start_date) {
		project.setStrtDate(start_date);
	}

	public void setEndDate(Date end_date) {
		project.setStrtDate(end_date);
	}

	boolean isWorkingOnProject(Project project) {
		return (this.project_list.indexOf(project)) >= 0 ? true : false;
	}

}
