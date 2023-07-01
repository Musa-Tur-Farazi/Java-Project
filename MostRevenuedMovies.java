package com.example.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MostRevenuedMovies {

    public static List<Movie>findByMostRevenuedMovie(List<Movie>movielist){

        List<Movie>templist = new ArrayList<>();

        templist.addAll(movielist);

        int len = templist.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (templist.get(i).getRevenue() < templist.get(j).getRevenue()) {
                    Collections.swap(templist,i,j);
                }
            }
        }
        return templist;

    }
}
