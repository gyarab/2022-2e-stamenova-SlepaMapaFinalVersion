package com.example.rp_slepamapa.model;

public class Question {
    private String cityName;
    private double x;
    private double y;

    public Question(String cityName, double x, double y) {
        this.cityName = cityName;
        this.x = x;
        this.y = y;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
