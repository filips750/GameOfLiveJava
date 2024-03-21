import Organisms.Plants.Grass;
import UI.Console;
import UI.MyMouseListener;
import Organisms.*;
import Organisms.Animals.*;
import Helpers.coordinates;
import Worlds.*;

public class Main  {
    public static void main(String[] args){
        Console console = new Console();

        World world1 = new WorldRec();


        console.setWorld(world1);
        Human player = new Human(new coordinates(5,5), world1);

        player.setConsole(console);

        MyMouseListener mouseListener = console.getMouseListener();
        coordinates grass_coords = new coordinates(2, 2);
        Grass grass1 = new Grass(grass_coords, world1);
        world1.addOrganism(grass1);
        world1.addOrganism(player);

        while(true){
            if(console.isFigureClicked(world1)){
                System.out.println(console.getFigureClicked(world1));
                Organism toAdd = null;
                console.showOrganisms();

                while(toAdd == null){
                    toAdd = console.getLastPickedOrganism();
                }
                if(toAdd != null){
                    toAdd.setCoords(console.getFigureClicked(world1));
                    toAdd.setWorld(world1);
                    world1.addOrganism(toAdd);
                    mouseListener.setLastClickedPoint(null);
                }
            }
            if(console.getIsNewTurn()){
                world1.nextTurn();
                world1.drawWorld(console);
                console.setNewTurnFalse();
            }
            if(console.getWorld().isWorldToChange()){
                world1 = console.getWorld();
                player = world1.getHuman();
                player.setConsole(console);
                world1.setWorldToChange(false);
            }
        }
    }
}