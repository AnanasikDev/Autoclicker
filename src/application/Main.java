package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    private static Stage stage;
    private static Scene scene;
    public static Clicker clicker;

    public static EventController eventController;

    @Override
    public void start(Stage stage) throws Exception
    {
        clicker = new Clicker();

        stage.initStyle(StageStyle.DECORATED);
        setPrimaryStage(stage);
        setPrimaryScene(scene);
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        Thread clickerThread = new Thread(clicker);
        clickerThread.start();

        eventController.Init();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private void setPrimaryStage(Stage stage)
    {
        Main.stage = stage;
    }

    public static Stage getMainStage()
    {
        return Main.stage;
    }

    private void setPrimaryScene(Scene scene)
    {
        Main.scene = scene;
    }

    public static Scene getMainScene()
    {
        return Main.scene;
    }
}