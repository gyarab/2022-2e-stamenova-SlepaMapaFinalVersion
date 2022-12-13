package com.example.rp_slepamapa;

import com.example.rp_slepamapa.model.Question;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.List;

public class HelloController {
    private List<Question> questions;
    private double x;
    private double y;
    private int points;
    private boolean isQuestionActive = false;
    @FXML
    private Label cityNameLabel;
    @FXML
    private Label pointsLabel;
    @FXML
    private Canvas canvas;
    @FXML
    protected void onStartButtonClick() {
        generateQuestion();
    }
    private void askQuestion() {
        generateQuestion();
        isQuestionActive = true;
    }
    public void onClick(MouseEvent mouseEvent) {
        if (isQuestionActive == true) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillOval(mouseEvent.getX()-5, mouseEvent.getY()-5, 10, 10);

            double distance = Math.sqrt(Math.pow(mouseEvent.getX() - x, 2) + Math.pow(mouseEvent.getY() - y, 2));
            int accuracy = 0;
            if (distance < 10) {
                points += 10;
                pointsLabel.setText("You earned " + points + " points");
            }
            else if (distance < 20) {
                points += 5;
                pointsLabel.setText("You earned " + points + " points");
            }
            gc.setFill(Color.GREEN);
            gc.fillOval(x-1, y-1, 2, 2);
            gc.setStroke(Color.GREEN);
            gc.strokeOval(x-10, y-10, 20, 20);
            gc.setStroke(Color.ORANGE);
            gc.strokeOval(x-20, y-20, 40, 40);

        }
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