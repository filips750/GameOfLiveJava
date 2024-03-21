package Organisms.Plants;

import Helpers.coordinates;
import Organisms.Organism;
import Worlds.World;

import java.awt.*;

public class Dandelion extends Plant{
    public Dandelion(coordinates coords, World world) {
        super(coords, world);
        setColor(Color.yellow);
        setToShow(",");
    }

    public Dandelion(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(Color.yellow);
        setToShow(",");
    }

    public Dandelion() {
        setColor(Color.yellow);
        setToShow(",");
    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Dandelion(newCoords, world);
    }

    @Override
    public void action() {
        reproduce();
        reproduce();
        reproduce();
    }

    @Override
    public void collision() {

    }

    @Override
    public boolean isAttackRejected(Organism secondOrganism) {
        return false;
    }
}
