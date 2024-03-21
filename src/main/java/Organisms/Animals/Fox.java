package Organisms.Animals;

import Helpers.coordinates;
import Organisms.*;
import Worlds.World;

import java.awt.*;

public class Fox extends Animal{

    public Fox(coordinates coords, World world) {
        super(coords, world);
        setColor(Color.ORANGE);
        setSecondColor(Color.white);
        setToShow("🦊");
        setStrength(3);
        setInitiative(7);
    }

    public Fox() {
        setColor(Color.ORANGE);
        setSecondColor(Color.white);
        setToShow("🦊");
        setStrength(3);
        setInitiative(7);

    }

    public Fox(coordinates coords, int age, int strength) {
        super(coords, age, strength);
        setColor(Color.ORANGE);
        setSecondColor(Color.white);
        setToShow("🦊");
        setInitiative(7);
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

        if (two_organisms.getFirst() instanceof Fox) {
            two_organisms.getFirst().colission();
        }
        else if (two_organisms.getSecond() instanceof Fox) {
            two_organisms.getSecond().colission();
        }
    }

    @Override
    public void collision() {

    }

    public boolean checkIfShouldEscape(){
        twoOrganisms twoOrgs = world.getTwoOrganisms(getCoords());
        if(twoOrgs.getFirst() == null || twoOrgs.getSecond() == null)
            return false;

        if(twoOrgs.getFirst() instanceof Fox && twoOrgs.getSecond() instanceof Fox)
            return false;


        if(twoOrgs.getFirst() == this){
            if(this.getStrength() < twoOrgs.getSecond().getStrength())
                return true;
            else
                return false;
        } else if (twoOrgs.getSecond() == this) {
            if(this.getStrength() < twoOrgs.getFirst().getStrength())
                return true;
            else
                return false;
        }
    return false;
    }

    @Override
    public void colission() {
        if(checkIfShouldEscape()){
            setCoords(getPrevCoords());
            return;
        }
        super.colission();
    }

    @Override
    protected Organism copy(coordinates newCoords) {
        return new Fox(newCoords, world);
    }
}
