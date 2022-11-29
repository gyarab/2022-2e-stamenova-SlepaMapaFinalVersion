package com.example.rp_slepamapa;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas canvas;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onClick(MouseEvent mouseEvent) {
        welcomeText.setText(mouseEvent.getX() + " : " + mouseEvent.getY());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillOval(mouseEvent.getX()-5, mouseEvent.getY()-5, 10,10);
    }
}