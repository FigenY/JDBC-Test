package com.eurotech.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;

public class CrudEmployeesTest {

        String dbUrl = "jdbc:mysql://localhost:3306/employees";
        String dbUser = "root";
        String dbPass = "test123";
        Connection connection;

        {
            try {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testCrud() throws SQLException {

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("select * from employees");
            resultSet.last();
            int empNo = resultSet.getInt("emp_no") + 1;
            String birthDate = "1990-04-21";
            String firstName = "Jack";
            String lastName = "Smith";
            String gender = "M";
            String hireDate = "2024-01-11";
            addEmployee(empNo, birthDate, firstName, lastName, gender, hireDate);
            getEmployee(empNo);
            String newFirstName = "Ali";
            String newLastName = "Korkmaz";
            updateEmployee(empNo, newFirstName, newLastName);
            getEmployee(empNo);
            deleteEmployee(empNo);


        }

        public void addEmployee(int empNo, String birthDate, String firstName, String lastName, String gender, String hireDate) throws SQLException {
            String insertSQL = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES (?,?,?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
            insertStatement.setInt(1, empNo);
            insertStatement.setDate(2, java.sql.Date.valueOf(birthDate));
            insertStatement.setString(3, firstName);
            insertStatement.setString(4, lastName);
            insertStatement.setString(5, gender);
            insertStatement.setDate(6, java.sql.Date.valueOf(hireDate));

            int rows = insertStatement.executeUpdate();
            Assert.assertTrue(rows > 0);
            System.out.println(rows > 0 ? "Employee added successfully" : "Employee not added");

        }

        public void getEmployee(int empNo) throws SQLException {
            String selectSQL = "SELECT * FROM employees WHERE emp_no = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSQL);
            selectStatement.setInt(1, empNo);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("empNo - " + resultSet.getInt("emp_no"));
                System.out.println("birthDate - " + resultSet.getDate("birth_date"));
                System.out.println("firstName - " + resultSet.getString("first_name"));
                System.out.println("lastName - " + resultSet.getString("last_name"));
                System.out.println("gender - " + resultSet.getString("gender"));
                System.out.println("hireDate - " + resultSet.getDate("hire_date"));
            }
        }

        public void updateEmployee(int empNo, String newFirstName, String newLastName) throws SQLException {

            String updateSQL = "UPDATE employees SET first_name = ?, last_name = ? WHERE emp_no = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            updateStatement.setString(1, newFirstName);
            updateStatement.setString(2, newLastName);
            updateStatement.setInt(3, empNo);

            int rows = updateStatement.executeUpdate();
            Assert.assertTrue(rows > 0);
            System.out.println(rows > 0 ? "Employee updated successfully" : "Employee not updated");

        }


        public void deleteEmployee(int empNo) throws SQLException {
            String deleteSQL = "DELETE FROM employees WHERE emp_no = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            deleteStatement.setInt(1, empNo);
            int rows = deleteStatement.executeUpdate();
            Assert.assertTrue(rows > 0);
            System.out.println(rows > 0 ? "Employee deleted successfully" : "Employee not deleted");
        }




    }


