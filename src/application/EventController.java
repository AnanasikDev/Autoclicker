package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EventController {

    @FXML
    private Button testAreaBtn;

    @FXML
    private Button toggleBtn;

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
