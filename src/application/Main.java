package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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
        stage.getIcons().add(new Image(  Main.class.getResourceAsStream("../icon.png")));
        stage.setTitle("Anti-anti-clicker");
        stage.setMinWidth(650);
        stage.setMinHeight(760);
        stage.setWidth(650);
        stage.setHeight(760);

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

    private static Map<String, Integer> keyCodes = new HashMap<>() {{
        put("ESCAPE", 1);
        put("LEFT MOUSE BUTTON", 1024);
        put("RIGHT MOUSE BUTTON", 4096);
        put("MIDDLE MOUSE BUTTON", 2048);
        put("F1", 59);
        put("F2", 60);
        put("F3", 61);
        put("F4", 62);
        put("F5", 63);
        put("F6", 64);
        put("F7", 65);
        put("F8", 66);
        put("F9", 67);
        put("F10", 68);
        put("F11", 87);
        put("F12", 88);
        put("1", 2);
        put("2", 3);
        put("3", 4);
        put("4", 5);
        put("5", 6);
        put("6", 7);
        put("7", 8);
        put("8", 9);
        put("9", 10);
        put("0", 11);
        put("MINUS", 12);
        put("EQUALS", 13);
        put("BACKSPACE", 14);
        put("TAB", 15);
        put("CAPS_LOCK", 58);
        put("A", 30);
        put("B", 48);
        put("C", 46);
        put("D", 32);
        put("E", 18);
        put("F", 33);
        put("G", 34);
        put("H", 35);
        put("I", 23);
        put("J", 36);
        put("K", 37);
        put("L", 38);
        put("M", 50);
        put("N", 49);
        put("O", 24);
        put("P", 25);
        put("Q", 16);
        put("R", 19);
        put("S", 31);
        put("T", 20);
        put("U", 22);
        put("V", 47);
        put("W", 17);
        put("X", 45);
        put("Y", 21);
        put("Z", 44);
        put("OPEN_BRACKET", 26);
        put("CLOSE_BRACKET", 27);
        put("BACK_SLASH", 43);
        put("SEMICOLON", 39);
        put("QUOTE", 40);
        put("ENTER", 28);
        put("COMMA", 51);
        put("PERIOD", 52);
        put("SLASH", 53);
        put("SPACE", 57);
        put("PAUSE", 3653);
        put("INSERT", 3666);
        put("DELETE", 3667);
        put("HOME", 3655);
        put("END", 3663);
        put("PAGE_UP", 3657);
        put("PAGE_DOWN", 3665);
        put("UP", 57416);
        put("LEFT", 57419);
        put("CLEAR", 57420);
        put("RIGHT", 57421);
        put("DOWN", 57424);
        put("NUM_LOCK", 69);
        put("SEPARATOR", 83);
        put("SHIFT", 42);
        put("CONTROL", 29);
        put("ALT", 56);
    }};

    public static int parseKeyName(String keyName){
        return keyCodes.get(keyName);
    }
}