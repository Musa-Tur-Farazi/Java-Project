package com.example.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private static String INPUT_FILE_NAME = "movies.txt";
    private static List<Movie> servermovielist = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        try {
            String line;
            while ((line = br.readLine()) != null) {
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
                String genre = split[2] + "\r\n" +split[3]+ "\r\n" +split[4];
                long profit = Integer.parseInt(split[8])-Integer.parseInt(split[7]);

                Movie m = new Movie(split[0],Integer.parseInt(split[1]),genre,Integer.parseInt(split[5]),split[6],Integer.parseInt(split[7]),Integer.parseInt(split[8]),profit);

                servermovielist.add(m);
            }
        } catch (IOException e) {
            System.out.println("Cannot open the database!" + e);
        }

         ServerSocket server = new ServerSocket(3000);
         HashMap<String, SocketWrapper> clientMap = new HashMap<>();

         HashMap<String,String> passwordMap = new HashMap<>();
         for(Movie m : servermovielist){
             passwordMap.put(m.getProductionCompany(),m.getProductionCompany().replaceAll("\\s",""));
         }


        while (true) {
            Socket clientSocket = server.accept();
            SocketWrapper client = new SocketWrapper(clientSocket);

            new Thread(() -> {

                try {

                    Object data = client.read();
                    DataWrapper datawrap = (DataWrapper) data;

                    if(datawrap.command.equals("Name")) {
                        String name = (String) datawrap.data;
                        clientMap.put(name, client);
                    }


                    while (true) {
                        data = client.read();
                        DataWrapper dataWrapper = (DataWrapper) data;

                        if (dataWrapper.command.equals("Login")) {
                            String str = (String) dataWrapper.data;
                            System.out.println("Read from client !");
                            List<Movie> movies = new ArrayList<>();
                            for (Movie m : servermovielist) {
                                if (m.getProductionCompany().equalsIgnoreCase(str)) {
                                    movies.add(m);
                                }
                            }
                            for(Movie m : movies){
                                System.out.println("Name - > " + m.getTitle());
                            }
                            client.write(movies);
                            System.out.println("Sent");

                        }
                        else if(dataWrapper.command.equals("Add")){
                            Movie movie = (Movie)dataWrapper.data;
                            servermovielist.add((Movie)dataWrapper.data);
                        }
                        else if(dataWrapper.command.equals("Transfer")){
                            TransferData transferData = (TransferData)dataWrapper.data;
                            Movie movie = (Movie)transferData.data;
                            servermovielist.add(movie);

                            String receiver = transferData.receiver;
                            SocketWrapper receiverClient = clientMap.get(receiver);
                            if(receiverClient!=null){
                                receiverClient.write(new DataWrapper("Transfer",movie));
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }).start();

            System.out.println("Server Ok!");
        }
    }
}


