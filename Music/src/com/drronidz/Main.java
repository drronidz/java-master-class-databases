package com.drronidz;/*
    CREATED BY : ABD EL HALIM
    PROJECT NAME : {IntelliJ IDEA}
    CREATED ON : 6/7/2021 , 
    CREATED ON : 1:59 PM
*/

import com.drronidz.model.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if(!dataSource.open()) {
            System.out.println("Can't open DataSource");
            return;
        }
        dataSource.close();
    }
}
