package Organisms.Plants;

import Helpers.coordinates;
import Organisms.Organism;
import Worlds.World;

import java.util.Random;

public abstract class Plant extends Organism {
    private static final int PLANT_REPRODUCE_CONST = 10;

    public Plant(coordinates coords, World world) {
        super(coords, world);
    }
    public Plant(coordinates coords, int age, int strength) {
        super(coords, age, strength);
    }

    public Plant() {
        super();
    }


    @Override
    public void perk() {

    }

    @Override
    public void colission() {

    }

    public void reproduce(){
        Random generator = new Random();
        if (generator.nextInt() % PLANT_REPRODUCE_CONST == 0) {
            coordinates old_coords = getCoords();
            coordinates new_coords = world.getNeighbourCoordinates(old_coords);
            if (world.getOrganismByCoords(new_coords) == null &&
                    (new_coords.x >= 0 && new_coords.x < world.getSIZE()) &&
                    (new_coords.y >= 0 && new_coords.y < world.getSIZE())) {
                Organism to_add = this.copy(new_coords);
                world.addOrganism(to_add);
            }
        }
    }
}
