package com.drronidz;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 6/7/2021 , 
    CREATED ON : 1:59 PM
*/

import com.drronidz.model.Artist;
import com.drronidz.model.DataSource;

import java.util.List;

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
                dataSource.queryAlbumsForArtist("Iron Maiden" , DataSource.ORDER_BY_ASC);

        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        dataSource.close();
    }
}
