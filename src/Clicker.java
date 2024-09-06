import java.awt.Robot;
import java.awt.event.InputEvent;
import java.sql.Timestamp;

public class Clicker implements Runnable {

    private long prevMs;
    private int deltanext = 100;

    public void simulateRightClick() {
        try {
            // Create a Robot instance to simulate the click
            Robot robot = new Robot();

            // Simulate right mouse button press and release
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);  // Right button down
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);  // Right button up
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Update(){
        if (System.currentTimeMillis() - prevMs >= deltanext){
            prevMs = System.currentTimeMillis();

            getNextClick();
            deltanext = 100;
        }
    }

    private void getNextClick(){
        simulateRightClick();
    }

    @Override
    public void run() {
        try {
            // Simulate some action in a separate thread (e.g., sleeping)
            while (true) {
                //System.out.println("Clicker working...");
                //Thread.sleep(2000); // Sleep for 2 seconds
                Update();
                Thread.sleep(deltanext);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
