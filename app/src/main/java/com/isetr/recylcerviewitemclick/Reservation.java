package com.isetr.recylcerviewitemclick;

import java.io.Serializable;

public class Reservation implements Serializable {
    private int id;
    private String username;
    private String Startday;
    private  String Endday;



    public Reservation(String username, String startday, String endday) {
        this.username = username;
        Startday = startday;
        Endday = endday;
    }

    public Reservation(int id, String username, String startday, String endday) {
        this.id = id;
        this.username = username;
        Startday = startday;
        Endday = endday;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getStartday() {
        return Startday;
    }

    public String getEndday() {
        return Endday;
    }
}
