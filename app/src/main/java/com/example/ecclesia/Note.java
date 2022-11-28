package com.example.ecclesia;

public class Note {
    private int id;
    private String content;

    public  int getId() {
        return id;
    }
    public  void setId(int id){
        this.id = id;
    }


    public String getContent(){
        return  content;
    }
    public  void setContent(String content){
        this.content = content;
    }

    public void set(int id, String content){
        this.id = id;
        this.content = content;
    }
}
