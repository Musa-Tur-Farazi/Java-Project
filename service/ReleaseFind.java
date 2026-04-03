package com.example.demo1;

import java.util.ArrayList;
import java.util.List;

public class ReleaseFind {

    public static List<Movie> findByRelease(int year,List<Movie>movielist){
        List<Movie>templist = new ArrayList<>();
        for(Movie m : movielist){
            if(m.getYearOfRelease()==year){
                templist.add(m);
            }
        }
        return templist;
    }
}
