package Q3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<State> calculateBeliefState(List<State> initialBS, List<Integer> actions, List<Integer> obs) {
        // todo
        return new ArrayList<State>();
    }

    public static void main(String[] args) {
        List<State> initialBS = new ArrayList<State>();
        List<Integer> actions = new ArrayList<Integer>();
        List<Integer> obs = new ArrayList<Integer>();

	    List<State> beliefState = calculateBeliefState(initialBS, actions, obs);

	    for (State state : beliefState) {
            System.out.println(state.location + ": " + state.value);
        }
    }
}
