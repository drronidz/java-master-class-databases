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
        List<Artist> artists = dataSource.queryArtists();
        if(artists == null) {
            System.out.println("No artists!");
            return;
        }

        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        dataSource.close();
    }
}
