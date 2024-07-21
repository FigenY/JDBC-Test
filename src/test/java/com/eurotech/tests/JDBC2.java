package com.eurotech.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC2 {
    String dbUrl="jdbc:mysql://localhost/mentoring";
    String dbUsername="root";
    String dbPassword="12345678";
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @BeforeMethod
    public void setUpn() throws Exception{
    connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
    statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    @AfterMethod
    public void tearDown() throws Exception{
        connection.close();
        statement.close();
        resultSet.close();
    }
    @Test
    public void metadataTest() throws Exception{
       // resultSet= statement.executeQuery("select * from salaries");
    }
}
