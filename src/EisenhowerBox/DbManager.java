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
        db_name = "EisenHower.db";
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
            stmt = c.createStatement();
            stmt.executeUpdate(sql_query);

            if (func_name == "getTask"){
                System.out.println("get task class");
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

    // fake grid location generator 
    public int randInt(){
        int min = 1;
        int max = 4;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    // ============== DB ===================
    // Create users
    // Pass in username and password
    public void createUser(String username, String password) {
        String func_name = "createUser";

        // initialize sql query
        System.out.printf("Create username: %s pw: %s\n", username, password);
        String sql_query = String.format("INSERT into user "+
            "(username, password) values ('%s', '%s')", 
            username, password); 

        // execute sql_query
        localExecuteSqlQuery(func_name, sql_query);
    }


    // fucntion to create task
    public void createTask(int user_id, String task_title, String task_content) {
        String func_name = "createTask";
        String current_datetime;

        // get current datetime
        // Need to convert java date obj into: TEXT as strings ("YYYY-MM-DD HH:MM:SS.SSS") for sqlite
        Date dNow = new Date();

        //create a data formatter, and use it convert it to a string. 
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");
        current_datetime = ft.format(dNow);

        // grid location
        // Eric says he need this value to do the gui
        // right now it's a random number fro2m 1 to 4
        int grid_location = randInt();

        System.out.printf("Create task for user %d\n", user_id);
        String sql_query = String.format("INSERT into task " +
                "(user_id, task_title, task_content, date_created, date_last_mod, grid_location) values " +
                "('%s', '%s', '%s', '%s', '%s', '%s')", 
                user_id, task_title, task_content, current_datetime, current_datetime, grid_location); 

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
    public List getTaskArray(int user_id) {
        String func_name = "getTask";
        List<String[]> table = new ArrayList<>();

        String sql_query = String.format("SELECT * "+
            "from task WHERE user_id = '%s'", 
            user_id); 

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

    // Function to get arraylist of task object
    public ArrayList<Task> getTask(int user_id) {
        String func_name = "getTask";

        // sql query to select all task assgiend to 'user_id'
        String sql_query = String.format("SELECT * "+
            "from task WHERE user_id = '%s'", 
            user_id); 

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
                String date_created = result.getString("date_created");
                String date_last_mod = result.getString("date_last_mod");
                String task_title = result.getString("task_title");
                String task_content = result.getString("task_content");

                // create new task using the string
                Task temp_task = new Task(id, date_created, date_last_mod, task_title, task_content);

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
