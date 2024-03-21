package Organisms.Plants;

import Helpers.coordinates;
import Organisms.Animals.Animal;
import Organisms.Organism;
import Organisms.twoOrganisms;
import Worlds.World;

import java.awt.*;

public class SosnowskyHogweed extends Plant{
    public SosnowskyHogweed(coordinates coords, World world) {
        super(coords, world);
        setColor(new Color(60, 87, 51));
        setSecondColor(Color.white);
        setToShow("☣️︎");
        setStrength(10);
    }

    public SosnowskyHogweed(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(new Color(60, 87, 51));
        setSecondColor(Color.white);
        setToShow("☣️︎");
    }

    public SosnowskyHogweed() {
        setColor(new Color(60, 87, 51));
        setSecondColor(Color.white);
        setToShow("☣️︎");
        setStrength(10);
    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new SosnowskyHogweed(newCoords, world);
    }

    @Override
    public void action() {
        reproduce();
        perk();
    }

    @Override
    public void collision() {

    }

    @Override
    public boolean isAttackRejected(Organism secondOrganism) {
        return false;
    }

    @Override
    public void perk(){
        coordinates[] arrayToDelete = world.getAllNeighbourCoordinates(getCoords());
        Animal toDelete;
        for(coordinates coord: arrayToDelete){
            if(world.getOrganismByCoords(coord) instanceof Animal){
                toDelete = (Animal) world.getOrganismByCoords(coord);
                System.out.println(toDelete + "killed by toxic fumes on: " + coord);
                toDelete.removeFromWorld();
            }
        }
    }
}
