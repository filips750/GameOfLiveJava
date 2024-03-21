package Helpers;

public class coordinates {
    public int x;
    public int y;

    public coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(coordinates toCheck){
        if(this.x == toCheck.x && this.y == toCheck.y)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.x + " , " + this.y;
    }
}
