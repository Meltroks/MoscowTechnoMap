package com.meltroks.moscowtechnomap;

public class Event {
    String name;
    Integer date;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getDate() {return date;}

    public void setDate(Integer date){
        if(date < 0) date = 0;
        this.date = date;
    }

    public Event(String name, Integer date){
        this.name = name;
        this.date = date;
    }

}
