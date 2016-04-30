package EisenhowerBox;

import java.util.Date;
import java.util.List;

public class ProjectManager extends User {

	private List<Project> project_list;
	private Project project;

	/** Constructor with a string for name, a string for password and
	* an integer for ID
	* @param name
	* @param password
	* @param ID
	*/
	public ProjectManager(String name, String password, int ID) {
		super(name, password, ID);
	}

	public Project createProject() {
		project = new Project();

		return project;
	}

	public Project createProject(String project_name) {
		project = new Project(project_name);

		return project;
	}

	public Project createProject(String project_name,  Date start_date, Date end_date, Project.Utile.Importance importance) {
		project = new Project(project_name, start_date, end_date, importance);

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

    // method setName() takes an argument of type string representing the
    // name of the manager and changes the name of the current manager
    // to its argument and returns no argument
    void setName(String name) {
    	this.name = name;
    }

    // method getName() returns a string reprsenting the name of the
    // manager and takes no argument
    String getName() {
    	return this.name;
    }

    /** method setPassword() takes an argument of type string representing the
    password of the manager and changes the password of the current manager
    to its argument and returns no argument */
    void setPassword(String password) {
    	this.password = password;
    }

    // method getPassword() returns a string reprsenting the password of the
    // manager and takes no argument
    String getPassword() {
    	return this.password;
    }

    // method setID() takes an argument of type integer representing the
    // ID of the manager and changes the ID of the current manager
    // to its argument and returns no argument
    void setID(int ID) {
    	this.ID = ID;
    }

    // method getID() returns an integer reprsenting the ID of the
    // current manager and takes no argument
    int getID() {
    	return this.ID;
    }

}
