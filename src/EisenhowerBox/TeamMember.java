package EisenhowerBox;

import java.util.List;

public class TeamMember extends User {

	private List<Project> project_list;
	private List<Task> task_list;

	public TeamMember(String member_first, String member_last, String member_username) {
		super(member_first, member_last, member_username);
	}

	boolean isWorkingOnProject(Project project) {
		return (this.project_list.indexOf(project)) >= 0 ? true : false;
	}

	boolean isAssignedTask(Task task) {
		return (this.task_list.indexOf(task)) >= 0 ? true : false;
	}

	boolean hasCompletedTask(Task task) {
		return ( (this.task_list.indexOf(task) >= 0) && (task.getTskStatus() == "Completed") ) ? true : false;
	}

}
