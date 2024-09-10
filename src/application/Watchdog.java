package application;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Watchdog implements NativeKeyListener {

    public static int pauseButton = NativeKeyEvent.VC_ESCAPE;
    public static int startButton = NativeKeyEvent.VC_P;

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == pauseButton) {
            Main.clicker.Disable();
        }
        else if (e.getKeyCode() == startButton) {
            Main.clicker.Enable();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    public static void Init(){
        try {
            // Register JNativeHook
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("There was a problem registering the native hook.");
            e.printStackTrace();
            System.exit(1);
        }

        // Add the key listener to the GlobalScreen
        GlobalScreen.addNativeKeyListener(new Watchdog());
    }
}
