package game;

import javafx.application.Application;
import javafx.stage.Stage;

import static game.Controller.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameScope.primaryStage = primaryStage;
        primaryStage.setTitle("The Game");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
