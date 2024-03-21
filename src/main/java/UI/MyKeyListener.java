package UI;

import java.awt.*;
import java.awt.event.*;

public class MyKeyListener extends KeyAdapter implements KeyListener{
    private int lastClickedKey;
    Console console;
    public MyKeyListener(Console console){
        this.console = console;
    }

    public int getLastClickedKey() {
        return lastClickedKey;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        console.frame.setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        lastClickedKey = e.getKeyCode();
        console.frame.setFocusable(true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        console.frame.setFocusable(true);
    }
}
