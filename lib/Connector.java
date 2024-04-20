/**
 * Name:    Matthew Tam
 * Group:   2
 * Created: 19 April 2024
 * File:    Connector.java
 * Description:
 *  A class called Connector to be used to
 *  get a connection with full permissions to the
 *  database. To be used for the Group Project. 
 *  Uses a mock user that was added to the database.
 */
package lib;
import java.sql.*;

public class Connector{
    //Expects the database to exist on localhost with the default port
    private static String url = "jdbc:mysql://localhost:3306/respitecare";

    //mock user 
    private static String username = "user";
    private static String password = "password";
    /**
     * Returns a connection to the database with full permissions.
     * Requires the JDBC driver to be in the classpath.
     */
    public static Connection getConnection() throws SQLException{
        //If the driver does not automatically load, try the below.
        /** 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e){
            throw new IllegalStateException("Driver not in classpath", e);
        }
        */

        return DriverManager.getConnection(url, username, password);
    }
}