package Q3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<State> calculateBeliefState(List<State> initialBS, List<Integer> actions, List<Integer> obs) {
        // todo
        // 1 = UP , 2 = DOWN , 3 = LEFT , 4 = RIGHT
        for(int i = 0; i < actions.size(); i++){
            for(int j = 0; i < initialBS.size(); i++){
                initialBS.get(j).updateBeliefState(actions.get(i),obs.get(i));
            }
        }
        List<State> updatedBS = new ArrayList<State>(initialBS);
        return updatedBS;
    }

    public static void main(String[] args) {
        List<State> initialBS = new ArrayList<State>();
        List<Integer> actions = new ArrayList<Integer>();
        List<Integer> obs = new ArrayList<Integer>();

	    List<State> beliefState = calculateBeliefState(initialBS, actions, obs);

	    for (State state : beliefState) {
            System.out.println(state.returnCoordinate() + ": " + state.value);
        }
    }
}
