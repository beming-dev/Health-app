package org.techtown.realapp;

public class Ex {
    private int choosed;
    private String name;


    Ex(int choosed, String name){
        this.choosed = choosed;
        this.name = name;
    }

    Ex(String name){
        this.choosed = 0;
        this.name = name;
    }

    void choice(){ this.choosed = 1; }

    void unchoice() {
        this.choosed = 0;
    }

    void putChoosed(int choosed){
        this.choosed = choosed;
    }

    void putName(String name){
        this.name = name;
    }

    int getChoosed(){
        return choosed;
    }

    String getName(){
        return name;
    }

    void init(){
        this.choosed =0;
        this.name = null;
    }
}
