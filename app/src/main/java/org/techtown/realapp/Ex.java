package org.techtown.realapp;

public class Ex {
    private int choosed;
    private int number_of_times;
    private int set;
    private int restTime;
    private String name;

    Ex(String name){
        this.choosed = 0;
        this.name = name;
        this.number_of_times = Constants.DEFAULT_NUMBER;
        this.set = Constants.DEFAULT_SET;
        this.restTime = Constants.DEFAULT_REST;
    }

    void choice(){ this.choosed = 1; }
    void unchoice() {
        this.choosed = 0;
        this.set = Constants.DEFAULT_SET;
        this.number_of_times = Constants.DEFAULT_NUMBER;
        this.restTime = Constants.DEFAULT_REST;
    }

    String getName(){return name;}
    int getChoosed(){return choosed;}
    int getNumber_of_times(){return this.number_of_times;}
    int getSet(){return this.set;}
    int getRestTime(){return this.restTime;}

    void setNumber_of_times(int time){this.number_of_times = time;}
    void setSet(int set){this.set = set;}
    void setRestTime(int Rtime){this.restTime = Rtime;}
}
