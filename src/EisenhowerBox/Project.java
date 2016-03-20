package EisenhowerBox;

/*
 * Software Engineering project Spring Semester 2016
 * Instructor Sukendeep Samra
 * Group Project EisenhowerBox Team members: Samir Asfirane, Erik Kalan,
 * Team members: Erik Kalan, Javier Valerio, Nelson Debate,  Ricky Lee,
 * Samir Asfirane, Yu (Will) Tian
 */

/**
 * Class Project defines the object project it's attributes and methods
 * @author Samir Asfirane
 */

import java.util.Date;

public class Project {

    // Class Utile defines useful types of structures used
    // in class Project and class task such as Priority and Importance
	static class Utile {

	    //define Priority as an enum type
	    public enum Importance {PRIMORDIAL, SUPERIMPORTANT, IMPORTANT, ORDINARY,
	    UNIMPORTANT, NOTINDICATED}

	    // define Importance as an enum type
	    public enum Priority {URGENT, NOTURGENT}

    }

    // define the project name and intialize it to null
    private String project_name = null;
    // define the project start date and end date
    private Date start_date, end_date ;
    //define the Project priority
    Utile.Importance importance = Utile.Importance.NOTINDICATED;

    // Constructor with no arguments
    public Project() { }

    // Constructor with one argument the name of the project
    public Project(String name) {
    	// assigns project name
        this.project_name = name;
    }

    // Constructor with three arguments, project name start date and end date
    public Project(String name, Date st_date, Date end_date){
	    // assigns project name
	    this.project_name = name;
	    // assigns project start date
	    this.start_date = st_date;
	    // assigns project end date
	    this.end_date = end_date;
    }
     // Constructor with three arguments, project name start date and end date
    public Project(String name, Date st_date, Date end_date,Utile.Importance import_ance){
	    // assigns project name
	    this.project_name = name;
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
    public String getPrjctName(){
    	return project_name;
    }

    // method getStrtDate takes no arguments and returns
    // an object of type Date representing the curent project start date
    public Date getStrtDate(){
    	return start_date;
    }

    // method getEndDate takes no arguments and returns
    // an object of type Date representing the curent project end date
    public Date getEndDate(){
    	return end_date;
    }

    // method getPrjctImportance takes no arguments and returns
    // a type Imporatnce representing the current object importance
    public Utile.Importance getPrjctImportance(){
    	return importance;
    }

    // mutators methods for all objects attributes
    // method setPrjctName takes an argument of type String
    // changes the name of the project to the argument and returns no argument
    public void setPrjctName(String name){
    	this.project_name = name;
    }

    // method setStrtDate takes a reference to an object of type date
    // changes the current start date to the argument and returns no argument
    public void setStrtDate(Date date){
    	this.start_date = date;
    }

    // method setEndDate takes a reference to an object of type date
    // changes the current end date to the argument and returns no argument
    public void setEndDate(Date date){
    	this.end_date = date;
    }

    // method getPrjctImportance takes no arguments and returns
    // a type Importance representing the current object importance
    public void setPrjctImportance(Utile.Importance import_tant){
    	this.importance = import_tant;
    }
}
