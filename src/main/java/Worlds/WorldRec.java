package Worlds;

import Helpers.coordinates;
import Organisms.Organism;
import UI.Console;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class WorldRec extends World{
    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private final int xOffset = 100;
    private final int yOffset = 100;
    private final int RECSIZE = 40;

    public WorldRec(){
        super();
        for(int i = 0; i < getSIZE(); i++)
            for(int j = 0; j < getSIZE(); j++){
                rectangles.add(
                        new Rectangle(xOffset + j * RECSIZE, yOffset + i * RECSIZE, RECSIZE, RECSIZE)
                );
            }
    }

    public int getRECSIZE(){
        return RECSIZE;
    }

    @Override
    public coordinates[] getAllNeighbourCoordinates(coordinates coords) {
        coordinates N = new coordinates(coords.x, coords.y + 1);
        coordinates E = new coordinates(coords.x + 1, coords.y);
        coordinates S = new coordinates(coords.x, coords.y - 1);
        coordinates W = new coordinates(coords.x - 1, coords.y);
        coordinates[] toReturn = {N, E, S, W};
        return toReturn;
    }

    @Override
    public coordinates getNeighbourCoordinates(coordinates coords) {
        Random generator = new Random();
        coordinates[] toChooseFrom = getAllNeighbourCoordinates(coords);
        int indexToReturn = generator.nextInt(0,4);
        return toChooseFrom[indexToReturn];
    }

    @Override
    public void drawWorld(Console console){
        Graphics g = console.getPanel().getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        for (Rectangle rec: rectangles){
            g2d.setColor(Color.black);
            g2d.drawRect(rec.x, rec.y, rec.width, rec.height);
            g2d.setColor(Color.white);
            g2d.fillRect(rec.x, rec.y, rec.width, rec.height);
        }

        for (Organism org: organisms){
            if(org != null)
                org.draw(console);
        }
    }

    @Override
    public coordinates getCoordsToDrawOn(coordinates coords) {
        return new coordinates(xOffset + coords.x *RECSIZE, yOffset + coords.y * RECSIZE);
    }

    @Override
    public boolean isClickedFigureCoordinates(Point point) {
        if(point == null)
            return false;

        for(int i = 0; i < rectangles.size(); i++){
            if(rectangles.get(i).contains(point)){
                return true;
            }
        }
        return false;
    }

    @Override
    public coordinates getClickedFigureCoordinates(Point point){
        for(int i = 0; i < rectangles.size(); i++){
            if(rectangles.get(i).contains(point)){
                return new coordinates(i % getSIZE(), i / getSIZE());
            }
        }
        return null;
    };
}
