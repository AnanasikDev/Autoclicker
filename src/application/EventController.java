package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
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
            float rand = newValue.floatValue();
            Main.clicker.setRandomness(rand);
            System.out.println(rand);
        });

//        XYChart.Series<Number, Number> cpsData = new XYChart.Series();
//        cpsData.getData().add(new XYChart.Data<>(1, 2));
//        cpsGraph.getData().add(cpsData);
    }

    @FXML
    private Button testAreaBtn;
    @FXML
    private Button toggleBtn;
    @FXML
    private Slider randomnessSlider;
    @FXML
    private Slider cpsSlider;

//    @FXML
//    private LineChart<Number, Number> cpsGraph;

    public void toggle(ActionEvent e){
        if (Main.clicker.getState())
        {
            toggleBtn.setText("Enable");
            Main.clicker.Disable();
        }
        else
        {
            toggleBtn.setText("Disable");
            Main.clicker.Enable();
        }
        System.out.println("Toggled to " + Main.clicker.getState());
    }

    public void onTestAreaClick(ActionEvent e){
        Stats.clicks++;
        testAreaBtn.setText(Stats.clicks + " clicks");
    }
}
