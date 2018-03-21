package t34;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Tsz Hey Lam 21/03/2018
 * This class primarily handles the connection and sql queries to the HSQLDB and returns story titles
 */

public class DatabaseHandler {

    private String databaseURL = "";
    private Connection con;
    private Statement stmt;
    private ResultSet result;

    public DatabaseHandler(){
        con = null;
        stmt = null;
        result= null;
    }

    public ArrayList<String> queryLabelsReturned(String label){
        //Results array list will store the query results
        ArrayList<String> results = new ArrayList<>();

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();
            result = stmt.executeQuery(
                    "SELECT * FROM stories x INNER JOIN (SELECT * FROM taggedStories WHERE tagID = '" + label.toLowerCase() + "') y ON x.storyid = y.storyid");

            //Looping over each result we will add them to the results arraylist
            while(result.next()){
                System.out.println(result.getInt("storyid")+" | "+
                        result.getString("title")+" | "+
                        result.getString("filepath"));
                results.add(result.getString("filepath"));
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            //Currently returning null, in the future hope to implement a more robust implementation
            return null;
        }
    }
}
