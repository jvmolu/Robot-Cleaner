import java.util.ArrayList;
import java.util.List;

public class Helper {

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

    static public int getOnesCount(ArrayList<ArrayList<Integer>> room) {
        int oneCount = 0;
        for (ArrayList<Integer> integers : room) {
            for (Integer integer : integers) {
                if (integer == 1) {
                    oneCount++;
                }
            }
        }
        return oneCount;
    }

}
