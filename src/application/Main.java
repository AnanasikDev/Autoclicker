package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
        Watchdog.Init();
        Watchdog.onForceEnabled.Subscribe(new Delegate() {
            @Override
            public void execute() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.clicker.Enable();
                        Main.eventController.GUIEnableClicker();
                    }
                });
                System.out.println("enable");
            }
        });
        Watchdog.onForceDisabled.Subscribe(new Delegate() {
            @Override
            public void execute() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.clicker.Disable();
                        Main.eventController.GUIDisableClicker();
                    }
                });
                System.out.println("disable");
            }
        });

        stage.initStyle(StageStyle.DECORATED);
        setPrimaryStage(stage);
        setPrimaryScene(scene);
        Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));

        scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);

        stage.setScene(scene);
        stage.show();

        // Set minimum width and height for the window
        stage.setMinWidth(650);
        stage.setMinHeight(560);
        stage.setWidth(660);
        stage.setHeight(560);

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