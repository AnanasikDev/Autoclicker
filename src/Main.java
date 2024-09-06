import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        Clicker clicker = new Clicker();
        Stats stats = new Stats();

        // Create a new JFrame (the window)
        JFrame frame = new JFrame("Empty Window");

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        frame.setSize(400, 300);

        // Create a JButton
        JButton button = new JButton("Begin autoclick");
        JButton cpstest = new JButton("0 cp/s");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // to sleep 10 seconds
                    clicker.Enable();
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    // recommended because catching InterruptedException clears interrupt flag
                    Thread.currentThread().interrupt();
                    // you probably want to quit if the thread is interrupted
                    return;
                }
            }
        });

        cpstest.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stats.clicks++;
                cpstest.setText(stats.clicks + " clicks");
            }
        });

        // Add the button to the frame
        frame.add(button, BorderLayout.NORTH); // Add to the center of the frame
        frame.add(cpstest, BorderLayout.SOUTH); // Add to the center of the frame

        // Make the window visible
        frame.setVisible(true);

        Thread clickerThread = new Thread(clicker);
        clickerThread.start();
    }
}
