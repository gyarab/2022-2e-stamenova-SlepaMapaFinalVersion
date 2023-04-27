package com.example.rp_slepamapa;

import com.example.rp_slepamapa.model.Question;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class HelloController {
    private List<Question> questions;
    private double x;
    private double y;
    private int points;
    private Thread timeCounterThread;
    private boolean isQuestionActive = false;
    private Function<Double, Double> convertWidth;
    private Function<Double, Double> convertLength;
    private int numberOfQuestions;
    private int maxPoints;
    @FXML
    private Label cityNameLabel;
    @FXML
    private Label pointsLabel;
    @FXML
    public Canvas canvas;
    @FXML
    private Button nextButton;
    @FXML
    private Label timeCounterLabel;
    @FXML
    public ImageView imageView;
    @FXML
    public StackPane stackPane;
    @FXML
    private Label currentStatusLabel;
    @FXML
    protected void onNextButtonClick() throws IOException {
        if(numberOfQuestions == 0) {
           goToMainMenu();
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        askQuestion();
    }
    @FXML
    protected void onMenuButtonClick() throws IOException {
        goToMainMenu();
    }
    private void goToMainMenu() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) canvas.getScene().getWindow();
            stage.setScene(scene);
            stage.setHeight(260);
            stage.setWidth(370);
            stage.show();
    }

    private void askQuestion() {
        generateQuestion();
        isQuestionActive = true;
        nextButton.setDisable(true);
        timeCounterThread = getTimeCounterThread();
        timeCounterThread.start();
        currentStatusLabel.setText("Select a place!");
        numberOfQuestions --;
    }

    public void onClick(MouseEvent mouseEvent) {
        if (isQuestionActive == true) {
            timeCounterThread.interrupt();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillOval(mouseEvent.getX() - 5, mouseEvent.getY() - 5, 10, 10);

            //Vypočítání vzdálenosti a přirazení bodů
            double distance = Math.sqrt(Math.pow(mouseEvent.getX() - x, 2) + Math.pow(mouseEvent.getY() - y, 2));
            int accuracy = 0;
            if (distance < 10) {
                points += 10;
                pointsLabel.setText("You have " + points + " points");
                currentStatusLabel.setText("You earned 10 points!");
            } else if (distance < 20) {
                points += 5;
                pointsLabel.setText("You have " + points + " points");
                currentStatusLabel.setText("You earned 5 points!");
            }
            else{
                currentStatusLabel.setText("Too far, you don't get any points!");
            }
            showResult();
        }
    }
    //Označení správného města
    private void showResult() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(x - 1, y - 1, 2, 2);
        gc.setStroke(Color.GREEN);
        gc.strokeOval(x - 10, y - 10, 20, 20);
        gc.setStroke(Color.ORANGE);
        gc.strokeOval(x - 20, y - 20, 40, 40);
        if(numberOfQuestions == 0) {
            nextButton.setText("Back to main menu");
            currentStatusLabel.setText("Game over! " +
                    "You earned " + points + " points from maximum of " + maxPoints);

        }
        isQuestionActive = false;
        nextButton.setDisable(false);
    }

    //Určení počtu otázek a maximálního počtu bodů
    public void setQuestions(List<Question> questions, int numberOfQuestions) {
        this.questions = questions;
        this.numberOfQuestions = numberOfQuestions < questions.size() ? numberOfQuestions : questions.size();
        this.maxPoints = this.numberOfQuestions*10;
    }

    public void setConvertWidth(Function<Double, Double> convertWidth) {
        this.convertWidth = convertWidth;
    }

    public void setConvertLength(Function<Double, Double> convertLength) {
        this.convertLength = convertLength;
    }

    private void generateQuestion() {
        Question question = questions.remove(0);
        x = convertWidth.apply(question.getX());
        y = convertLength.apply(question.getY());
        cityNameLabel.setText(question.getCityName());
    }

    public void runGame() {
        points = 0;
        pointsLabel.setText("You have " + points + " points");
        askQuestion();

    }
    //Časovač - 10 sekund na každou otázku
    private Thread getTimeCounterThread() {
        timeCounterLabel.setText("You have " + 10 + " seconds left");
        return new Thread(() -> {
            int counter = 10;
            try {
                while (true) {
                    counter--;
                    int copyOfCounter = counter;
                    Platform.runLater(() -> timeCounterLabel.setText("You have " + copyOfCounter + " seconds left"));
                    if (counter == 0) {
                        break;
                    }
                    Thread.sleep(1000);
                }
                Platform.runLater(() -> currentStatusLabel.setText("Time's up, you don't get any points!"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> showResult());
        });
    }
}