import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Interview {

    static int cellsRemaining = 0;

    static public ArrayList<ArrayList<Integer>> getMatrix(int n, int m) {

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for(int j = 0; j < m; j++) {
                if(Math.random() > 0.5)
                    row.add(0);
                else
                    row.add(1);
            }
            matrix.add(row);
        }
        return matrix;

    }

    static public ArrayList<Integer> findFirstFreeSpot(ArrayList<ArrayList<Integer>> room) {
        for(int i = 0; i < room.size(); i++) {
            for(int j = 0; j < room.get(i).size(); j++) {
                if(room.get(i).get(j) == 1) {
                    System.out.println("Found free spot at: " + i + ", " + j);
                    return new ArrayList<>(List.of(i, j));
                }
            }
        }
        // No free spot found
        return new ArrayList<>(List.of(-1, -1));
    }

    static public void printMatrix(ArrayList<ArrayList<Integer>> room) {
        for (ArrayList<Integer> integers : room) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    static public int solveRobotDFSUtil(ArrayList<ArrayList<Integer>> room, Robot robot, Set<List<Integer>> visited) {

        int steps = 0;

        // Clean the cell and mark as visited
        robot.clean();
        visited.add(robot.getPosition());

        cellsRemaining--;
        if(cellsRemaining == 0) { // STOP HERE IF ALL CELLS ARE CLEANED
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

                steps += solveRobotDFSUtil(room, robot, visited); // Solve for rest of the room

                if(cellsRemaining == 0) { // STOP HERE IF ALL CELLS ARE CLEANED
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

    static int solveRobotDFS(ArrayList<ArrayList<Integer>> room, Robot robot) {

        // Get Starting Position
        ArrayList<Integer> startingPosition = findFirstFreeSpot(room);

        if(startingPosition.get(0) == -1 && startingPosition.get(1) == -1) {
            System.out.println("No free spot found");
            return 0;
        }

        robot.setPosition(startingPosition.get(0), startingPosition.get(1));

        // Find Number of Zeroes | MAX CELLS TO CLEAN -> STOP AFTER ALL CELLS ARE CLEANED
        int oneCount = 0;
        for (ArrayList<Integer> integers : room) {
            for (Integer integer : integers) {
                if (integer == 1) {
                    oneCount++;
                }
            }
        }

        // VISITED of x,y
        Set<List<Integer>> visited = new HashSet<>();

        // DFS
        cellsRemaining = oneCount;
        return solveRobotDFSUtil(room, robot, visited);
    }


    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> room = getMatrix(7, 7);

        printMatrix(room);

        Robot robot = new Robot(0, 0, 0);
        int dfsCount = solveRobotDFS(room, robot);

        System.out.println("DFS Count: " + dfsCount);
    }
}
