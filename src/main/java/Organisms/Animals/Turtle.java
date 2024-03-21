package Organisms.Animals;

import Helpers.coordinates;
import Organisms.*;
import Worlds.World;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal{

    public Turtle(coordinates coords, World world) {
        super(coords, world);
        setColor(new Color(255, 255, 255));
        setSecondColor(new Color(27, 65, 18));
        setToShow("🐢");
        setStrength(2);
        setInitiative(1);

    }

    public Turtle() {
        setColor(new Color(255, 255, 255));
        setSecondColor(new Color(27, 65, 18));
        setToShow("🐢");
        setStrength(2);
        setInitiative(1);
    }

    public Turtle(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(new Color(255, 255, 255));
        setSecondColor(new Color(27, 65, 18));
        setToShow("🐢");
        setInitiative(1);
    }

    @Override
    public boolean isAttackRejected(Organism secondOrganism) {
        if(secondOrganism.getInitiative() > 5)
            System.out.println("Turtle rejected oponent: " + secondOrganism);
        return secondOrganism.getStrength() > 5;
    }

    @Override
    public void action(){
        Random generator = new Random();
        if(generator.nextInt() % 4 == 0)
            return;
        super.action();
        World world = getWorld();
        twoOrganisms two_organisms = world.getTwoOrganisms(getCoords());
        if (two_organisms.getFirst() == null || two_organisms.getSecond() == null)
            return;

        if (two_organisms.getFirst() instanceof Turtle) {
            two_organisms.getFirst().colission();
        }
        else if (two_organisms.getSecond() instanceof Turtle) {
            two_organisms.getSecond().colission();
        }
    }

    @Override
    public void collision() {

    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Turtle(newCoords, world);
    }
}
