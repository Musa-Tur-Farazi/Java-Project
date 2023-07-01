package com.example.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MostRecentMovies {

    public static List<Movie> findByMostRecentMovies(List<Movie> movies) {
        //  System.out.print("      Enter Production Company : ");

        List<Movie> templist = new ArrayList<>();


        templist.addAll(movies);


        int len = templist.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (templist.get(i).getYearOfRelease() < templist.get(j).getYearOfRelease()) {
                    Collections.swap(templist,i, j);
                }
            }
        }
        return templist;
    }
}




