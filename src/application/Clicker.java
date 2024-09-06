package application;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

public class Clicker implements Runnable {

    private long prevMs;
    private int deltanext = 250;
    private boolean isEnabled = false;
    private int defaultStartDelay = 3500;
    private int targetCPS = 20;
    private float randomness = 0;

    private Random random;

    public Clicker(){
        random = new Random();
    }

    public void setTargetCPS(int cps){
        targetCPS = cps;
    }

    public void setRandomness(float rand){
        randomness = rand;
    }

    public boolean getState(){
        return isEnabled;
    }

    public int getDelta(){
        return (int)(System.currentTimeMillis() - prevMs);
    }

    public void Enable(){
        isEnabled = true;
        prevMs = System.currentTimeMillis();
        deltanext = defaultStartDelay;
    }
    public void Disable(){
        isEnabled = false;
    }

    public void simulateClick() {
        try {
            // Create a Robot instance to simulate the click
            Robot robot = new Robot();

            // Simulate right mouse button press and release
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);  // Right button down
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  // Right button up
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean Update(){
        if (!isEnabled) return false;

        if (System.currentTimeMillis() - prevMs >= deltanext){
            prevMs = System.currentTimeMillis();

            simulateClick();
            deltanext = (1000 / targetCPS) + (int)(randomness * (random.nextFloat() * targetCPS / 2f));
        }
        return true;
    }

    @Override
    public void run() {
        try {
            // Simulate some action in a separate thread (e.g., sleeping)
            while (true) {
                //System.out.println("Clicker working...");
                //Thread.sleep(2000); // Sleep for 2 seconds
                if (Update())
                    Thread.sleep(deltanext);
                else
                    Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
