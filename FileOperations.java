package com.example.demo1;

import javafx.scene.control.CheckBox;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileOperations {

    private static String INPUT_FILE_NAME = "movies.txt";
    static List<Movie>movieList = new ArrayList<>();

    public static void fileoperate(String name) {

        BufferedReader br = null;
        List<Movie> tempList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        String line;
        while (true) {
            try {
                if ((line = br.readLine()) == null) break;
                else {

                    List<String> splitlist = new ArrayList<>();
                    String[] split_init = line.split(",");
                    Collections.addAll(splitlist, split_init);
                    if (splitlist.size() == 7) {
                        splitlist.add(3, " ");
                        splitlist.add(4, " ");
                    } else if (splitlist.size() == 8) {
                        splitlist.add(4, " ");
                    }
                    String[] split = new String[100];
                    int len = 0;
                    for (String s : splitlist) {
                        split[len++] = s;
                    }
                    String s = split[2] + " " + split[3] + " " + split[4];


                    Movie m = new Movie(split[0], Integer.parseInt(split[1]), s, Integer.parseInt(split[5]), split[6], Integer.parseInt(split[7]), Integer.parseInt(split[8]),Integer.parseInt(split[8])-Integer.parseInt(split[7]));
                    tempList.add(m);
                }


            } catch (IOException e) {
                System.out.println("Fail!");
            }
        }
        int i = 0;
        for (Movie m : tempList) {

            System.out.println(m.getProductionCompany()+ " " +name + " " +i);
            i++;
            if (m.getProductionCompany().equalsIgnoreCase(name)) {
                movieList.add(m);
            }
        }
    }
}
