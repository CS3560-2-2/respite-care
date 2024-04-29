/**
 * Name:    Matthew Tam & Simon Nasser
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connector{
    //Expects the database to exist on localhost with the default port
    private static String url = "jdbc:mysql://localhost:3306/respitecare";

    private static String username = "admin";
    private static String password = "%55%cP*Xq&hIDwjrF0@l";
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
            throw new IllegalStateException("Driver not in classpath", e); }
        */
        return DriverManager.getConnection(url, username, password);
    }
    public static ResultSet getTable(String tableName) {
        try (Connection conn = Connector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet sqlReturnObj = statement.executeQuery("SELECT * FROM " + tableName)) {
            return sqlReturnObj;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Map<String, Object>> getList(String tableName) {
    List<Map<String, Object>> tableData = new ArrayList<>();

    try (Connection conn = Connector.getConnection();
         Statement statement = conn.createStatement();
         ResultSet sqlReturnObj = statement.executeQuery("SELECT * FROM " + tableName)) {

        ResultSetMetaData metaData = sqlReturnObj.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (sqlReturnObj.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = sqlReturnObj.getObject(i);
                row.put(columnName, value);
            }
            tableData.add(row);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tableData;
}
   
    
    
    
    
    
    
    
    
    
    
    
    
    public static void testBasicSql() {
        try (Connection conn = Connector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet sqlReturnObj = statement.executeQuery("SELECT * FROM Person")) {
            ResultSetMetaData metaData = sqlReturnObj.getMetaData();
            int columnCount = metaData.getColumnCount();
    
            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
    
            // Print data rows
            while (sqlReturnObj.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(sqlReturnObj.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void testDictionaryGet() {
        String tableName = "Person";
        List<Map<String, Object>> Person = getList(tableName);
    
        System.out.println("Person Table Contents:");
        for (Map<String, Object> row : Person) {
            System.out.println(row);
        }
    }
    public static void main(String[] args) {
        testBasicSql();
        testDictionaryGet();
    }
}
