package com.example.demo1;

import java.util.List;

public class TitleFind {

    public static Movie findByTitle(String name,List<Movie> movielist){

        for(Movie m : movielist){
            if(m.getTitle().equalsIgnoreCase(name)){
               return m;
            }
        }
        return null;
    }
}
