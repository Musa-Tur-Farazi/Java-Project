package com.example.demo1;

import javafx.scene.chart.PieChart;

import java.io.Serializable;

public class DataWrapper implements Serializable {

    public String command;
    public Object data;
    public String name;
    public Object data2;

    public DataWrapper(String command){
        this.command = command;
    }
    public DataWrapper(String command,Object data){
        this.command = command;
        this.data = data;
    }
    public DataWrapper(String command,Object data,Object data2){
        this.command = command;
        this.data = data;
        this.name = name;
        this.data2 = data2;
    }

}
