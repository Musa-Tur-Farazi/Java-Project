package com.example.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MostRunningTime {

    public static List<Movie> findByMostRunningTime(List<Movie>movielist){

        List<Movie>templist = new ArrayList<>();
        templist.addAll(movielist);

        int len = templist.size();
        for(int i=0;i<len-1;i++) {
            for (int j = i + 1; j < len; j++) {
                if (templist.get(i).getRunningTime() < templist.get(j).getRunningTime()) {
                    Collections.swap(templist, i, j);
                }
            }
        }
        return templist;
    }
}

