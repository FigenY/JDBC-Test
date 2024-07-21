package com.eurotech.tests;

import org.testng.annotations.Test;

import java.sql.*;

public class JDBC1 {
    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "12345678");
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet =statement.executeQuery("select * from employees limit 50");

        resultSet.next();
        System.out.println("resultSet.getInt(\"emo_no\") = " + resultSet.getInt("emp_no"));
        System.out.println("resultSet.getString(\"emp_no\") = " + resultSet.getString("emp_no"));
        System.out.println("resultSet.getDate(2) = " + resultSet.getDate(2));
        System.out.println("resultSet.getString(2) = " + resultSet.getString(2));

        resultSet.absolute(20);
        System.out.println("resultSet.getString(3) = " + resultSet.getString(3));
        resultSet.last();
        System.out.println("resultSet.getString(4) = " + resultSet.getString(4));
        resultSet.previous();
        System.out.println("resultSet.getString(5) = " + resultSet.getString(5));
        resultSet.first();
        System.out.println("resultSet.getString(4) = " + resultSet.getString(4));
        resultSet.beforeFirst();
        while(resultSet.next()){
            System.out.println("resultSet.getInt(1) = " + resultSet.getInt(1));
        }


    }
}
