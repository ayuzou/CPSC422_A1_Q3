package Q3;

import javafx.util.Pair;

import java.text.DecimalFormat;
import java.util.*;

public class Main {

    private static double[][] calculateBeliefState(double[][] prevBS, List<Integer> actions, List<Integer> obs) {
        double[][] curBS = new double[3][4];
        double sumForNorm = 0;
        for (int a = 0; a < actions.size(); a++) {
            curBS = new double[3][4];
            for (int i = 0; i < prevBS.length; i++) {
                for (int j = 0; j < prevBS[i].length; j++) {
                    if (i == 1 && j == 1) continue;
                    curBS[i][j] = updateBS(prevBS, i, j, actions.get(a), obs.get(a));
                    sumForNorm += curBS[i][j];
                }
            }
            normalize(curBS, sumForNorm);
            sumForNorm = 0;

            prevBS = curBS;
        }
        return curBS;
    }

    public static void normalize(double[][] curBS, double sumForNum) {
        for (int i = 0; i < curBS.length; i++) {
            for (int j = 0; j < curBS[i].length; j++) {
                curBS[i][j] /= sumForNum;
            }
        }
    }

    private static double updateBS(double[][] prevBS, int row, int col, int action, int obs) {
        double wallProb = getWallProb(row, col, obs);
        return wallProb * calculateSum(prevBS, row, col, action);
    }

    private static double getWallProb(int row, int col, int obs) {
        if (obs == 0) {
            if (col == 3 && row != 2) return 1;
            else return 0;
        }
        else if (col == 3 && row != 2) return 0;
        else if (col == 2) {     // col 3 in grid example
            if (obs == 1) return 0.9f;
            else return 0.1f;
        }
        else {
            if (obs == 1) return 0.1f;
            else return 0.9f;
        }

    }

    private static double calculateSum(double[][] prevBS, int row, int col, int action) {
        double sum = 0f;
        Map<Pair<Integer, Integer>, Double> neighbours = getNeighbours(row, col, action);

        for (Map.Entry<Pair<Integer, Integer>, Double> neighbour : neighbours.entrySet()) {
            sum += prevBS[neighbour.getKey().getKey()][neighbour.getKey().getValue()] * neighbour.getValue();
        }
        return sum;
    }



    public static Map<Pair<Integer, Integer>, Double> getNeighbours(int row, int col, int action) {
        Map<Pair<Integer, Integer>, Double> neighbours = new HashMap<Pair<Integer, Integer>, Double>();

        //(0,0)
        // up
        if (row == 0 && col == 0 && action == 1) {
             neighbours.put(new Pair<Integer, Integer>(0, 1), 0.1);
             neighbours.put(new Pair<Integer, Integer>(1, 0), 0.8);
             neighbours.put(new Pair<Integer, Integer>(0, 0), 0.9);
        }

        // down
        if (row == 0 && col == 0 && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.1);
        }

        // left
        if (row == 0 && col == 0 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.9);
        }

        // right
        if (row == 0 && col == 0 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.1);
        }


        // (0,1)
        // up
        if (row == 0 && col == 1 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.1);
        }

        // down
        if (row == 0 && col == 1 && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.1);
        }

        // left
        if (row == 0 && col == 1 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.2);
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.8);
        }

        // right
        if (row == 0 && col == 1 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.2);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.8);
        }


        // (0,2)
        // up
        if (row == 0 && col == 2 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.8);
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.8);
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.1);
        }

        // down
        if (row == 0 && col == 2 && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.1);
        }

        // left
        if (row == 0 && col == 2 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.1);
        }

        // right
        if (row == 0 && col == 2 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(0, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.1);
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.1);
        }

        // (0,3) terminal
        // up
        if (row == 0 && col == 3 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.1);
        }

        // down
        if (row == 0 && col == 3 && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.1);
        }

        // left
//        if (row == 0 && col == 3 && action == 3) {
//            neighbours.put(null, null);
//        }

        // right
        if (row == 0 && col == 3 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.8);
        }

        // (1,0)
        // up
        if (row == 1 && col == 0 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.2);
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.8);
        }

        // down
        if (row == 1 && col == 0 && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.8);
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.2);
        }

        // left
        if (row == 1 && col == 0 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.1);
        }

        // right
        if (row == 1 && col == 0 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(0, 0), 0.1);
        }


        // (1,2)
        // up
        if (row == 1 && col == 2 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.8);
        }

        // down
        if (row == 1 && col == 2 && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.8);
        }

        // left
        if (row == 1 && col == 2 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.8);
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.1);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
        }

        // right
        if (row == 1 && col == 2 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(0, 2), 0.1);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
        }


        // (1,3)
        // up
        if (row == 1 && col == 3 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.8);
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.1);
        }

        // down
        if (row == 1 && col == 3 && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.1);
        }

        // left
        if (row == 1 && col == 3 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.1);
        }

        // right
        if (row == 1 && col == 3 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.1);
        }


        // (2,0)
        // up
        if (row == 2 && col == 0 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.1);
        }

        // down
        if (row == 2 && col == 0  && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.9);
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.1);
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.8);
        }

        // left
        if (row == 2 && col == 0 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.9);
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.1);
        }

        // right
        if (row == 2 && col == 0 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(1, 0), 0.1);
        }


        // (2,1)
        // up
        if (row == 2 && col == 1 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
        }

        // down
        if (row == 2 && col == 1  && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.1);
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
        }

        // left
        if (row == 2 && col == 1 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.2);
        }

        // right
        if (row == 2 && col == 1 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.2);
            neighbours.put(new Pair<Integer, Integer>(2, 0), 0.8);
        }


        // (2,2)
        // up
        if (row == 2 && col == 2 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.1);
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.1);
        }

        // down
        if (row == 2 && col == 2  && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.1);
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.1);
        }

        // left
        if (row == 2 && col == 2 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.1);
        }

        // right
        if (row == 2 && col == 2 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(2, 1), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
            neighbours.put(new Pair<Integer, Integer>(1, 2), 0.1);
        }


        // (2,3)
        // up
        if (row == 2 && col == 3 && action == 1) {
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.1);
        }

        // down
        if (row == 2 && col == 3  && action == 2) {
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.8);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.1);
        }

        // left
        if (row == 2 && col == 3 && action == 3) {
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.1);
        }

        // right
        if (row == 2 && col == 3 && action == 4) {
            neighbours.put(new Pair<Integer, Integer>(2, 3), 0.9);
            neighbours.put(new Pair<Integer, Integer>(2, 2), 0.8);
        }

        return neighbours;
    }

    public static void main(String[] args) {
        double[][] prevBS = new double[3][4];
//        prevBS[0][0] = (double)1/9;
//        prevBS[0][1] = (double)1/9;
//        prevBS[0][2] = (double)1/9;
//        prevBS[0][3] = (double)0;
//        prevBS[1][0] = (double)1/9;
//        prevBS[1][1] = (double)1/9;
//        prevBS[1][2] = (double)1/9;
//        prevBS[1][3] = (double)0;
//        prevBS[2][0] = (double)1/9;
//        prevBS[2][1] = (double)1/9;
//        prevBS[2][2] = (double)1/9;
//        prevBS[2][3] = (double)1/9;

        prevBS[0][0] = 0;
        prevBS[0][1] = 0;
        prevBS[0][2] = 0;
        prevBS[0][3] = 0;
        prevBS[1][0] = 0;
        prevBS[1][1] = 0;
        prevBS[1][2] = 0;
        prevBS[1][3] = 0;
        prevBS[2][0] = 1;
        prevBS[2][1] = 0;
        prevBS[2][2] = 0;
        prevBS[2][3] = 0;

        // 1 = up, 2 = down, 3 = left, 4 = right
        List<Integer> actions = new ArrayList<Integer>();
        actions.add(1);
        actions.add(4);
        actions.add(4);
        actions.add(4);

        // 0 = end, 1 = 1-wall, 2 = 2-wall
        List<Integer> obs = new ArrayList<Integer>();
        obs.add(2);
        obs.add(2);
        obs.add(1);
        obs.add(1);

	    double[][] beliefState = calculateBeliefState(prevBS, actions, obs);

	    // round to 4 decimal places
        DecimalFormat df = new DecimalFormat("#.####");

	    for (int i = 0; i < beliefState.length; i++) {
	        for (int j = 0; j < beliefState[i].length; j++) {
                System.out.print(df.format(beliefState[i][j]) + "\t\t");
            }
            System.out.println();
        }
    }
}
