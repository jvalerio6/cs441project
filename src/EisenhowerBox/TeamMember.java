/*
 * Software Engineering project Spring Semester 2016
 * Instructor Sukendeep Samra
 * Group Project EisenhowerBox Team members: Samir Asfirane, Erik Kalan,
 * Team members: Erik Kalan, Javier Valerio, Nelson Debate,  Ricky Lee,
 * Samir Asfirane, Yu (Will) Tian
 */

/**
 * Class TeamMember defines the TeamMember project
 * it's attributes and methods
 * @author Javier Valerio
 */
package EisenhowerBox;

import java.util.List;

public class TeamMember extends User {

	private List<Project> project_list;
	private List<Task> task_list;

	// Default Constructor with no argument
    public TeamMember() {
    	super();
    }
    // Constructor with only a name for argument
    public TeamMember(String name) {
    	super(name);
	}

    // Constructor with name and password
	public TeamMember(String name, String password) {
		super(name, password);
	}

	// Constructor with name and password and ID
	public TeamMember(String name, String password, int ID) {
		super(name, password, ID);
	}

	boolean isWorkingOnProject(Project project) {
		return (this.project_list.indexOf(project)) >= 0 ? true : false;
	}

	boolean isAssignedTask(Task task) {
		return (this.task_list.indexOf(task)) >= 0 ? true : false;
	}

	boolean hasCompletedTask(Task task) {
		return ( (this.task_list.indexOf(task) >= 0) && (task.isComplete()== true) ) ? true : false;
	}

}