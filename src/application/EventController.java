package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.HashMap;
import java.util.Map;

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

        toggleBtn.getStyleClass().add("toggleBtn-disabled");

        //XYChart.Series<Number, Number> series = new XYChart.Series<>();
        //cpsData.setData(FXCollections.observableList(new XYChart.Data<Float, Float>(1.f,2.f)));
        //cpsGraph.getData().add(series);
    }

    Map<Float, Float> cpsDataDict = new HashMap<>();

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
    public LineChart<Number, Number> cpsGraph;

    public void toggle(ActionEvent e){
        if (Main.clicker.getState())
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
        String format = "CPS: %s | Total Clicks: %s";
        testAreaBtn.setText(format.formatted(0, Stats.clicks));
    }
}
