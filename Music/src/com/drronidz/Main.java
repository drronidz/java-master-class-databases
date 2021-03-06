package com.drronidz;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 6/7/2021 , 
    CREATED ON : 1:59 PM
*/

import com.drronidz.model.Artist;
import com.drronidz.model.DataSource;
import com.drronidz.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if(!dataSource.open()) {
            System.out.println("Can't open DataSource");
            return;
        }
        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_NONE);
        if(artists == null) {
            System.out.println("No artists!");
            return;
        }

        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        //
        System.out.println("------------------------------------------------------");
        //

        List<String> albumsForArtist =
                dataSource.queryAlbumsForArtist("Carole King" , DataSource.ORDER_BY_ASC);

        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        System.out.println("------------------------------------------------------");

        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Heartless", DataSource.ORDER_BY_ASC);
        if(songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());
        }
        System.out.println("------------------------------------------------------");

        dataSource.querySongsMetaData();

        System.out.println("------------------------------------------------------");

        int count = dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        System.out.println("------------------------------------------------------");

        dataSource.createViewForSongArtists();

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter a song title: ");
//        String title = scanner.nextLine();
//
//        songArtists = dataSource.querySongInfoView(title);
//        if(songArtists.isEmpty()) {
//            System.out.println("Could't find the artist for the song");
//        }
//
//        for (SongArtist songArtist : songArtists) {
//            System.out.println("FROM VIEW - Artist name = " + songArtist.getArtistName() +
//                    " Album name = " + songArtist.getAlbumName() +
//                    " Track number = " + songArtist.getTrack());
//        }

        System.out.println("------------------------------------------------------");
        dataSource.insertSong("Touch of Grey", "Grateful Dead", "In The Dark", 1);
//        dataSource.insertSong("Like A Rolling Stone", "Bob Dylan", "Bob Dylan's Greatest Hits",5);
        dataSource.close();
    }
}
