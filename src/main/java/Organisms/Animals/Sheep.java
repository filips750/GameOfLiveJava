package Organisms.Animals;

import Helpers.coordinates;
import Organisms.*;
import Worlds.World;

import java.awt.*;

public class Sheep extends Animal{

    public Sheep(coordinates coords, World world) {
        super(coords, world);
        setColor(Color.white);
        setSecondColor(Color.darkGray);
        setToShow("\uD83D\uDC11");
        setStrength(4);
        setInitiative(4);

    }

    public Sheep() {
        setColor(Color.white);
        setSecondColor(Color.darkGray);
        setToShow("\uD83D\uDC11");
        setStrength(4);
        setInitiative(4);
    }

    public Sheep(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(Color.white);
        setSecondColor(Color.darkGray);
        setToShow("\uD83D\uDC11");
        setToShow("🐢");
        setInitiative(4);
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

        if (two_organisms.getFirst() instanceof Sheep) {
            two_organisms.getFirst().colission();
        }
        else if (two_organisms.getSecond() instanceof Sheep) {
            two_organisms.getSecond().colission();
        }
    }

    @Override
    public void collision() {

    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Sheep(newCoords, world);
    }
}
