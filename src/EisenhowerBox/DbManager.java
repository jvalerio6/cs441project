package EisenhowerBox;

import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.*;


public class DbManager {
    private Connection c;
    private Statement stmt;
    private static DbManager instance = null;

    public DbManager() {
        System.out.println("DBManager Created");
        try {
			createConnection();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static DbManager getInstance(){
    	if (instance == null){
    		instance = new DbManager();
    	}
    	return instance;
    }

    // create the connection and put into the global Connection c
    public void createConnection() throws FileNotFoundException {
    	c = null;
        String db_name = "EisenHower.db";
        String db_target = null;
        String db_location = null;
        final String fileName = "dbAdress.txt";


        try {
        	System.out.println("Reading dbAddress:");
        	java.net.URL url = getClass().getResource(fileName);
        	String relativePath = new String(url.getPath());
        	 BufferedReader br = new BufferedReader(new FileReader(relativePath));
            db_location = new String(br.readLine());
            br.close();
            System.out.println("Completed file read:");
        } catch (IOException e) {
        	System.out.println("could not open location file for the db closing:");
            System.err.format("IOException: %s%n", e);
        	System.exit(0);
        }

        // initialize name and target of database
        db_target = db_location + db_name;
        System.out.println("Database path: " + db_target);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(db_target);
            System.out.println("Database Connection created");
        } catch (Exception e)  {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        System.out.printf("Opened %s successfully\n", db_name);
    }


    // pass in function name for error reporting
    // pass in sql query to execute
    public ResultSet localExecuteSqlQuery(String func_name, String sql_query) {
        ResultSet result = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql_query);

            // if sql query return values, get result here
            if (func_name == "getTask" || func_name == "getUser") {
                // System.out.println("Call get sql query");
                result = stmt.executeQuery(sql_query);
            }
            // stmt.close();
            return result;
        } catch (Exception e) {
            System.err.printf("Function %s error: %s\n", func_name, sql_query);
            System.exit(0);
        }
        return result;
    }

    // ============== Datebase Action ===================
    // Create user wtih string field
    public void createUser(String username, String password) {
        String func_name = "createUser";

        // initialize sql query - pass username and pw
        System.out.printf("Create username: %s pw: %s\n", username, password);
        String sql_query = String.format("INSERT into TeamMember (MemName, MemPws) values ('%s', '%s')",
            username, password);

        // execue sql_query
        System.out.println(sql_query);
        localExecuteSqlQuery(func_name, sql_query);
    }

    // Create user in sqldb with user object
    public void createUser(TeamMember new_member) {
        // call the createUser Function, pass in string field
        createUser(new_member.name, new_member.password);
    }

    // Get user
    // Pass in username, return user object
    public TeamMember getUser(String username){
        String func_name = "getUser";

        // sql query to select all task assgiend to 'member_id'
        String sql_query = String.format("SELECT * from TeamMember WHERE MemName = '%s'",
            username);

        ResultSet result = localExecuteSqlQuery(func_name, sql_query);

        // traverse and wrap the result into task object
        try {
            int id = result.getInt("id");
            String MemName = result.getString("MemName");
            String MemPws = result.getString("MemPws");

            TeamMember temp_user = new TeamMember(MemName, MemPws, id);
            return temp_user;

        } catch (Exception e) {
            System.out.println(e);

            // if no user were found, return a empty user object with ID = 0
            TeamMember temp_user = new TeamMember("", "", 0);
            return temp_user;
        }
    }


    // create task on sql
    public void createTask(int member_id, String task_title, String task_content, int priority, int importance, String startDate, String endDate) {
        String func_name = "createTask";

        System.out.printf("Create task for user %d\n", member_id);
        String sql_query = String.format("INSERT into Task " +
                "(TeamMember_id, task_title, task_content, start_date, due_date, T_Prio, T_Urg) values " +
                "(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %d, %d);",
                member_id, task_title, task_content, startDate, endDate, priority, importance);

        System.out.println("SQL Query: " + sql_query);

        // execute sql_query
        localExecuteSqlQuery(func_name, sql_query);
    }

    // Update task with string values
    public void updateTask(int task_id, String task_title, String task_content, int priority, int importance) {
        String func_name = "updateTask";

        String sql_query = String.format("UPDATE Task " +
            "set task_title = \"%s\", task_content = \"%s\", T_Prio = %d, T_Urg = %d WHERE id = \"%s\" ",
            task_title, task_content, priority, importance, task_id);

        // print testing
        System.out.println(sql_query);
        localExecuteSqlQuery(func_name, sql_query);
    }

    // Update task with task object
    public void updateTask(Task temp) {
        // get task object field
        int task_id = temp.getTaskId();
        String task_title = temp.getTskName();
        String task_content = temp.getTskDescription();
        int prio = Project.Utile.Priority.valueOf(temp.getTskPriority()).ordinal();
        int imp = Project.Utile.Importance.valueOf(temp.getTskImportance()).ordinal();

        // update task
        updateTask(task_id, task_title, task_content, prio, imp);

    }


    // function to print out all task
    public void printTask(List<String[]> result_table) {
        for(String[] row: result_table ){
                for( String s: row ){
                    System.out.print( " / " + s );
            }
            System.out.println();
        }
    }

    // pass in user id, list out all tasks
    public List getTaskArray(int member_id) {
        String func_name = "getTask";
        List<String[]> table = new ArrayList<String[]>();

        String sql_query = String.format("SELECT * "+
            "from task WHERE member_id = '%s'",
            member_id);

        // execute sql query
        ResultSet result = localExecuteSqlQuery(func_name, sql_query);

        // traverse and print the result
        try {
            int nCol = result.getMetaData().getColumnCount();
            System.out.printf("Number of column: %d\n", nCol);

            // ResultSet starts from 1, arrayList starts from 0
            while( result.next()) {
                String[] row = new String[nCol];
                for( int iCol = 1; iCol <= nCol; iCol++ ){
                    row[iCol-1] = result.getObject( iCol ).toString();
                }
                table.add( row );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return table;
    }

    // Get arraylist of task object using User_id
    public ArrayList<Task> getTaskList(int TeamMember_id) {
        String func_name = "getTask";

        // sql query to select all task assgiend to 'member_id'
        String sql_query = String.format("SELECT * "+
            "from task WHERE TeamMember_id = '%s'",
            TeamMember_id);

        // execute sql query
        ResultSet result = localExecuteSqlQuery(func_name, sql_query);
        ArrayList<Task> task_list = new ArrayList<Task>();

        // traverse and wrap the result into task object
        try {
            // check number of columns
            int nCol = result.getMetaData().getColumnCount();
            System.out.printf("Result has: %d columns. \n", nCol);

            // iterate through the result
            while( result.next()) {
                // assign db result to string
                int id = result.getInt("id");
                String start_date = result.getString("start_date");
                String due_date = result.getString("due_date");
                String task_title = result.getString("task_title");
                String task_content = result.getString("task_content");

                int importanceInt = result.getInt("T_Urg");
                Project.Utile.Importance importance = Task.getImportanceByIndex(importanceInt - 1);

                int priorityInt = result.getInt("T_Prio");
                Project.Utile.Priority priority = Task.getPriorityByIndex(priorityInt - 1);

                // convert the string date from sql to Java.util.date
                Date date_created_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(start_date);
                Date due_date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(due_date);

                // create new task using the string
                Task temp_task = new Task(id, task_title, task_content, date_created_temp, due_date_temp, importance, priority);
                System.out.println("added task: " + temp_task.toString());

                // append the task object to the task arraylist
                task_list.add(temp_task);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // return the arraylist
        return task_list;
    }
}
