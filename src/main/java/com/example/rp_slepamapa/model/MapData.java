package com.example.rp_slepamapa.model;

public class MapData {
    private String pathToQuestions;
    private String pathToMapImage;
    private int height;
    private int width;

    public MapData(String pathToQuestions, String pathToMapImage, int height, int width) {
        this.pathToQuestions = pathToQuestions;
        this.pathToMapImage = pathToMapImage;
        this.height = height;
        this.width = width;
    }

    public String getPathToQuestions() {
        return pathToQuestions;
    }

    public void setPathToQuestions(String pathToQuestions) {
        this.pathToQuestions = pathToQuestions;
    }

    public String getPathToMapImage() {
        return pathToMapImage;
    }

    public void setPathToMapImage(String pathToMapImage) {
        this.pathToMapImage = pathToMapImage;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
