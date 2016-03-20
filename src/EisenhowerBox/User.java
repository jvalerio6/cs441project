package EisenhowerBox;

public abstract class User {
	private String first_name, last_name, username;

	public User(String member_first, String member_last, String member_username) {
		first_name = member_first;
		last_name = member_last;
		username = member_username;
	}

	abstract boolean isWorkingOnProject(Project project);

}
