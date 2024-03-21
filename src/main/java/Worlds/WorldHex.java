package Worlds;

import Organisms.Organism;
import UI.Console;
import Helpers.coordinates;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Math.*;
import java.util.Random;

public class WorldHex extends World{
    private ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
    private final int xOffset = 100;
    private final int yOffset = 100;
    private final int HEXSIZE = 21;
    Point centerPoint;
    public WorldHex(){
        super();
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(i%2 == 0)
                    centerPoint = new Point((int) (j * HEXSIZE*Math.sqrt(3) + xOffset),
                                            (int) (i * HEXSIZE*Math.sqrt(3) + yOffset));
                else
                    centerPoint = new Point((int) (j * HEXSIZE*Math.sqrt(3) + HEXSIZE / 2 * Math.sqrt(3) + xOffset),
                                            (int) (i * HEXSIZE*Math.sqrt(3) + yOffset));
                Hexagon hex = new Hexagon(centerPoint, HEXSIZE, Color.black);
                hexagons.add(hex);
            }
        }
    }

    @Override
    public coordinates getCoordsToDrawOn(coordinates coords){
        int tempX;
        int tempY;

        if(coords.y%2 == 0){
            tempX = (int) (coords.x * HEXSIZE*Math.sqrt(3) + xOffset);
            tempY = (int) (coords.y * HEXSIZE*Math.sqrt(3) + yOffset);
        }
        else {
            tempX = (int) (coords.x * HEXSIZE*Math.sqrt(3) + HEXSIZE / 2 * Math.sqrt(3) + xOffset);
            tempY = (int) (coords.y * HEXSIZE*Math.sqrt(3) + yOffset);
        }
        return new coordinates(tempX, tempY);
    }

    public int getHEXSIZE(){
        return HEXSIZE;
    }

    @Override
    public coordinates getNeighbourCoordinates(coordinates coords) {
        coordinates NW;
        coordinates NE;
        coordinates E;
        coordinates SE;
        coordinates SW;
        coordinates W;
        if(coords.y % 2 == 0){
            NW = new coordinates(coords.x - 1, coords.y + 1);
            NE = new coordinates(coords.x, coords.y + 1);
            E = new coordinates(coords.x + 1, coords.y);
            SE = new coordinates(coords.x, coords.y-1);
            SW = new coordinates(coords.x - 1, coords.y -1);
            W = new coordinates(coords.x - 1, coords.y);
        }
        else{
            NW = new coordinates(coords.x, coords.y + 1);
            NE = new coordinates(coords.x + 1 , coords.y + 1);
            E = new coordinates(coords.x + 1, coords.y);
            SE = new coordinates(coords.x + 1, coords.y-1);
            SW = new coordinates(coords.x, coords.y -1);
            W = new coordinates(coords.x - 1, coords.y);
        }
        coordinates[] arrayCoords = {NW, NE, E, SE, SW, W};
        Random generator = new Random();
        int randomIndex = generator.nextInt(arrayCoords.length);
        return arrayCoords[randomIndex];
    }

    @Override
    public coordinates[] getAllNeighbourCoordinates(coordinates coords) {
        coordinates NW;
        coordinates NE;
        coordinates E;
        coordinates SE;
        coordinates SW;
        coordinates W;
        if(coords.y % 2 == 0){
            NW = new coordinates(coords.x - 1, coords.y + 1);
            NE = new coordinates(coords.x, coords.y + 1);
            E = new coordinates(coords.x + 1, coords.y);
            SE = new coordinates(coords.x, coords.y-1);
            SW = new coordinates(coords.x - 1, coords.y -1);
            W = new coordinates(coords.x - 1, coords.y);
        }
        else{
            NW = new coordinates(coords.x, coords.y + 1);
            NE = new coordinates(coords.x + 1 , coords.y + 1);
            E = new coordinates(coords.x + 1, coords.y);
            SE = new coordinates(coords.x + 1, coords.y-1);
            SW = new coordinates(coords.x, coords.y -1);
            W = new coordinates(coords.x - 1, coords.y);
        }
        coordinates[] arrayCoords = {NW, NE, E, SE, SW, W};
        return arrayCoords;
    }

    @Override
    public void drawWorld(Console console){
        Graphics g = console.getPanel().getGraphics();
        for (Hexagon hex: hexagons){
            hex.draw(g);
            hex.draw(g, Color.white);
        }

        for (Organism org: organisms){
            if(org != null)
                org.draw(console);
        }
    }

    public coordinates getClickedFigureCoordinates(Point point){
        for(int i = 0; i < hexagons.size(); i++){
            if(hexagons.get(i).contains(point)){
                return new coordinates(i % getSIZE(), i / getSIZE());
            }
        }
        return null;
    }

    @Override
    public boolean isClickedFigureCoordinates(Point point){
        for(int i = 0; i < hexagons.size(); i++){
            if(hexagons.get(i).contains(point)){
                return true;
            }
        }
        return false;
    }


}
