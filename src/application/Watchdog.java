package application;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Watchdog implements NativeKeyListener {

    public static int stopButton = NativeKeyEvent.VC_Z;
    public static int startButton = NativeKeyEvent.VC_BACK_SLASH;

    public static Action onForceEnabled = new Action();
    public static Action onForceDisabled = new Action();

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == stopButton || e.getKeyCode() == 44) {
            onForceDisabled.Invoke();
        }
        else if (e.getKeyCode() == startButton) {
            onForceEnabled.Invoke();
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
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("There was a problem registering the native hook.");
            e.printStackTrace();
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new Watchdog());
    }
}
