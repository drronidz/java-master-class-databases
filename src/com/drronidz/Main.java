package com.drronidz;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 6/7/2021 , 
    CREATED ON : 11:39 AM
*/

import java.sql.*;

public class Main {
    public static final String DB_NAME = "test.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Work\\Databases\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

        // New format
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)){
//            connection.setAutoCommit(false);
            // Class.forName("org.sql.JDBC");
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS "
                    + TABLE_CONTACTS
                    + "("
                    + COLUMN_NAME  + " text, "
                    + COLUMN_PHONE + " integer, "
                    + COLUMN_EMAIL + " text"
                    + ")");


            insertContact(statement,"Tim", 665544, "tim@email.com");
            insertContact(statement,"Joe", 998877, "joe@anywhere.com");
            insertContact(statement,"Jane", 112233, "jane@everywhere.com");
            insertContact(statement,"John", 332211, "john@somewhere.com");

            statement.execute("UPDATE "
                    + TABLE_CONTACTS
                    + " SET "
                    + COLUMN_PHONE + "=779616727"
                    + " WHERE "
                    + COLUMN_NAME + "='Jane'");

            statement.execute("DELETE FROM "
                    + TABLE_CONTACTS
                    + " WHERE "
                    + COLUMN_NAME + "='Joe'");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);

            while (resultSet.next()) {
                System.out.println(
                                  resultSet.getString(COLUMN_NAME) + " "
                                + resultSet.getInt(COLUMN_PHONE) + " "
                                + resultSet.getString(COLUMN_EMAIL));
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
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

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO "
                + TABLE_CONTACTS
                + "( " + COLUMN_NAME
                + ", " + COLUMN_PHONE
                + ", " + COLUMN_EMAIL
                + ")"
                + "VALUES ('" + name + "', " + phone + ", ' " + email + "')");
    }
}
