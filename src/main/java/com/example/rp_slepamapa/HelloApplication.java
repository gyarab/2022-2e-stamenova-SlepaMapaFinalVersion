package com.example.rp_slepamapa;

import com.example.rp_slepamapa.model.Question;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HelloApplication extends Application {
    private static List<Question> questions = new LinkedList<>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        var controller = (HelloController)fxmlLoader.getController();
        controller.setQuestions(questions);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    private static void readQuestions(String path) throws IOException {
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

    public static void main(String[] args) throws IOException {
        readQuestions("src/main/resources/com/example/rp_slepamapa/CeskaRepublika.txt");
        launch();
    }
}