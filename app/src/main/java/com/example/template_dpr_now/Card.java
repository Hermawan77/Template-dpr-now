package com.example.template_dpr_now;

public class Card {
    private String title;
    private String info;

    public Card(String title, String info){
        this.title = title;
        this.info = info;
    }

    String getTitle(){
        return title;
    }

    String getInfo(){
        return info;
    }
}

