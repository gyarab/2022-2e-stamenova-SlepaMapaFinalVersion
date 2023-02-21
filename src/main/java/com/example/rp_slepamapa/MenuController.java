package com.example.rp_slepamapa;

import com.example.rp_slepamapa.model.MapData;
import com.example.rp_slepamapa.model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static List<Question> questions = new LinkedList<>();
    private MapData mapDataCz = new MapData("src/main/resources/com/example/rp_slepamapa/CZ.txt", "SlepaMapaCr.jpg", 294,511, x -> (x - 12.09138889)/6.7675 * 511,  y-> (51.05583333 - y)/2.503333333 * 294);
    private MapData mapDataWorld = new MapData("src/main/resources/com/example/rp_slepamapa/World.txt", "SlepaMapaWorld3.jpg", 483,960, x -> 480 + (x/180*480), y -> 242 -(y/90*242));
    @FXML
    private TextField NumberTextField;


    public void runCzMap(ActionEvent event) throws IOException {
        runMap(mapDataCz, event);
    }
    public void runWorldMap(ActionEvent event) throws IOException {
        runMap(mapDataWorld, event);
    }
    private void runMap(MapData mapData, ActionEvent event) throws IOException {
        readQuestions(mapData.getPathToQuestions());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            root = loader.load();
            HelloController helloController = loader.getController();
            int numberOfQuestions = Integer.parseInt(NumberTextField.getText());
            helloController.setQuestions(questions, numberOfQuestions);
            helloController.setConvertWidth(mapData.getConvertWidth());
            helloController.setConvertLength(mapData.getConvertLength());
            helloController.imageView.setFitHeight(mapData.getHeight());
            helloController.imageView.setFitWidth(mapData.getWidth());
            helloController.canvas.setHeight(mapData.getHeight());
            helloController.canvas.setWidth(mapData.getWidth());
            helloController.stackPane.setPrefHeight(mapData.getHeight());
            helloController.stackPane.setPrefWidth(mapData.getWidth());
            Image image = new Image(String.valueOf(getClass().getResource(mapData.getPathToMapImage())));
            helloController.imageView.setImage(image);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(800);
            stage.setWidth(1200);
            stage.setX(100);
            stage.setY(50);
            stage.show();
            helloController.runGame();
    }
    private void readQuestions(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while((line = br.readLine()) != null) {
            String[] data = line.split(" ");
            String cityName = data[0];
            double x = Double.parseDouble(data[1]);
            double y = Double.parseDouble(data[2]);
            Question question = new Question(cityName, x, y);
            questions.add(question);
        }
        Collections.shuffle(questions);

    }
}