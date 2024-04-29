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

public static boolean insertRow(String tableName, Map<String, Object> row) {
    try (Connection conn = Connector.getConnection();
         Statement statement = conn.createStatement()) {

        // Generate private key if empty
        if (row.containsKey("private_key") && (row.get("private_key") == null) || row.get("private_key") == "") {
            ResultSet maxKeyResult = statement.executeQuery("SELECT MAX(private_key) FROM " + tableName);
            int maxKey = 0;
            if (maxKeyResult.next()) {
                maxKey = maxKeyResult.getInt(1);
            }
            row.put("private_key", maxKey + 1);
        }

        // Build the SQL query
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesBuilder = new StringBuilder("VALUES (");

        for (String columnName : row.keySet()) {
            queryBuilder.append(columnName).append(", ");
            valuesBuilder.append("?, ");
        }

        // Remove the trailing comma and space
        queryBuilder.setLength(queryBuilder.length() - 2);
        valuesBuilder.setLength(valuesBuilder.length() - 2);

        queryBuilder.append(") ");
        valuesBuilder.append(")");

        String query = queryBuilder.toString() + valuesBuilder.toString();

        // Prepare the statement and set the parameter values
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        int parameterIndex = 1;
        for (Object value : row.values()) {
            preparedStatement.setObject(parameterIndex++, value);
        }

        // Execute the insert query
        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
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
    public static void testInsertManager() {
        String tableName = "Manager";
    
        // Create a dictionary (Map) representing the row to be inserted
        Map<String, Object> managerRow = new HashMap<>();
        managerRow.put("ssn", "100000177");
    
        // Call the insertRow function
        boolean insertSuccessful = insertRow(tableName, managerRow);
    
        if (insertSuccessful) {
            System.out.println("Manager row inserted successfully.");
        } else {
            System.out.println("Failed to insert Manager row.");
        }
    }
    public static void testInsertPerson() {
        String tableName = "Person";
    
        // Create a dictionary (Map) representing the row to be inserted
        Map<String, Object> personRow = new HashMap<>();
        personRow.put("ssn", "");
    
        // Call the insertRow function
        boolean insertSuccessful = insertRow(tableName, personRow);
    
        if (insertSuccessful) {
            System.out.println("Empty person row inserted successfully.");
        } else {
            System.out.println("Failed to insert Person row.");
        }
    }
    public static void main(String[] args) {
        testBasicSql();
        testDictionaryGet();
        testInsertManager();
        testInsertPerson();
    }
}
