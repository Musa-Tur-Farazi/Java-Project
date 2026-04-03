package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import java.awt.Desktop;

import static com.example.demo1.MyApplication.movielist;
import static com.example.demo1.MyApplication.server;

public class MovieListController implements Initializable{

    public ListView<String> list;
    public TextField titleField;
    public TextField yearField;
    public TextField runningField;
    public TextField watchNameField;

    private Stage stage;
    public TableColumn<Movie,String> title;
    public TableColumn<Movie,Integer> release;
    public TableColumn<Movie,String> genre;
    public TableColumn<Movie,Integer> runningTime;
    public TableColumn<Movie, Long> budget;

    public TableColumn<Movie,Long> revenue;
    public TableColumn<Movie,Long> profit;
    public Label titleLabel;
    public TableView table;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Thread(()-> {
            while (true) {

                try {
                    Object data = server.read();
                    DataWrapper dataWrapper = (DataWrapper) data;

                    if (dataWrapper.command.equals("Transfer")) {
                        movielist.add((Movie)dataWrapper.data);
                        refresh(movielist);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        titleLabel.setText(StartMenuController.name);
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        release.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        runningTime.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
        budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        revenue.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));

        table.getItems().addAll(movielist);

    }


    public void onMostRecentMoviesClick(ActionEvent event) {
        table.refresh();
        List<Movie> movies = MostRecentMovies.findByMostRecentMovies(movielist);
        table.getItems().clear();
        table.getItems().addAll(movies);

    }


    public void onMostProfitedMoviesClick(ActionEvent event) {
        table.refresh();
        List<Movie>movies = MostProfitedMovies.findByMostProfitedMovies(movielist);
        table.getItems().clear();
        table.getItems().addAll(movies);
    }

    public void onMostRevenuedMoviesClick(ActionEvent event) {
        table.refresh();
        List<Movie> movies = MostRevenuedMovies.findByMostRevenuedMovie(movielist);
        table.getItems().clear();
        table.getItems().addAll(movies);

    }

    public void onMostRunningTimeClick(ActionEvent event) {
        table.refresh();
        List<Movie> movies = MostRunningTime.findByMostRunningTime(movielist);
        table.getItems().clear();;
        table.getItems().addAll(movies);

    }

    public void onMostBudgetClick(ActionEvent event) {
        table.refresh();
        List<Movie> movies = MostBudgetMovies.findByMostBudget(movielist);
        table.getItems().clear();
        table.getItems().addAll(movies);
    }


    public void onBackClick(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartMenu.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("IMDB v1.0");
        stage.setScene(new Scene(root, 905,510));
        stage.show();
        refresh(movielist);
    }

    public void onSearchClick(ActionEvent event) {
        String title = titleField.getText();
        table.refresh();
        Movie movie = TitleFind.findByTitle(title,movielist);
        table.getItems().clear();
        table.getItems().add(movie);

    }
    public void onSearchTwoClick(ActionEvent event) {
        int year = Integer.parseInt(yearField.getText());
        table.refresh();
        List<Movie> movies = ReleaseFind.findByRelease(year,movielist);
        table.getItems().clear();
        table.getItems().addAll(movies);
    }

    public void onSearchThreeClick(ActionEvent event) {
        int year = Integer.parseInt(runningField.getText());
        table.refresh();
        List<Movie> movies = RunningFind.findByRunningTime(year,movielist);
        table.getItems().clear();
        table.getItems().addAll(movies);
    }

    public void onAddMovieClick(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddMovie.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("IMDB v1.0");
        stage.setScene(new Scene(root, 905,510));
        stage.show();

    }

    public void onTransferClick(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Transfer.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("IMDB v1.0");
        stage.setScene(new Scene(root, 905,510));
        stage.show();


    }
    public void onUndoClick(ActionEvent event) {
        FileOperations.fileoperate(StartMenuController.name);
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MovieList.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("IMDB v1.0");
        stage.setScene(new Scene(root, 905,510));
        stage.show();
    }
    public void onExitClick(ActionEvent event) {
        System.exit(0);
    }

    public void refresh(List<Movie> movieList){
        titleLabel.setText(StartMenuController.name);
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        release.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        runningTime.setCellValueFactory(new PropertyValueFactory<>("runningTime"));
        budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        revenue.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));

        table.getItems().clear();
        table.getItems().addAll(movieList);

    }
}


