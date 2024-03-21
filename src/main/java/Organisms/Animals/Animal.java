package Organisms.Animals;

import Helpers.coordinates;
import Organisms.*;
import Worlds.World;

import java.util.Random;

public abstract class Animal extends Organism {
    coordinates prevCoords;

    public Animal(coordinates coords, World world) {
        super(coords, world);
    }

    public Animal() {
        super();
        prevCoords = new coordinates(0,0);
    }

    public Animal(coordinates coords, int age, int strength) {
        super(coords, age, strength);
    }

    @Override
    public void action() {
        ageOrganism();
        Random generator = new Random();
        coordinates oldCoords = getCoords();
        setPrevCoords(oldCoords);
        coordinates new_coords = world.getNeighbourCoordinates(oldCoords);
        if ((new_coords.x >= 0 && new_coords.x < world.getSIZE()) && (new_coords.y >= 0 && new_coords.y < world.getSIZE()))
            setCoords(new_coords);
    }


    private void setPrevCoords(coordinates oldCoords) {
        prevCoords = oldCoords;
    }
    public coordinates getPrevCoords() {
        return prevCoords;
    }
    public boolean isMature(){
        return getAge() > 1;
    }
    @Override
    public void perk() {}

    @Override
    public void colission() {
        World world = this.getWorld();
        twoOrganisms twoOrgs = world.getTwoOrganisms(getCoords());

        if (twoOrgs.getFirst() == null || twoOrgs.getSecond() == null)
            return;

        if(twoOrgs.getFirst().getClass().equals(twoOrgs.getSecond().getClass())){
            Animal firstAnimal = (Animal) twoOrgs.getFirst();
            Animal secondAnimal = (Animal) twoOrgs.getSecond();
            if(firstAnimal.isMature() && secondAnimal.isMature())
                reproduce(getPrevCoords());
            setCoords(prevCoords);
            return;
        }

        if(twoOrgs.getFirst() == this){
            if(twoOrgs.getSecond().isAttackRejected(twoOrgs.getFirst())){
                twoOrgs.getFirst().setCoords(getPrevCoords());
            }
            twoOrgs.getSecond().perk();
            twoOrgs.getFirst().fight();
        }
        if(twoOrgs.getSecond() == this){
            if(twoOrgs.getFirst().isAttackRejected(twoOrgs.getSecond())){
                twoOrgs.getSecond().setCoords(getPrevCoords());
            }
            twoOrgs.getFirst().perk();
            twoOrgs.getSecond().fight();
        }
    }

    @Override
    protected abstract Organism copy(coordinates newCoords);

    public void reproduce(coordinates coords){
        Random generator = new Random();
        coordinates oldCoords = getCoords();
        coordinates newCoords = world.getNeighbourCoordinates(oldCoords);
        World world = this.getWorld();
        if (world.getOrganismByCoords(newCoords) == null &&
                (newCoords.x >= 0 && newCoords.x < world.getSIZE()) &&
                (newCoords.y >= 0 && newCoords.y < world.getSIZE()) &&
                newCoords.x != coords.x && newCoords.y != coords.y ) {
            Organism to_add = this.copy(newCoords);
            to_add.setWorld(world);
            world.addOrganism(to_add);
            System.out.println("New" + to_add.getClass() + "on: " + to_add.getCoords());
        }
    }
}
