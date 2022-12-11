package com.example.rp_slepamapa;

import com.example.rp_slepamapa.model.Question;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class HelloController {
    private List<Question> questions;
    private double x;
    private double y;
    @FXML
    private Label cityNameLabel;
    @FXML
    private Canvas canvas;
    @FXML
    protected void onStartButtonClick() {
        generateQuestion();
    }

    public void onClick(MouseEvent mouseEvent) {
        cityNameLabel.setText(mouseEvent.getX() + " : " + mouseEvent.getY());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillOval(mouseEvent.getX()-5, mouseEvent.getY()-5, 10,10);
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;

    }
    private void generateQuestion() {
        Question question = questions.remove(0);
        x = question.getX();
        y = question.getY();
        cityNameLabel.setText(question.getCityName());


    }
}