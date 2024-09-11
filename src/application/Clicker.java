package application;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

public class Clicker implements Runnable {

    private long prevMs;
    private int deltanext = 250;
    private boolean isEnabled = false;
    private int defaultStartDelay = 1000;
    private int targetCPS = 10;
    private float randomness = 0;
    private float skipClickChance = 0f;

    private Random random;

    public Clicker(){
        random = new Random();
    }

    public void setTargetCPS(int cps){
        targetCPS = cps;
    }

    public void setSkipClickChance(float chance){
        skipClickChance = chance;
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
        System.out.println("Enabled");
    }
    public void Disable(){
        isEnabled = false;
        System.out.println("Disabled");
    }

    public void simulateClick() {
        try {
            Robot robot = new Robot();

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);  // Left button down
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  // Left button up
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean Update(){
        if (!isEnabled) return false;

        if (System.currentTimeMillis() - prevMs >= deltanext){
            prevMs = System.currentTimeMillis();

            if (random.nextFloat() > skipClickChance)
                simulateClick();
            deltanext = (1000 / targetCPS) + (int)(randomness * ((random.nextFloat() - 0.5f) * 1000.f / targetCPS));
        }
        return true;
    }

    @Override
    public void run() {
        try {
            while (true) {
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
