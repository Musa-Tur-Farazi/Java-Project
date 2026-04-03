package com.example.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MostProfitedMovies {

    public static List<Movie> findByMostProfitedMovies(List<Movie> movielist) {

        List<Movie> templist = new ArrayList<>();
        templist.addAll(movielist);
        int len = movielist.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (movielist.get(i).getProfit() < movielist.get(j).getProfit()) {
                    Collections.swap(templist, i, j);
                }
            }
        }
        return templist;
    }

}
