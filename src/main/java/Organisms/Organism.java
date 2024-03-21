package Organisms;
import Organisms.Animals.*;
import Organisms.Plants.*;
import UI.Console;
import Worlds.Hexagon;
import Worlds.World;
import Helpers.coordinates;
import Worlds.WorldHex;
import Worlds.WorldRec;
import org.w3c.dom.css.Rect;

import java.awt.*;

public abstract class Organism {
    private int strength;
    private int initiative;
    private int age = 0;
    private Color color;
    private Color secondColor;
    private coordinates coords;
    private String toShow;

    public World world;
    public Organism(coordinates coords, World world){
        this.coords = coords;
        this.world = world;
    }

    public Organism() {
    }

    public Organism(coordinates coords, int age, int strength){
        this.coords = coords;
        this.age = age;
        this.strength = strength;
    }

    public void draw(Console console){
        Graphics g = console.getPanel().getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        if(world instanceof WorldHex){
            coordinates tempCoords = ((WorldHex) world).getCoordsToDrawOn(coords);
            Point centerPoint = new Point(tempCoords.x, tempCoords.y);
            Hexagon hex = new Hexagon(centerPoint, ((WorldHex) world).getHEXSIZE(), Color.black);
            hex.draw(g, color);
            g.setColor(secondColor);
            g.drawString(toShow, tempCoords.x, tempCoords.y);
        }
        else if (world instanceof WorldRec) {
            coordinates tempCoords = world.getCoordsToDrawOn(coords);
            WorldRec tempWorldRec = (WorldRec) world;
            Rectangle rec = new Rectangle(tempCoords.x, tempCoords.y, tempWorldRec.getRECSIZE(), tempWorldRec.getRECSIZE());
            g2d.setColor(color);
            g2d.fillRect(rec.x, rec.y, rec.width, rec.height);
            g2d.setColor(secondColor);
            g2d.drawString(toShow, (int)(rec.x + tempWorldRec.getRECSIZE() / 2), (int)(rec.y + tempWorldRec.getRECSIZE() / 2));
        }
    }

    public void setToShow(String toSet){
        toShow = toSet;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative1){
        initiative = initiative1;
    }

    public coordinates getCoords() {
        return coords;
    }
    public void setStrength(int strength1){
        strength = strength1;
    }

    public int getStrength() {
        return strength;
    }

    public int getAge() {
        return age;
    }
    public void fight(){
        Organism other;
        Organism startingFight;
        twoOrganisms twoOrgs = world.getTwoOrganisms(getCoords());
        if(twoOrgs.getFirst() == this)
            other = twoOrgs.getSecond();
        else
            other = twoOrgs.getFirst();

        if(this == null || other == null)
            return;
        startingFight = this;
        if(startingFight.getStrength() < other.getStrength()){
            System.out.println(startingFight.toString() + "got killed by: " + other.toString());
            startingFight.removeFromWorld();
        }
        else{
            System.out.println(other.toString() + "got killed by: " + startingFight.toString());
            other.removeFromWorld();
        }

    }

    public void removeFromWorld() {
        world.changeOrganism(this, null);
    }

    public abstract void action();
    public abstract void perk();
    public abstract void collision();
    public abstract boolean isAttackRejected(Organism secondOrganism);
    protected abstract Organism copy(coordinates newCoords);
    public void ageOrganism(){
        age = age + 1;
    }

    public void setCoords(coordinates figureClicked) {
        coords = figureClicked;
    }
    public void setSecondColor(Color secondColor1){
        secondColor = secondColor1;
    }
    public void setWorld(World world1) {
        world = world1;
    }

    protected World getWorld() {
        return world;
    }

    public abstract void colission();

    public String serialize(){
        if(this instanceof Human)
            return this.getClass() + "," + getCoords().x + "," + getCoords().y + ","
                    + getStrength() + "," + getAge() + "," + ((Human) this).getSpecialAbilityCd();
        else
            return this.getClass() + "," + getCoords().x + "," + getCoords().y + ","
                    + getStrength() + "," + getAge() + "," + "-1";
    }

    public static Organism deserialize(String serialized){
        String[] arrayStrings = serialized.split(",");
        String orgClass = arrayStrings[0];
        String xCoords = arrayStrings[1];
        String yCoords = arrayStrings[2];
        coordinates coords = new coordinates( Integer.parseInt(xCoords) , Integer.parseInt(yCoords));
        String strength = arrayStrings[3];
        String age = arrayStrings[4];
        String humanSpecialAbility = arrayStrings[5];
        if(orgClass == "class Organisms.Animals.Wolf"){
            return new Wolf(coords, Integer.parseInt(age), Integer.parseInt(strength));
        }else if(orgClass.equals("class Organisms.Animals.Turtle")){
            return new Turtle(coords, Integer.parseInt(age), Integer.parseInt(strength));
        }else if(orgClass.equals("class Organisms.Animals.Sheep")){
            return new Sheep(coords, Integer.parseInt(age), Integer.parseInt(strength));
        }else if(orgClass.equals("class Organisms.Animals.Antelope")){
            return new Antelope(coords, Integer.parseInt(age), Integer.parseInt(strength));
        }else if(orgClass.equals("class Organisms.Animals.Fox")){
            return new Fox(coords, Integer.parseInt(age), Integer.parseInt(strength));
        } else if (orgClass.equals("class Organisms.Animals.Human")) {
            return new Human(coords, Integer.parseInt(age), Integer.parseInt(strength), Integer.parseInt(humanSpecialAbility));
        } else if (orgClass.equals("class Organisms.Plants.Grass")){
            return new Grass(coords, Integer.parseInt(age), Integer.parseInt(strength));
        } else if (orgClass.equals("class Organisms.Plants.Guarana")){
            return new Guarana(coords, Integer.parseInt(age), Integer.parseInt(strength));
        } else if (orgClass.equals("class Organisms.Plants.Nightshade")){
            return new Nightshade(coords, Integer.parseInt(age), Integer.parseInt(strength));
        } else if (orgClass.equals("class Organisms.Plants.Dandelion")){
            return new Dandelion(coords, Integer.parseInt(age), Integer.parseInt(strength));
        } else if (orgClass.equals("class Organisms.Plants.SosnowskyHogweed")){
            return new SosnowskyHogweed(coords, Integer.parseInt(age), Integer.parseInt(strength));
        }
        return null;
    }

    @Override
    public String toString() {
        return getClass().toString() + " strength: " + getStrength() + " initiative " + getInitiative();
    }
}

