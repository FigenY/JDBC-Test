package com.eurotech;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static Connection connection;
    private static Statement statement;

    private static ResultSet resultSet;

    public static void createConnection() {
        String dbURL = "jdbc:mysql://localhost:3306/employees";
        String dbUsername = "root";
        String dbPassword = "test123";
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {

        }
    }
    public static void createConnection(String dbURL, String dbUsername, String dbPassword) {

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {

        }
    }


    public static void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {

        }

    }

    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }

    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }

    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }

    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i < rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }

        } catch (SQLException e) {

        }
        return rowList;
    }

    public static List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
        } catch (SQLException e) {
        }
        return rowList;
    }

    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i < rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
        }
        return rowList;
    }

    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            for (int i = 1; i < rsmd.getColumnCount(); i++) {
                columns.add(rsmd.getColumnName(i));
            }

        } catch (SQLException e) {

        }
        return columns;
    }

    private static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {

        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {

        }

    }

    public static int getRowCount() {
        int rowCount = 0;
        try {
            resultSet.last();
            int rowCount1 = resultSet.getRow();
            rowCount = rowCount1;
        } catch (SQLException e) {
        }
        return rowCount;
    }
}
