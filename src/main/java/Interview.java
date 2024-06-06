import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Interview {
    static int cellsRemaining = 0;

    static public int solveRobotDFSUtil(ArrayList<ArrayList<Integer>> room, Robot robot, Set<List<Integer>> visited) {

        int steps = 0;

        // Clean the cell and mark as visited
        robot.clean();
        visited.add(robot.getPosition());

        cellsRemaining--;
        // STOP HERE IF ALL CELLS ARE CLEANED
        if(cellsRemaining == 0) {
            return steps;
        }

        // Move in all 4 directions
        for(int i = 0; i < 4; i++) {

            ArrayList<Integer> nextPosition = robot.getNextPosition();

            if(!visited.contains(nextPosition) &&
                    nextPosition.get(0) >= 0 && nextPosition.get(0) < room.size() &&
                    nextPosition.get(1) >= 0 && nextPosition.get(1) < room.get(0).size() &&
                    room.get(nextPosition.get(0)).get(nextPosition.get(1)) == 1) {

                robot.move(true); // MOVE
                steps++;

                // Solve for rest of the room
                steps += solveRobotDFSUtil(room, robot, visited);

                // STOP HERE IF ALL CELLS ARE CLEANED
                if(cellsRemaining == 0) {
                    return steps;
                }

                // U-TURN
                robot.right();
                robot.right();

                // MOVE BACK TO ORIGINAL POSITION
                robot.move(true);
                steps++;

                // FACE ORIGINAL DIRECTION
                robot.right();
                robot.right();
            }

            // Change Direction
            robot.right();
        }

        return steps;
    }

    static int solveRobot(ArrayList<ArrayList<Integer>> room, Robot robot, Algorithm algorithm) {

        // Get Starting Position
        ArrayList<Integer> startingPosition = Helper.findFirstFreeSpot(room);

        if(startingPosition.get(0) == -1 && startingPosition.get(1) == -1) {
            System.out.println("No free spot found");
            return 0;
        }

        robot.setPosition(startingPosition.get(0), startingPosition.get(1));

        // MAX CELLS TO CLEAN -> STOP AFTER ALL CELLS ARE CLEANED
        cellsRemaining = Helper.getOnesCount(room);

        // Visited Array
        Set<List<Integer>> visited = new HashSet<>();

        switch (algorithm) {
            case DFS -> {
                return solveRobotDFSUtil(room, robot, visited);
            }
            case BFS -> {
                return -1;
            }
            default -> {
                System.out.println("Invalid Algorithm");
                return -1;
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> room = Helper.getMatrix(7, 7);

        Helper.printMatrix(room);

        Robot robot = new Robot(0, 0, 0);
        int dfsCount = solveRobot(room, robot, Algorithm.DFS);

        System.out.println("DFS Count: " + dfsCount);
    }
}
