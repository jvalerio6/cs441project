package EisenhowerBox;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.sql.*; 


// build with:
// javac Dbdriver.java
// java -classpath ".:sqlite-jdbc-3.8.11.2.jar" Dbdriver


// javac EisenhowerBox/Dbdriver.java EisenhowerBox/Task.java EisenhowerBox/Project.java EisenhowerBox/DbManager.java EisenhowerBox/TeamMember.java
// java -classpath ".:sqlite-jdbc-3.8.11.2.jar" EisenhowerBox/Dbdriver

public class Dbdriver {
    public static void main(String[] args) {
        System.out.println("Staring");
        DbManager dbm = new DbManager();

        // // create user (username, pw)
        // dbm.createUser("will", "123");
        // dbm.createUser("ricky", "1231231");
        // dbm.createUser("erik", "456");
        // dbm.createUser("nelson", "999");
        // dbm.createUser("jack", "111");

        // create task (user_id, task_title, task_content)
        // dbm.createTask(1, "Buy toliet paper", "extra soft one");
        // dbm.createTask(1, "Prepare for midterm", "");
        // dbm.createTask(1, "Start reading network project material", "");
        // dbm.createTask(2, "Do stuff", "lalalalalalallala");
        // dbm.createTask(2, "OS homework", "it is hard, make time for it");
        // dbm.createTask(1, "Learn python", "Learn numpy");
        // dbm.createTask(1, "Job interview", "For summer internship");
        // dbm.createTask(1, "Read that book", "~~~~~~~~~~~");
        // dbm.createTask(3, "Shopping for birthday", "");
        // dbm.createTask(2, "Dinner with xx", "AT xx street");
        // dbm.createTask(1, "Finish lab4 for Design pattern class", "read 3 page of doc first");
        // dbm.createTask(1, "Buy new phone", "");
        // dbm.createTask(1, "Watch the GSW game", "Last game of the season");


        // create arraylist of task object
        ArrayList<Task> task_list = new ArrayList<Task>();

        // call the funciton with user id, this
        // this return a arraylist of task objects
        task_list = dbm.getTask(1);

        // print out the task
        System.out.printf("\n=== Task id: %d === \n", task_list.get(0).getTaskId());
        System.out.println(task_list.get(0).getTskStrtDate());
        System.out.println(task_list.get(0).getTskName());
        System.out.println(task_list.get(0).getTskDescription());
        System.out.println();

        // get teamMmber testing............
        TeamMember temp = dbm.getUser("will");
        System.out.println(temp.name);
    
    }
}
