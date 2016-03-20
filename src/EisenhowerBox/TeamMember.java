package EisenhowerBox;

public class TeamMember extends User {

	public TeamMember(String member_first, String member_last, String member_username) {
		super(member_first, member_last, member_username);
	}

	boolean isWorkingOnProject(Project project) {
		return false;
	}

	boolean isAssignedTask(Task task) {
		return false;
	}

	boolean hasCompletedTask(Task task) {
		return false;
	}

}
