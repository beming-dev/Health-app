package org.techtown.realapp;

public class Ex {
    private int choosed;
    private int is_written;
    private String name;


    Ex(int choosed, String name){
        this.choosed = choosed;
        this.name = name;
        this.is_written = 0;
    }

    Ex(String name){
        this.choosed = 0;
        this.is_written = 0;
        this.name = name;
    }

    void written() {this.is_written = 1;}

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
    int getwritten(){
        return is_written;
    }

    String getName(){
        return name;
    }

    void init(){
        this.is_written = 0;
        this.choosed =0;
        this.name = null;
    }
}
