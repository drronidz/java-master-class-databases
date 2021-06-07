package com.drronidz.model;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 6/7/2021 , 
    CREATED ON : 2:03 PM
*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING =
            "jdbc:sqlite:C:\\Work\\Java\\Java Vanilla\\java-master-class-databases\\Music\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";

    private Connection connection;

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;

        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " +e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Artist> queryArtists() {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {

            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(COLUMN_ARTISTS_ID));
                artist.setName(resultSet.getString(COLUMN_ARTISTS_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
//        finally {
//            try {
//                if(resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Error closing ResultSet :" + e.getMessage() );
//                e.printStackTrace();
//            }
//
//            try {
//                if(statement != null) {
//                    statement.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Error closing Statement :" + e.getMessage() );
//                e.printStackTrace();
//            }
//        }
    }
}

