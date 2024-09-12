package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

public class EventController {

    public EventController(){
        Main.eventController = this;
    }

    public void Init(){
        cpsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int cps = newValue.intValue();
            Main.clicker.setTargetCPS(cps);
            System.out.println(cps);
        });

        randomnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            float rand = newValue.floatValue() / 100.0f;
            Main.clicker.setRandomness(rand);
            System.out.println(rand);
        });

        skipChanceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            float skipChance = newValue.floatValue() / 100.0f;
            Main.clicker.setSkipClickChance(skipChance);
        });

        perlinNoiseSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            float perlinFactor = newValue.floatValue() / 100.0f;
            Main.clicker.setPerlinNoiseFactor(perlinFactor);
        });

        startKeyCombo.getSelectionModel().select(46);
        stopKeyCombo.getSelectionModel().select(0);
        actionKeyCombo.getSelectionModel().select(0);

        // set default style for toggleBtn
        toggleBtn.getStyleClass().add("toggleBtn-disabled");
    }

    @FXML
    private Button testAreaBtn;
    @FXML
    private Button toggleBtn;
    @FXML
    private Slider randomnessSlider;
    @FXML
    private Slider cpsSlider;
    @FXML
    private Slider skipChanceSlider;
    @FXML
    private Slider perlinNoiseSlider;
    @FXML
    private ComboBox<String> startKeyCombo;
    @FXML
    private ComboBox<String> stopKeyCombo;
    @FXML
    private ComboBox<String> actionKeyCombo;

    public void toggle(ActionEvent e){
        if (Main.clicker.isActive())
        {
            Main.clicker.Disable();
            GUIDisableClicker();
        }
        else
        {
            Main.clicker.Enable();
            GUIEnableClicker();
        }
    }

    public void GUIEnableClicker(){
        toggleBtn.setText("ENABLED");
        toggleBtn.getStyleClass().remove("toggleBtn-disabled");
        toggleBtn.getStyleClass().add("toggleBtn-enabled");
    }

    public void GUIDisableClicker(){
        toggleBtn.setText("DISABLED");
        toggleBtn.getStyleClass().remove("toggleBtn-enabled");
        toggleBtn.getStyleClass().add("toggleBtn-disabled");
    }

    public void onTestAreaClick(ActionEvent e){
        Stats.clicks++;
        String format = "Total Clicks: %s";
        testAreaBtn.setText(format.formatted(Stats.clicks));
    }

    public void onStartKeyChanged(ActionEvent event) {
        Watchdog.startButton = Main.parseKeyName(startKeyCombo.getValue());
        System.out.println(startKeyCombo.getValue());
    }

    public void onStopKeyChanged(ActionEvent event) {
        Watchdog.stopButton = Main.parseKeyName(stopKeyCombo.getValue());
        System.out.println(stopKeyCombo.getValue());
    }

    public void onActionKeyChanged(ActionEvent event) {
        Main.clicker.setActionKey(Main.parseKeyName(actionKeyCombo.getValue()));
        System.out.println(actionKeyCombo.getValue());
    }
}
