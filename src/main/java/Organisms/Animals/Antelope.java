package Organisms.Animals;

import Helpers.coordinates;
import Organisms.*;
import Worlds.World;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal{

    public Antelope(coordinates coords, World world) {
        super(coords, world);
        setColor(Color.yellow);
        setSecondColor(Color.white);
        setToShow("🦌");
        setStrength(4);
        setInitiative(4);

    }

    public Antelope() {
        setColor(Color.yellow);
        setSecondColor(Color.white);
        setToShow("🦌");
        setStrength(4);
        setInitiative(4);
    }

    public Antelope(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(Color.yellow);
        setSecondColor(Color.white);
        setToShow("🦌");
        setInitiative(4);
    }

    @Override
    public boolean isAttackRejected(Organism secondOrganism) {
        return false;
    }

    @Override
    public void action(){
        super.action();
        super.action();
        World world = getWorld();
        twoOrganisms two_organisms = world.getTwoOrganisms(getCoords());
        if (two_organisms.getFirst() == null || two_organisms.getSecond() == null)
            return;

        if (two_organisms.getFirst() instanceof Antelope) {
            two_organisms.getFirst().colission();
        }
        else if (two_organisms.getSecond() instanceof Antelope) {
            two_organisms.getSecond().colission();
        }
    }

    @Override
    public void perk(){
        Random generator = new Random();
        if(generator.nextInt() % 2 == 0){
            coordinates[] toRun =  world.getAllNeighbourCoordinates(getCoords());
            for(coordinates coords: toRun){
                if(world.getOrganismByCoords(coords) == null){
                    setCoords(coords);
                    return;
                }
            }
        }
    }

    @Override
    public void collision() {

    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Antelope(newCoords, world);
    }
}
