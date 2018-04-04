package model.pojo;

import java.util.Date;

public class Tour {
    int  id;
    String name;
    int price;
    Date date;
    String description;

    public Tour(int id, String name, int price, Date date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }
    public Tour(int id, String name, int price, Date date, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public Date getDate(){
        return date;
    }
    public String getDescription(){
        return description;
    }
}
