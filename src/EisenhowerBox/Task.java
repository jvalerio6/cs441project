 package EisenhowerBox;

/*
 * Software Engineering project Spring Semester 2016
 * Instructor Sukendeep Samra
 * Group Project EisenhowerBox Team members: Samir Asfirane, Erik Kalan,
 * Team members: Erik Kalan, Javier Valerio, Nelson DeBate,  Ricky Lee,
 * Samir Asfirane, Yu (Will) Tian
 */

/*
 * Class Project defines the object project
 * it's attributes and methods
 * @author Samir Asfirane
 */

import EisenhowerBox.Date;


public class Task {


    private int id = 0;                 // define the Task Id and intializes it
    private String taskName = null;     //define the task name and intialize it to null
    private String taskDescription = null; //allows the user to put a detailed description of the task
    private String tag = null;          //allows users to add a tag to the task
    private String comment = null;      //allows users to add a comment to the task
	private Date startDate = null, endDate = null ;  //define the project start date and end date
    private String status = null;     //define the status of the task
	Project.Utile.Importance importance = Project.Utile.Importance.NOTINDICATED;  //define the Project priority
	Project.Utile.Priority prio = Project.Utile.Priority.NOTURGENT;  //define the Project priority

    /**
	//Constructor with no arguments
    */
	public Task() { }


    /**
	//Constructor with one argument the name of the project
    */
	public Task(String name){
        this.taskName = name;   //assigns project name
	}


    /**
	//Constructor with three arguments, project name start date and end date
    */
    public Task(String name, Date st_date, Date endDate){
        this.taskName = name;    //assigns project name
	    this.startDate = st_date;    //assigns project start date
	    this.endDate = endDate;    //assigns project end date
        this.status = "Started";
    }


    /**
    //Constructor with three arguments, project name start date and end date
    */
    public Task(String name, Date st_date, Date endDate,Project.Utile.Importance import_ance){
	    this.taskName = name;    //assigns project name
	    this.startDate = st_date;    // assigns project start date
        this.endDate = endDate;    // assigns project end date
        this.importance = import_ance;   // assigns the importance of the project
        this.status = "Started";
    }


    /**
    //accessors methods for all objects attributes
    //takes no arguments and returns a string
    //representing the current object name
    */
    public String getTskName(){
    	return taskName;
    }


    /**
    //accessors methods for all objects attributes
    //takes no arguments and returns a string
    //representing the current object name
    */
    public String getTskDescription(){
        return taskDescription;
    }


    /**
    //takes no arguments and returns an object of Type
    //Date representing the curent project start date
    */
    public Date getTskStrtDate(){
    	return startDate;
    }


    /**
    //takes no arguments and returns an object of type
    //date representing the curent project end date
    */
    public Date getTskEndDate(){
    	return endDate;
    }


    /**
    //method getTskId takes no arguments and returns
    //an integer representing the id of the current task
    */
    public int getTskId(){
      return id;
    }


    /**
    //returns a String representation of the status
    //of the task
    */
    public String getTaskStatus(){
    	return status;
    }


    /**
    //takes no arguments and returns a type Importance representing
    //the current object importance
    */
    public Project.Utile.Importance getTskImportance(){
    	return importance;
    }


    /**
    //method getTskPriority takes no argument and return an argument of type
    //type Priority
    */
    public Project.Utile.Priority getTskPriority(){
        return this.prio;
    }


    //takes no arguments and returns the urgency of the task
    public void getTaskUrgency() {

      //due today
      if (startDate == endDate && startDate != null && endDate != null){



      }


      //7 days

      //one month
      //return number of days left as an int

    }


    /**
    //adds an ID for a task
    */
    public void setTskId(int id){
      this.id = id;
    }


    /**
    //mutators methods for all objects attributes
    //takes an argument of type String changes the name of the project
    //to the argument and returns no argument
    */
    public void setTskName(String name){
    	this.taskName = name;
    }


    /**
    //mutators methods for all objects attributes
    //takes an argument of type String changes the name of the project
    //to the argument and returns no argument
    */
    public void setTskDescription(String description){
        this.taskDescription = description;
    }


    /**
    //takes a reference to an object of type date
    //changes the current start date to the argument and returns no argument
    */
    public void setTskStrtDate(Date date){
    	this.startDate = date;
    }


    /**
    //takes a reference to an object of type date
    //changes the current end date to the argument and returns no argument
    */
    public void setTskEndDate(Date date){
    	this.endDate = date;
    }


    /**
    //changes the value of the status variable and returns
    //no argument
    */
    public void setTaskStatus(String status){
    	this.status = status;
    }


    /**
    //takes no arguments and returns a type Importance
    //representing the current object importance
    */
    public void setTskImportance(Project.Utile.Importance import_tant){
    	this.importance = import_tant;
    }


    /**
    // method setTskPriority takes an argument of type enum Priority
    // and sets the current task priority to its argument
    */
    public void setTskPriority(Project.Utile.Priority prio){
    this.prio = prio;
    }


    /**
    //takes a string that will add a tag to the task
    //this tag will be added to the database
    */
    public void setTag(String taskTag) {
    	tag = taskTag;
    }


    /**
    //takes a string that will be added to the task
    //as a comment..multiple comments can be added
    //comments will be stored in a database
    */
    public void setComment(String userComment) {
    	comment = userComment;
    }


    /**
    // method setTskStatus() returns no argument and takes an argument of type
    // boolean and sets the current status to the value of its argument
    */
     void setTskStatus(String status){
        this.status = status;

    }


    /**
    // method isComplete() takes no arguments and returns
    // a boolean true is the task is complete false otherwise
    */
    boolean isComplete(){
        return status == "Completed";      // return true if the task is completed
    }

}


//priority
//description method
