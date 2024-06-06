import java.util.ArrayList;
import java.util.List;

public class Robot {

    private int pos_x;
    private int pos_y;
    private int dir;

    public Robot(int pos_x, int pos_y, int dir) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.dir = dir;
    }
    public void setPosition(int x, int y) {
        this.pos_x = x;
        this.pos_y = y;
    }
    public ArrayList<Integer> getPosition() {
        return new ArrayList<>(List.of(this.pos_x, this.pos_y));
    }
    public void left() {
        this.dir = (this.dir + 1) % 4;
    }
    public void right() {
        this.dir = (this.dir - 1 + 4) % 4;
    }

    public void move() {
        this.move(false);
    }

    public void move(boolean verbose) {
        switch (this.dir) {
            case 0 -> this.pos_y++; // UP
            case 1 -> this.pos_x--; // LEFT
            case 2 -> this.pos_y--; // DOWN
            case 3 -> this.pos_x++; // RIGHT
        }
        if(verbose)
            System.out.println("Moving to: " + this.pos_x + ", " + this.pos_y);
    }

    public ArrayList<Integer> getNextPosition() {
        this.move();
        ArrayList<Integer> nextPosition = new ArrayList<>(List.of(this.pos_x, this.pos_y));
        // U-TURN
        this.right();
        this.right();
        // MOVE BACK TO ORIGINAL POSITION
        this.move();
        // FACE ORIGINAL DIRECTION
        this.right();
        this.right();
        return nextPosition;
    }

    public boolean clean() {
        System.out.println("Cleaning at: " + this.pos_x + ", " + this.pos_y);
        return true;
    }
}