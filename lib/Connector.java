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
        PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int parameterIndex = 1;
        for (Object value : row.values()) {
            preparedStatement.setObject(parameterIndex++, value);
        }
        
        // Execute the insert query
        int rowsAffected = preparedStatement.executeUpdate();
        // Retrieve the generated private key
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedPrivateKey = generatedKeys.getInt(1);
            System.out.println("Generated Private Key: " + generatedPrivateKey);
        }
        
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


public static boolean updateRow(String tableName, Map<String, Object> oldRow, Map<String, Object> newRow) {
    try (Connection conn = Connector.getConnection();
         Statement statement = conn.createStatement()) {

        // Build the SQL query
        StringBuilder queryBuilder = new StringBuilder("UPDATE " + tableName + " SET ");

        for (String columnName : newRow.keySet()) {
            queryBuilder.append(columnName).append(" = ?, ");
        }

        // Remove the trailing comma and space
        queryBuilder.setLength(queryBuilder.length() - 2);

        // Add the WHERE clause based on the old row values
        queryBuilder.append(" WHERE ");

        boolean firstCondition = true;
        for (String columnName : oldRow.keySet()) {
            if (!firstCondition) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(columnName).append(" = ?");
            firstCondition = false;
        }

        String query = queryBuilder.toString();

        // Prepare the statement and set the parameter values
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        int parameterIndex = 1;
        for (Object value : newRow.values()) {
            preparedStatement.setObject(parameterIndex++, value);
        }

        for (Object value : oldRow.values()) {
            preparedStatement.setObject(parameterIndex++, value);
        }

        // Execute the update query
        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public static boolean deleteRow(String tableName, Map<String, Object> row) {
    try (Connection conn = Connector.getConnection();
         Statement statement = conn.createStatement()) {

        // Build the SQL query
        StringBuilder queryBuilder = new StringBuilder("DELETE FROM " + tableName + " WHERE ");

        boolean firstCondition = true;
        for (String columnName : row.keySet()) {
            if (!firstCondition) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(columnName).append(" = ?");
            firstCondition = false;
        }

        String query = queryBuilder.toString();

        // Prepare the statement and set the parameter values
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        int parameterIndex = 1;
        for (Object value : row.values()) {
            preparedStatement.setObject(parameterIndex++, value);
        }

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}   
public static void printListMap(List<Map<String, Object>> list) {
    for (Map<String, Object> row : list) {
        System.out.println(row);
    }
}
public static List<Map<String, Object>> customQuery(String query) {
    List<Map<String, Object>> tableData = new ArrayList<>();

    try (Connection conn = Connector.getConnection();
         Statement statement = conn.createStatement();
         ResultSet sqlReturnObj = statement.executeQuery(query)) {

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
public static void printListMap(Map<String, Object> map) {
    System.out.println(map);
}
// Using the custom query function, a function that takes in a table name and a dictionary
// and returns all results from the table where the dictionary values match, as a list of dictionaries
public static List<Map<String, Object>> getMatchingRows(String tableName, Map<String, Object> filters) {
    StringBuilder queryBuilder = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
    boolean firstCondition = true;
    
    // Build the WHERE clause conditions based on the filter dictionary
    for (String columnName : filters.keySet()) {
        if (!firstCondition) {
            queryBuilder.append(" AND ");
        }
        queryBuilder.append(columnName).append(" = ?");
        firstCondition = false;
    }

    String query = queryBuilder.toString();

    // Create a List to store the query parameters
    List<Object> queryParameters = new ArrayList<>(filters.values());

    // Execute the custom query with the query string and parameters
    return customQuery(query, queryParameters);
}

// Overload the customQuery method to accept query parameters
public static List<Map<String, Object>> customQuery(String query, List<Object> parameters) {
    List<Map<String, Object>> tableData = new ArrayList<>();

    try (Connection conn = Connector.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(query)) {

        // Set the query parameters
        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setObject(i + 1, parameters.get(i));
        }

        // Execute the query
        ResultSet sqlReturnObj = preparedStatement.executeQuery();

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
    public static void testInsertManager() {
        String tableName = "Manager";
    
        // Create a dictionary (Map) representing the row to be inserted
        Map<String, Object> managerRow = new HashMap<>();
        // managerRow.put("ssn", "100000177");
    
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
        personRow.put("ssn", "101");
        personRow.put("firstName", "Hailey");
        personRow.put("lastName", "Camacho");
        personRow.put("phoneNumber", "402-946-4536");
        personRow.put("emailAddress", "camacho3936@gmail.com");
        personRow.put("dateOfBirth", "1978-04-14");

        // Call the insertRow function
        boolean insertSuccessful = insertRow(tableName, personRow);
    
        if (insertSuccessful) {
            System.out.println("Empty person row inserted successfully.");
        } else {
            System.out.println("Failed to insert Person row.");
        }
    }
    public static void testUpdatePerson() {
        String tableName = "Person";
    
        // Create a dictionary (Map) representing the existing row to be updated
        Map<String, Object> oldPersonRow = new HashMap<>();
        oldPersonRow.put("ssn", "101");
        oldPersonRow.put("firstName", "Hailey");
        oldPersonRow.put("lastName", "Camacho");
        oldPersonRow.put("phoneNumber", "402-946-4536");
        oldPersonRow.put("emailAddress", "camacho3936@gmail.com");
        oldPersonRow.put("dateOfBirth", "1978-04-14");
    
        // Create a dictionary (Map) representing the updated values for the row
        Map<String, Object> newPersonRow = new HashMap<>();
        newPersonRow.put("emailAddress", "cnewmeil3936@gmail.com");
    
        // Call the updateRow function
        boolean updateSuccessful = updateRow(tableName, oldPersonRow, newPersonRow);
    
        if (updateSuccessful) {
            System.out.println("Person row updated successfully.");
        } else {
            System.out.println("Failed to update Person row.");
        }
    }

    public static void testDeletePerson() {
        String tableName = "Person";
    
        // Create a dictionary (Map) representing the row to be deleted
        Map<String, Object> oldPersonRow = new HashMap<>();
        oldPersonRow.put("ssn", "101");
        oldPersonRow.put("firstName", "Hailey");
        oldPersonRow.put("lastName", "Camacho");
        oldPersonRow.put("phoneNumber", "402-946-4536");
        oldPersonRow.put("emailAddress", "cnewmeil3936@gmail.com");
        oldPersonRow.put("dateOfBirth", "1978-04-14");
    
        // Call the deleteRow function
        boolean deleteSuccessful = deleteRow(tableName, oldPersonRow);
    
        if (deleteSuccessful) {
            System.out.println("Person row deleted successfully.");
        } else {
            System.out.println("Failed to delete Person row.");
        }
    }
    public static void main(String[] args) {
        testBasicSql();
        testDictionaryGet();
        testInsertPerson();
        testUpdatePerson();
        testDeletePerson();
    }
}