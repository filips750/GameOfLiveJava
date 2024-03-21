package Organisms.Plants;

import Helpers.coordinates;
import Organisms.Organism;
import Organisms.twoOrganisms;
import Worlds.World;

import java.awt.*;

public class Guarana extends Plant{
    public Guarana(coordinates coords, World world) {
        super(coords, world);
        setColor(new Color(0,102, 0));
        setToShow("%");
    }

    public Guarana(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(new Color(0,102, 0));
        setToShow("%");
    }


    public Guarana() {
        setColor(new Color(0,102, 0));
        setToShow("%");
    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Guarana(newCoords, world);
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

    @Override
    public void perk(){
        Organism other;
        twoOrganisms twoOrgs = world.getTwoOrganisms(getCoords());
        if(twoOrgs.getFirst() == this)
            other = twoOrgs.getSecond();
        else
            other = twoOrgs.getFirst();

        if(this == null || other == null)
            return;

        Organism guarana = this;
        int tempStrength;
        if(twoOrgs.getFirst() == guarana){
            tempStrength = twoOrgs.getSecond().getStrength() + 3;
            twoOrgs.getSecond().setStrength(tempStrength);
        }
        else if(twoOrgs.getSecond() == guarana){
            tempStrength = twoOrgs.getFirst().getStrength() + 3;
            twoOrgs.getFirst().setStrength(tempStrength);
        }
    }
}
