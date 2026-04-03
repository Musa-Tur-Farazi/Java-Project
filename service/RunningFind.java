package com.example.demo1;

import java.util.ArrayList;
import java.util.List;

public class RunningFind {
    public static List<Movie> findByRunningTime(int year, List<Movie>movielist){
        List<Movie>templist = new ArrayList<>();
        for(Movie m : movielist){
            if(m.getRunningTime()==year){
                templist.add(m);
            }
        }
        return templist;
    }
}
