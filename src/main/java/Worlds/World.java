package Worlds;

import Organisms.*;
import Organisms.Animals.Human;
import UI.Console;
import Helpers.coordinates;
import java.awt.*;
import java.util.*;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class World {
    private boolean isWorldToChange = false;
    protected static final int SIZE = 10;
    protected ArrayList<Organism> organisms = new ArrayList<Organism>();

    public static World loadFromCSV(String path) {
        World toReturn = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            line = reader.readLine();
            if(line.equals("Rec")){
                toReturn = new WorldRec();
            }
            else if(line.equals("Hex")){
                toReturn = new WorldHex();
            }
            while ((line = reader.readLine()) != null) {
                Organism orgToAdd = Organism.deserialize(line);
                orgToAdd.setWorld(toReturn);
                toReturn.addOrganism(orgToAdd);
                toReturn.isWorldToChange = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public Human getHuman(){
        for(Organism org: organisms){
            if(org instanceof Human){
                return (Human)org;
            }
        }
        return null;
    }

    public void addOrganism(Organism toAdd){
        organisms.add(toAdd);
    }

    private void sortByInitiativeAndAge(){
        for (int iter = 1; iter < organisms.size(); iter++){
            if (organisms.get(iter - 1) == null || organisms.get(iter) == null)
                continue;

            if (organisms.get(iter - 1).getInitiative() < organisms.get(iter).getInitiative())
                Collections.swap(organisms, iter - 1, iter);
        }

        for (int iter = 1; iter < organisms.size(); iter++){
                if (organisms.get(iter - 1) == null || organisms.get(iter) == null)
                    continue;

                if (organisms.get(iter - 1).getInitiative() == organisms.get(iter).getInitiative() &&
                        organisms.get(iter - 1).getAge() < organisms.get(iter).getAge())
                    Collections.swap(organisms, iter - 1, iter);
            }

    }

    public Organism getOrganismByCoords(coordinates coords){
        for(Organism org: organisms){
            if(org == null)
                continue;
            if(org.getCoords().equals(coords))
                return org;
        }
        return null;
    }
    public void nextTurn(){
        sortByInitiativeAndAge();
        for(int i = 0; i < organisms.size(); i++){
            if(organisms.get(i)!= null)
                organisms.get(i).action();
        }
    }

    public void changeOrganism(Organism old, Organism toSet){
        for(int i = 0; i < organisms.size(); i++){
            if(organisms.get(i) == old){
                organisms.set(i, toSet);
            }
        }
    }
    public abstract coordinates[] getAllNeighbourCoordinates(coordinates coords);

    public abstract coordinates getNeighbourCoordinates(coordinates coords);
    public abstract void drawWorld(Console console);
    public abstract coordinates getCoordsToDrawOn(coordinates coordinates);
    public int getSIZE(){
        return SIZE;
    }

    public boolean isWorldToChange() {
        return isWorldToChange;
    }

    public abstract boolean isClickedFigureCoordinates(Point point);
    public abstract coordinates getClickedFigureCoordinates(Point point);
    public twoOrganisms getTwoOrganisms(coordinates coords){
        twoOrganisms to_return = new twoOrganisms();
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i) == null)
                continue;
            if (coords.x == organisms.get(i).getCoords().x && coords.y == organisms.get(i).getCoords().y) {
                if (to_return.getFirst() == null)
                    to_return.setFirst(organisms.get(i));
                else {
                    to_return.setSecond(organisms.get(i));
                    return to_return;
                }
            }
        }
        return to_return;
    }

    public void setWorldToChange(boolean worldToChange){
        isWorldToChange = worldToChange;
    }

    public void saveToCSV(){
        LocalDateTime now = LocalDateTime.now();
        String fileName = DateTimeFormatter.ofPattern("HH").format(now) + DateTimeFormatter.ofPattern("MM").format(now) +".csv";
        System.out.println(fileName);
        try {
            FileWriter writer = new FileWriter(fileName);
            if(this instanceof WorldRec){
                writer.append("Rec");
            } else if (this instanceof WorldHex) {
                writer.append("Hex");
            }
            writer.append('\n');
            for(Organism org: organisms){
                writer.append(org.serialize());
                writer.append('\n');
            }
            writer.flush();
            writer.close();
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
