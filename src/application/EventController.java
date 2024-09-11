package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
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
            float rand = newValue.floatValue();
            Main.clicker.setRandomness(rand);
            System.out.println(rand);
        });

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        //cpsData.setData(FXCollections.observableList(new XYChart.Data<Float, Float>(1.f,2.f)));
        cpsGraph.getData().add(series);
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
    public LineChart<Number, Number> cpsGraph;

    public void toggle(ActionEvent e){
        if (Main.clicker.getState())
        {
            DisableClicker();
        }
        else
        {
            EnableClicker();
        }
        System.out.println("Toggled to " + Main.clicker.getState());
    }

    public void EnableClicker(){
        toggleBtn.setText("Disable");
        Main.clicker.Enable();
    }

    public void DisableClicker(){
        toggleBtn.setText("Enable");
        Main.clicker.Disable();
    }

    public void onTestAreaClick(ActionEvent e){
        Stats.clicks++;
        //cpsDataDict.put(String.valueOf(Stats.clicks), (float)Main.clicker.getDelta());
//        cpsDataDict.put(1.f, 2.f);//  (float)Stats.clicks, (float)Main.clicker.getDelta());
//        cpsGraph.getData().clear();
//        XYChart.Series<Number, Number> series = new XYChart.Series<>();
//        // Add the data from the Map to the series
//        for (Map.Entry<Float, Float> entry : cpsDataDict.entrySet()) {
//            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
//        }
//        // Add the series to the chart
//        cpsGraph.getData().add(series);
//        //updateLineChartWithMap(cpsGraph, cpsDataDict);
//        testAreaBtn.setText(Stats.clicks + " clicks");
    }

    // Function to update LineChart values using a Map<T1, T2>
    public <T1, T2> void updateLineChartWithMap(LineChart<T1, T2> lineChart, Map<T1, T2> newValues) {
        // Clear existing data
        lineChart.getData().clear();

        // Create a new series for the updated data
        XYChart.Series<T1, T2> series = new XYChart.Series<>();
        series.setName("Updated Data");

        // Add new data points from the dictionary (map)
        for (Map.Entry<T1, T2> entry : newValues.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Add the series to the LineChart
        lineChart.getData().add(series);
    }
}
