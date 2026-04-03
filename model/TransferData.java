package com.example.demo1;

import java.io.Serializable;

public class TransferData implements Serializable {

    public String receiver;
    public Object data;
    public String sender;

    public TransferData(String sender,Object data,String receiver){
        this.sender = sender;
        this.data = data;
        this.receiver = receiver;
    }
}
