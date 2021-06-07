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
    public static final String COLUMN_ALBUMS_ID = "_id";
    public static final String COLUMN_ALBUMS_NAME = "name";
    public static final String COLUMN_ALBUMS_ARTIST = "artist";
    public static final int INDEX_ALBUMS_ID = 1;
    public static final int INDEX_ALBUMS_NAME = 2;
    public static final int INDEX_ALBUMS_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";
    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTISTS_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";
    public static final int INDEX_SONGS_ID = 1;
    public static final int INDEX_SONGS_TRACK = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            " SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ALBUMS_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            "ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + ", " +
                    TABLE_SONGS  + "." + COLUMN_SONGS_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONGS_ALBUM + " = " + TABLE_ALBUMS + "." +
                    COLUMN_ALBUMS_ID + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ARTIST + " = " + TABLE_ARTISTS +
                    "." + COLUMN_ALBUMS_ID + " WHERE " + TABLE_SONGS + "." + COLUMN_SONGS_TITLE + " =\"";

    public static final String QUERY_ARTSIT_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + " COLLATE NOCASE";

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

    public List<Artist> queryArtists(int sortOrder) {

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_ARTISTS);
        if(sortOrder != ORDER_BY_NONE) {
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_ARTISTS_NAME);
            stringBuilder.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC) {
                stringBuilder.append("DESC");
            } else {
                stringBuilder.append("ASC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(INDEX_ARTISTS_ID));
                artist.setName(resultSet.getString(INDEX_ARTISTS_NAME));
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

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
//        SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id
//        WHERE artists.name = "Pink Floyd" ORDER BY albums.name COLLATE NOCASE ASC
        StringBuilder stringBuilder = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        stringBuilder.append(artistName);
        stringBuilder.append("\"");

        if(sortOrder != ORDER_BY_NONE) {
            stringBuilder.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if(sortOrder == ORDER_BY_DESC) {
                stringBuilder.append("DESC");
            } else {
                stringBuilder.append("ASC");
            }
        }

        System.out.println("SQL statement = " + stringBuilder.toString());

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {

            List<String> albums = new ArrayList<>();
            while (resultSet.next()) {
                albums.add(resultSet.getString(1));
            }

            return albums;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

