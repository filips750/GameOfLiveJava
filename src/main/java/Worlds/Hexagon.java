package Worlds;

import java.awt.*;

public class Hexagon{
    private Point center;
    private int sideLength;
    private Color color;
    private int[]xPoints;
    private int[]yPoints;

    public Hexagon(Point center, int sideLength, Color color) {
        this.center = center;
        this.sideLength = sideLength;
        this.color = color;

        xPoints = new int[6];
        yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            double angle_deg = 60 * i - 30;
            double angle_rad = Math.PI / 180 * angle_deg;
            xPoints[i] = (int) (center.x + sideLength * Math.cos(angle_rad));
            yPoints[i] = (int) (center.y + sideLength * Math.sin(angle_rad));
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(color);
        g2d.drawPolygon(xPoints, yPoints, 6);
    }

    public void draw(Graphics g, Color fillColor) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(fillColor);
        g2d.fillPolygon(xPoints, yPoints, 6);
    }

    public boolean contains(Point point){
        if(point != null){
            Polygon tempPolygon = new Polygon(xPoints, yPoints, 6);
            return tempPolygon.contains(point);
        }
        return false;
    }
}
