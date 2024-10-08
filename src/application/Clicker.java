package application;

import java.awt.Robot;
import java.util.Random;

public class Clicker implements Runnable {

    private long prevMs;
    private int deltanext = 250;
    private boolean isEnabled = false;
    private int defaultStartDelay = 1000;
    private int targetCPS = 10;
    private float randomness = 0;
    private float skipClickChance = 0f;
    private float perlinNoiseFactor = 0f;

    // The key to be autoclicked. LeftMouseButton (1024) by default
    private int actionKey = 1024;

    private final Random random;
    private final NoiseGenerator perlinGenerator;
    private Robot robot;

    public Clicker(){
        random = new Random();
        perlinGenerator = new NoiseGenerator(1);
    }

    public void setTargetCPS(int cps){
        targetCPS = cps;
    }

    public void setPerlinNoiseFactor(float fac){
        perlinNoiseFactor = fac;
    }

    public void setSkipClickChance(float chance){
        skipClickChance = chance;
    }

    public void setRandomness(float rand){
        randomness = rand;
    }

    public void setActionKey(int key){
        actionKey = key;
    }

    public boolean isActive(){
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
            if (robot == null) robot = new Robot();

            if (actionKey == 1024 || actionKey == 2048 || actionKey == 4096){
                robot.mousePress(actionKey);
                robot.mouseRelease(actionKey);
            }
            else{
                System.out.println("Keyboard click key:(%s) is not supported.".formatted(actionKey));
            }
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
            deltanext = (int) ((1000 / targetCPS) *
            (
                                1f +
                                perlinNoiseFactor * (
                                        (perlinGenerator.noise((double) System.currentTimeMillis() /12) + 0.15f) -
                                        (perlinGenerator.noise((double) System.currentTimeMillis() /50)) -
                                        Math.pow(perlinGenerator.noise((double) System.currentTimeMillis() /180), 5)) +
                                randomness * (random.nextFloat() - 0.5f)
            ));
            if (deltanext < 0)
                deltanext = Math.abs(random.nextInt() % 200);
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
