package com.drronidz;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 6/7/2021 , 
    CREATED ON : 11:39 AM
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        // New format
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Work\\Databases\\test.db")){
            // Class.forName("org.sql.JDBC");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
                    "(name TEXT, phone INTEGER, email TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong: " + e.getMessage());
        }
        // Old format
//        try {
//            // Class.forName("org.sql.JDBC");
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Work\\Databases\\test.db");
//            Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
//
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
    }
}
