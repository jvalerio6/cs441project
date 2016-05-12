package EisenhowerBox;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;


// build with:
// javac EisenhowerBox/Dbdriver.java EisenhowerBox/Task.java EisenhowerBox/Project.java EisenhowerBox/DbManager.java EisenhowerBox/TeamMember.java

// then
// java -classpath ".:sqlite-jdbc-3.8.11.2.jar" EisenhowerBox/Dbdriver

public class Dbdriver {
    public static  void test() {
        // ==== Create a new DbManager Class, this handle all sql connections =====
        DbManager dbm = DbManager.getInstance();

        // ================ User creation ====================
        // == create user (username, pw)
        // dbm.createUser("will", "123");
        // dbm.createUser("ricky", "1231231");
        // dbm.createUser("erik", "456");
        // dbm.createUser("nelson", "999");
        // dbm.createUser("jack", "111");

        // == create user with user object
        // TeamMember new_members = new TeamMember("Javier", "921312");
        // dbm.createUser(new_members);

        // ================ Task Creation ===========================
        // == create task (user_id, task_title, task_content)
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


        // =========== Get Task Testing ================
        // create arraylist of task object
        // call the funciton with user id, return arraylist of task objects
        ArrayList<Task> task_list = new ArrayList<Task>();
        task_list = dbm.getTaskList();

        // print out the task
        System.out.printf("\n=== Task id: %d === \n", task_list.get(0).getTaskId());
        System.out.println(task_list.get(0).getTskStrtDate());
        System.out.println(task_list.get(0).getTskName());
        System.out.println(task_list.get(0).getTskDescription());
        System.out.println();


        // =========== Get User testing ================
        // if the user doesn't exit, return a empty user object with ID = 0
        TeamMember current_user = dbm.getUser("lala");
        System.out.printf("ID: %S / Name: %s / PW: %s\n", current_user.ID, current_user.name, current_user.password);

        // if it exist, return user object
        current_user = dbm.getUser("will");
        System.out.printf("ID: %S / Name: %s / PW: %s\n", current_user.ID, current_user.name, current_user.password);


        // ============ Update Task =================
        // int task_id, String task_title, String task_content, Date due_date
        // Date dNow = new Date();
        dbm.updateTask(3, "updated new title is here !!!!!!!!", "content", 1, 3);
    }
}
