package com.example.demo1;

import javafx.scene.control.CheckBox;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private int yearOfRelease;

    private String genre;


    private int runningTime;
    private String productionCompany;
    private long budget;
    private long revenue;
    private long profit;


    Movie(String title,int yearOfRelease,String genre, int runningTime,String productionCompany, long budget, long revenue,long profit){
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.genre = genre;
        this.runningTime = runningTime;
        this.productionCompany = productionCompany;
        this.budget = budget;
        this.revenue = revenue;
        this.profit = profit;


    }



    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public long getProfit() {
        return profit;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public String getTitle() {
        return title;
    }


    public String getGenre(){ return genre;}



    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }



}
