package Organisms.Plants;

import Helpers.coordinates;
import Organisms.Organism;
import Worlds.World;

import java.awt.*;

public class Grass extends Plant{
    public Grass(coordinates coords, World world) {
        super(coords, world);
        setColor(Color.green);
        setToShow(",");
    }
    public Grass(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(Color.green);
        setToShow(",");
    }


    public Grass() {
        setColor(Color.green);
        setToShow(",");
    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Grass(newCoords, world);
    }

    @Override
    public void action() {
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
