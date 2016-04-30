package EisenhowerBox;

/*
 * Software Engineering Project Spring Semester 2016
 * Instructor: Sukendeep Samra
 * Group Project EisenhowerBox Team members: Samir Asfirane, Erik Kalan,
 * Team members: Erik Kalan, Javier Valerio, Nelson DeBate,  Ricky Lee,
 * Samir Asfirane, Yu (Will) Tian
 */

/*
 * Class Project defines the object project
 * it's attributes and methods
 * @author Samir Asfirane, Nelson DeBate
 */

import EisenhowerBox.Date;


public class Task {


    private int taskId = 0;                             //define the Task Id and intializes it
    private int taskProjId = 0;                         //define the Task Project Id and intializes it
    private String taskName = null;                     //define the task name and intialize it to null
    private String taskDescription = null;              //allows the user to put a detailed description of the task
    private Date startDate = null, endDate = null ;     //define the project start date and end date
    private String taskStatus = null;                   //define the status of the task
    private String taskTag = null;                      //allows users to add a tag to the task
    private String taskComment = null;                  //allows users to add a comment to the task
    Project.Utile.Importance importance = Project.Utile.Importance.NOTINDICATED;    //define the Project priority
    Project.Utile.Priority prio = Project.Utile.Priority.NOTURGENT;     //define the Project priority

    /**
     //Constructor with no arguments
     */
    public Task() { }


    /**
     //Constructor with one argument the name of the task
     */
    public Task(String name){
        this.taskName = name;   //assigns task name
    }


    /**
     //Constructor with three arguments, project name, start date, end date
     */
    public Task(String name, Date st_date, Date endDate){
        this.taskName = name;           //assigns task name
        this.startDate = st_date;       //assigns task start date
        this.endDate = endDate;         //assigns task end date
        this.taskStatus = "Started";
    }


    /**
     //Constructor with four arguments, project name, start date, end date, importance
     */
    public Task(String name, Date st_date, Date endDate,Project.Utile.Importance import_ance){
        this.taskName = name;               //assigns task name
        this.startDate = st_date;           //assigns task start date
        this.endDate = endDate;             //assigns task end date
        this.importance = import_ance;      //assigns the importance of the task
        this.taskStatus = "Started";
    }


    /**
     //Constructor with five arguments, project name, start date, end date, description, importance
     */
    public Task(String name, Date st_date, Date endDate, String description, Project.Utile.Importance import_ance){
        this.taskName = name;               //assigns task name
        this.taskDescription = description; //assigns a description to the task
        this.startDate = st_date;           //assigns task start date
        this.endDate = endDate;             //assigns task end date
        this.importance = import_ance;      //assigns the importance of the task
        this.taskStatus = "Started";
    }


    /**
     //Constructor with six arguments, project name, description, start date, end date, tag, importance
     */
    public Task(String name, String description, Date st_date, Date endDate, String tag, Project.Utile.Importance import_ance){
        this.taskName = name;               //assigns task name
        this.taskDescription = description; //assigns a description to the task
        this.startDate = st_date;           //assigns task start date
        this.endDate = endDate;             //assigns task end date
        this.taskTag = tag;                 //assigns task a tag
        this.importance = import_ance;      //assigns the importance of the task
        this.taskStatus = "Started";
    }


    /**
     //does not take arguments and returns
     //an integer representing the id
     //of the current task
     */
    public int getTaskId(){
        return taskId;
    }


    /**
     //takes no arguments and returns and integer representing
     //the ID of the project that the task belongs to, if ID is null task doesn't
     //belong to any project
     //@return the TskPjID
     */
    public int getTaskProjectId() {
        return taskProjId;
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
     //returns a String representation of the status
     //of the task
     */
    public String getTaskStatus(){
        return taskStatus;
    }


    /**
     //returns the tag of the task
     */
    public String getTaskTag(){
        return taskTag;
    }


    /**
     //returns the tag of the task
     */
    public String getTaskComment(){
        return taskComment;
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


    //not finished, might not use
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
    public void setTaskId(int id){
        this.taskId = id;
    }


    /**
     //method setTskPjID() takes an argument of type integer and sets the TskPjID
     //of the task to it
     //@param TskPjID the TskPjID to set
     */
    public void setTaskPjID(int taskProjectId) {
        this.taskProjId = taskProjectId;
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
        this.taskStatus = status;
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
    public void setTag(String tag) {
        this.taskTag = tag;
    }


    /**
     //takes a string that will be added to the task
     //as a comment..multiple comments can be added
     //comments will be stored in a database
     */
    public void setComment(String userComment) {
        userComment = taskComment;
    }


    /**
     // method setTskStatus() returns no argument and takes an argument of type
     // boolean and sets the current status to the value of its argument
     */
    void setTskStatus(String status){
        this.taskStatus = status;

    }


    /**
     //takes no arguments and changes taskStatus to "Completed"
     */
    boolean isComplete() {
        return taskStatus == "Completed";
    }

}

