package com.drronidz;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 6/7/2021 , 
    CREATED ON : 11:39 AM
*/

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        // New format
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Work\\Databases\\test.db")){
//            connection.setAutoCommit(false);
            // Class.forName("org.sql.JDBC");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
                    "(name TEXT, phone INTEGER, email TEXT)");

//            statement.execute("INSERT INTO contacts (name, phone, email) " +
//                    "VALUES ('Joe', 12654, 'joe@gmail.com')");
//
//            statement.execute("INSERT INTO contacts (name, phone, email) " +
//                    "VALUES ('Jane', 654321, 'jane@gmail.com')");
//
//            statement.execute("INSERT INTO contacts (name, phone, email) " +
//                    "VALUES ('Fido', 112233, 'fido@gmail.com')");
//
//            statement.execute("INSERT INTO contacts (name, phone, email) " +
//                    "VALUES ('Abdou', 332211, 'abdou@gmail.com')");

//            statement.execute("UPDATE contacts SET phone=123654 WHERE name='Joe'");
//
//            statement.execute("DELETE FROM contacts WHERE name ='TIM'");


            statement.execute("SELECT * FROM contacts");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                System.out.println(
                          resultSet.getString("name") + " "
                        + resultSet.getInt("phone") + " "
                        + resultSet.getString("email"));
            }
            resultSet.close();

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
