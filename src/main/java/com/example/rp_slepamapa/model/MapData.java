package com.example.rp_slepamapa.model;

import java.util.function.Function;

public class MapData {
    private String pathToQuestions;
    private String pathToMapImage;
    private int height;
    private int width;
    private Function<Double, Double> convertWidth;
    private Function<Double, Double> convertLength;

    public MapData(String pathToQuestions, String pathToMapImage, int height, int width, Function<Double, Double> convertWidth, Function<Double, Double> convertLength) {
        this.pathToQuestions = pathToQuestions;
        this.pathToMapImage = pathToMapImage;
        this.height = height;
        this.width = width;
        this.convertWidth = convertWidth;
        this.convertLength = convertLength;
    }

    public Function<Double, Double> getConvertWidth() {
        return convertWidth;
    }

    public void setConvertWidth(Function<Double, Double> convertWidth) {
        this.convertWidth = convertWidth;
    }

    public Function<Double, Double> getConvertLength() {
        return convertLength;
    }

    public void setConvertLength(Function<Double, Double> convertLength) {
        this.convertLength = convertLength;
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
