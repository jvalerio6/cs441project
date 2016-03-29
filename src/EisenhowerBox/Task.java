package EisenhowerBox;

/*
 * Software Engineering project Spring Semester 2016
 * Instructor Sukendeep Samra
 * Group Project EisenhowerBox Team members: Samir Asfirane, Erik Kalan,
 * Team members: Erik Kalan, Javier Valerio, Nelson Debate,  Ricky Lee,
 * Samir Asfirane, Yu (Will) Tian
 */

/**
 * Class Project defines the object project
 * it's attributes and methods
 * @author Samir Asfirane
 */

import java.util.Date;

public class Task {
	// define the task name and intialize it to null
	private String task_name = null;
	// define the project start date and end date
	private Date start_date = null, end_date = null ;
	//define the Project priority
	Project.Utile.Importance importance = Project.Utile.Importance.NOTINDICATED;

	// Constructor with no arguments
	public Task() { }

	// Constructor with one argument the name of the project
	public Task(String name) {
		// assigns project name
		this.task_name = name;
	}

	// Constructor with three arguments, project name start date and end date
    public Task(String name, Date st_date, Date end_date) {
	    // assigns project name
	    this.task_name = name;
	    // assigns project start date
	    this.start_date = st_date;
	    // assigns project end date
	    this.end_date = end_date;
    }

     // Constructor with three arguments, project name start date and end date
    public Task(String name, Date st_date, Date end_date,Project.Utile.Importance import_ance) {
	    // assigns project name
	    this.task_name = name;
	    // assigns project start date
	    this.start_date = st_date;
	    // assigns project end date
	    this.end_date = end_date;
	    // assigns the importance of the project
	    this.importance = import_ance;
    }

    // accessors methods for all objects attributes
    // method getPrjctName takes no arguments and returns
    // a string representing the current object name
    public String getTskName() {
    	return task_name;
    }

    // method getStrtDate takes no arguments and returns
    // an object of type Date representing the curent project start date
    public Date getTskStrtDate() {
    	return start_date;
    }

    // method getEndDate takes no arguments and returns
    // an object of type Date representing the curent project end date
    public Date getTskEndDate() {
    	return end_date;
    }

    // method getPrjctImportance takes no arguments and returns
    // a type Imporatnce representing the current object importance
    public Project.Utile.Importance getPrjctImportance() {
    	return importance;
    }

    // mutators methods for all objects attributes
    // method setPrjctName takes an argument of type String
    // changes the name of the project to the argument and returns no argument
    public void setTskName(String name) {
    	this.task_name = name;
    }

    // method setStrtDate takes a reference to an object of type date
    // changes the current start date to the argument and returns no argument
    public void setTskStrtDate(Date date) {
    	this.start_date = date;
    }

    // method setEndDate takes a reference to an object of type date
    // changes the current end date to the argument and returns no argument
    public void setTskEndDate(Date date) {
    	this.end_date = date;
    }

    // method getPrjctImportance takes no arguments and returns
    // a type Importance representing the current object importance
    public void setTskImportance(Project.Utile.Importance import_tant) {
    	this.importance = import_tant;
    }

}