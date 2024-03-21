package Organisms.Animals;

import Helpers.coordinates;
import Organisms.*;
import Worlds.*;
import Helpers.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Random;

import UI.Console;

enum HexMove{
    NW,
    NE,
    E,
    SE,
    SW,
    W
}

enum RecMove{
    N,
    E,
    S,
    W
}

public class Human extends Animal{
    Console console;
    private HexMove hexMove;
    private RecMove recMove;

    private int specialAbilityCd;
    public Human(coordinates coords, World world) {
        super(coords, world);
        setColor(Color.darkGray);
        setSecondColor(Color.white);
        setToShow("\uD83E\uDD20");
        setStrength(5);
        setInitiative(4);
        setSpecialAbilityCd(10);
    }

    public Human(coordinates coords, int age, int strength, int specialAbilityCd) {
        super(coords, age, strength);
        setColor(Color.darkGray);
        setSecondColor(Color.white);
        setToShow("\uD83E\uDD20");
        setStrength(5);
        setInitiative(4);
        setSpecialAbilityCd(specialAbilityCd);
    }

    public void setSpecialAbilityCd(int specialAbilityCd){
        this.specialAbilityCd = specialAbilityCd;
    }

    public int getSpecialAbilityCd(){
        return specialAbilityCd;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }

    @Override
    public boolean isAttackRejected(Organism secondOrganism) {
        return false;
    }

    public void convertIntToHexMove(){
        if(getConsole().getLastPressedKey() == KeyEvent.VK_Z){
            hexMove = HexMove.NW;
        } else if (getConsole().getLastPressedKey() == KeyEvent.VK_X) {
            hexMove = HexMove.NE;
        }else if (getConsole().getLastPressedKey() == KeyEvent.VK_D) {
            hexMove = HexMove.E;
        }else if (getConsole().getLastPressedKey() == KeyEvent.VK_E) {
            hexMove = HexMove.SE;
        }else if (getConsole().getLastPressedKey() == KeyEvent.VK_W) {
            hexMove = HexMove.SW;
        }else if (getConsole().getLastPressedKey() == KeyEvent.VK_A) {
            hexMove = HexMove.W;
        }else
            hexMove = null;
    }

    public void convertIntToRecMove(){
        if(getConsole() == null){
            return;
        }
        int keyEvent = getConsole().getLastPressedKey();
        if(keyEvent == KeyEvent.VK_S){
            recMove = RecMove.N;
        } else if (keyEvent == KeyEvent.VK_D) {
            recMove = RecMove.E;
        }else if (keyEvent == KeyEvent.VK_W) {
            recMove = RecMove.S;
        }else if (keyEvent == KeyEvent.VK_A) {
            recMove = RecMove.W;
        }else
            recMove = null;
    }

    public void humanMove(){
        coordinates toSet;

        coordinates[] neighbourCoords = world.getAllNeighbourCoordinates(getCoords());
        if(world instanceof WorldHex){
            convertIntToHexMove();
            if(hexMove == HexMove.NW){
                toSet = neighbourCoords[0];
            }else if (hexMove == HexMove.NE){
                toSet = neighbourCoords[1];
            }else if (hexMove == HexMove.E){
                toSet = neighbourCoords[2];
            }else if (hexMove == HexMove.SE){
                toSet = neighbourCoords[3];
            }else if (hexMove == HexMove.SW){
                toSet = neighbourCoords[4];
            }else if (hexMove == HexMove.W){
                toSet = neighbourCoords[5];
            }
            else{
                toSet = getCoords();
            }
        }
        else if(world instanceof WorldRec){
            convertIntToRecMove();
            if(recMove == RecMove.N){
                toSet = neighbourCoords[0];
            } else if (recMove == RecMove.E) {
                toSet = neighbourCoords[1];
            } else if (recMove == RecMove.S) {
                toSet = neighbourCoords[2];
            }else if (recMove == RecMove.W) {
                toSet = neighbourCoords[3];
            }
            else{
                toSet = getCoords();
            }
        }else{
            toSet = getCoords();
        }
        if(toSet.x >= 0 && toSet.x < world.getSIZE() &&
           toSet.y >= 0 && toSet.y < world.getSIZE())
            setCoords(toSet);
    }

    @Override
    public void action(){
        Random generator = new Random();
        humanMove();
        if(specialAbilityCd > 7)
            humanMove();
        else if(specialAbilityCd > 5){
            if(generator.nextInt() % 2 == 0)
                humanMove();
        }

        if(specialAbilityCd <= 0 && console.getLastPressedKey() == KeyEvent.VK_R){
            specialAbilityCd = 10;
        }

        specialAbilityCd--;

        World world = getWorld();
        twoOrganisms two_organisms = world.getTwoOrganisms(getCoords());
        if (two_organisms.getFirst() == null || two_organisms.getSecond() == null)
            return;

        if (two_organisms.getFirst() instanceof Human) {
            two_organisms.getFirst().colission();
        }
        else if (two_organisms.getSecond() instanceof Human) {
            two_organisms.getSecond().colission();
        }
    }

    @Override
    public void collision() {

    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Human(newCoords, world);
    }
}
