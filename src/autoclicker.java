/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class autoclicker extends Application
{
    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.initStyle(StageStyle.DECORATED);
        setPrimaryStage(stage);
        setPrimaryScene(scene);
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private void setPrimaryStage(Stage stage)
    {
        autoclicker.stage = stage;
    }

    public static Stage getMainStage()
    {
        return autoclicker.stage;
    }

    private void setPrimaryScene(Scene scene)
    {
        autoclicker.scene = scene;
    }

    public static Scene getMainScene()
    {
        return autoclicker.scene;
    }
}