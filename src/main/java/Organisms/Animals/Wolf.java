package Organisms.Animals;

import Helpers.coordinates;
import Organisms.*;
import Worlds.World;

import java.awt.*;

public class Wolf extends Animal{

    public Wolf(coordinates coords, World world) {
        super(coords, world);
        setColor(Color.darkGray);
        setSecondColor(Color.white);
        setToShow("\uD83D\uDC3A");
        setStrength(9);
        setInitiative(5);
    }

    public Wolf() {
        setColor(Color.darkGray);
        setSecondColor(Color.white);
        setToShow("\uD83D\uDC3A");
        setStrength(9);
        setInitiative(5);
    }

    public Wolf(coordinates coords, int age, int strength){
        super(coords, age, strength);
        setColor(Color.darkGray);
        setSecondColor(Color.white);
        setToShow("\uD83D\uDC3A");
        setInitiative(5);
    }

    @Override
    public boolean isAttackRejected(Organism secondOrganism) {
        return false;
    }

    @Override
    public void action(){
        super.action();
        World world = getWorld();
        twoOrganisms two_organisms = world.getTwoOrganisms(getCoords());
        if (two_organisms.getFirst() == null || two_organisms.getSecond() == null)
            return;

        if (two_organisms.getFirst() instanceof Wolf) {
            two_organisms.getFirst().colission();
        }
        else if (two_organisms.getSecond() instanceof Wolf) {
            two_organisms.getSecond().colission();
        }
    }

    @Override
    public void collision() {

    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Wolf(newCoords, world);
    }
}
