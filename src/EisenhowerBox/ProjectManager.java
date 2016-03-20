package EisenhowerBox;

import java.util.Date;

public class ProjectManager extends User {
	String member_first, member_last, member_username;

	public ProjectManager(String member_first, String member_last, String member_username) {
		super(member_first, member_last, member_username);
	}

	public Project createProject() {
		return null;
	}

	public Project createProject(String project_name) {
		return null;
	}

	public Project createProject(String project_name, String priority, Date start_date, Date end_date) {
		return null;
	}

	public Date setStartDate(Date start_date) {
		return null;
	}

	public Date setEndDate(Date end_date) {
		return null;
	}

	boolean isWorkingOnProject(Project project) {
		return false;
	}

}
