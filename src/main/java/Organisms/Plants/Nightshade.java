package Organisms.Plants;

import Helpers.coordinates;
import Organisms.Organism;
import Organisms.twoOrganisms;
import Worlds.World;

import java.awt.*;

public class Nightshade extends Plant{
    public Nightshade(coordinates coords, World world) {
        super(coords, world);
        setColor(new Color(15, 40, 15));
        setSecondColor(Color.white);
        setToShow("☢︎");
        setStrength(99);
    }

    public Nightshade(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(new Color(15, 40, 15));
        setSecondColor(Color.white);
        setToShow("☢︎");
        setStrength(99);
    }

    public Nightshade() {
        setColor(new Color(15, 40, 15));
        setSecondColor(Color.white);
        setToShow("☢︎");
        setStrength(99);
    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Nightshade(newCoords, world);
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

        Organism nightshade = this;
        int tempStrength;
        if(twoOrgs.getFirst() == nightshade){
            twoOrgs.getSecond().removeFromWorld();
        }
        else if(twoOrgs.getSecond() == nightshade){
            twoOrgs.getFirst().removeFromWorld();
        }
    }
}
