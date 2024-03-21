package Organisms;

public class twoOrganisms{
    Organism firstOrganism;
    Organism secondOrganism;
    public Organism getFirst(){
        return firstOrganism;
    }
    public Organism getSecond(){
        return secondOrganism;
    }

    public void setFirst(Organism organism) {
        firstOrganism = organism;
    }

    public void setSecond(Organism organism) {
        secondOrganism = organism;
    }
}
