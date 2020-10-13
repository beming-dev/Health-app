package org.techtown.realapp;

public class Ex {
    private int choosed;
    private String name;
    private int number_of_times;
    private int set;
    private int restTime;

    Ex(String name){
        this.choosed = 0;
        this.name = name;
        this.number_of_times = 10;
        this.set = 3;
        this.restTime = 10;
    }

    void choice(){ this.choosed = 1; }

    void unchoice() {
        this.choosed = 0;
    }

    int getChoosed(){
        return choosed;
    }

    String getName(){
        return name;
    }

    int getNumber_of_times(){return this.number_of_times;}
    int getSet(){return this.set;}
    int getRestTime(){return this.restTime;}

    void setNumber_of_times(int time){this.number_of_times = time;}
    void setSet(int set){this.set = set;}
    void setRestTime(int Rtime){this.restTime = Rtime;}

    void putChoosed(int choosed){
        this.choosed = choosed;
    }

    void putName(String name){
        this.name = name;
    }

    void init(){
        this.choosed =0;
        this.name = null;
    }
}
