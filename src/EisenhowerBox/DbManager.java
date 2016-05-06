package EisenhowerBox;

import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.sql.*; 
import java.text.*;


public class DbManager {
    private Connection c;
    private Statement stmt;

    public DbManager() {
        System.out.println("DBManager Created");
        createConnection();
    }

    // create the connection and put into the global Connection c
    public void createConnection() {
        c = null;
        String db_name;
        String db_target;

        // initialize name and target of database
        db_name = "Eisenhower.db";
        db_target = ("jdbc:sqlite:").concat(db_name);
        // System.out.println(db_target);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(db_target);
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
            // sql query testing.
            // System.out.println(sql_query);

            stmt = c.createStatement();
            stmt.executeUpdate(sql_query);

            // need to update this !!!!
            if (func_name == "getTask" || func_name == "getUser"){
                System.out.println("Call get sql query");
                result = stmt.executeQuery(sql_query);
            }
            // stmt.close();
            return result;
        } catch (Exception e) {
            System.err.printf("error: %s\n", sql_query);
            System.exit(0);
        }
        return result;
    }

    // ============== DB ===================
    // Create users on sql
    public void createUser(String username, String password) {
        String func_name = "createUser";

        // initialize sql query
        // pass username and pw
        System.out.printf("Create username: %s pw: %s\n", username, password);
        String sql_query = String.format("INSERT into TeamMember (MemName, MemPws) values ('%s', '%s')", 
            username, password); 

        // testing sql_query
        System.out.println(sql_query);

        // execute sql_query
        localExecuteSqlQuery(func_name, sql_query);
    }

    // Get user
    // Pass in username, return user object 
    public TeamMember getUser(String username){
        String func_name = "getUser";

        // sql query to select all task assgiend to 'member_id'
        String sql_query = String.format("SELECT * from TeamMember WHERE MemName = '%s'", 
            username);

        ResultSet result = localExecuteSqlQuery(func_name, sql_query);

        int id = 0;
        String MemName = "";
        String MemPws = "";

        // traverse and wrap the result into task object
        try {
            id = result.getInt("id");
            MemName = result.getString("MemName");
            MemPws = result.getString("MemPws");

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
    public void createTask(int member_id, String task_title, String task_content) {
        String func_name = "createTask";
        String current_datetime;

        // get current datetime
        // Need to convert java date obj into: TEXT as strings ("YYYY-MM-DD HH:MM:SS.SSS") for sqlite
        Date dNow = new Date();

        //create a data formatter, and use it convert it to a string. 
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");
        current_datetime = ft.format(dNow);

        System.out.printf("Create task for user %d\n", member_id);
        String sql_query = String.format("INSERT into Task " +
                "(TeamMember_id, task_title, task_content, start_date, due_date) values " +
                "('%s', '%s', '%s', '%s', '%s')", 
                member_id, task_title, task_content, current_datetime, current_datetime); 

        // testing sql_query
        System.out.println(sql_query);

        // execute sql_query
        localExecuteSqlQuery(func_name, sql_query);
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
        List<String[]> table = new ArrayList<>();

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
    public ArrayList<Task> getTask(int TeamMember_id) {
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

                // convert the string date from sql to Java.util.date
                Date date_created_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(start_date);
                Date due_date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(due_date);

                // create new task using the string
                Task temp_task = new Task(id, task_title, task_content, date_created_temp, due_date_temp);             

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
