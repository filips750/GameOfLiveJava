package UI;

import java.awt.*;
import java.awt.event.*;

public class MyMouseListener extends MouseAdapter {
    public Point lastClickedPoint;
    @Override
    public void mouseClicked(MouseEvent event) {
        if ((event.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
            lastClickedPoint = event.getPoint();
        }
    }

    public Point getLastClickedPoint() {
        return lastClickedPoint;
    }
    public void setLastClickedPoint(Point toSet) {
        lastClickedPoint = toSet;
    }
}
