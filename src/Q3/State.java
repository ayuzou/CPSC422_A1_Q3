package Q3;

import java.util.ArrayList;
import java.util.List;

public class State {
    public int x;   // column starting from left
    public int y;   // row starting from bottom
    public float value;
    public boolean isTerminal;

    public static final State col1row1 = new State(1, 1, 0, false);
    public static final State col1row2 = new State(1, 2, 0, false);
    public static final State col1row3 = new State(1, 3, 0, false);

    public static final State col2row1 = new State(2, 1, 0, false);
    //public static final State col2row2 = new State(2, 2, 0, false);
    public static final State col2row3 = new State(2, 3, 0, false);

    public static final State col3row1 = new State(3, 1, 0, false);
    public static final State col3row2 = new State(3, 2, 0, false);
    public static final State col3row3 = new State(3, 3, 0, false);

    public static final State col4row1 = new State(4, 1, 0, false);
    public static final State col4row2 = new State(4, 2, -1, true);
    public static final State col4row3 = new State(4, 3, 1, true);

    public static final List<State> initialBS = new ArrayList<State>(){{
        add(State.col1row1);
        add(State.col1row2);
        add(State.col1row3);
        add(State.col2row1);
        add(State.col2row3);
        add(State.col3row1);
        add(State.col3row2);
        add(State.col3row3);
        add(State.col4row1);
        add(State.col4row2);
        add(State.col4row3);
    }};

    public State(int x, int y, float value, boolean isTerminal) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.isTerminal = isTerminal;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public String getCoordinate(){
        return "(" + this.x + "," + this.y + ")";
    }

    public float getWallProbability(int x){
        switch (x){
            case 1:
                if (this.x == 3){
                    return 0.9f;
                }
                else return 0.1f;

            case 2:
                if (this.x == 3){
                    return 0.1f;
                }
                else return 0.9f;
        }
        return 1;
    }

    public void updateBeliefState(int action, int observation){
        // todo
    }
}
