package Q3;

public class State {
    public int x;
    public int y;
    public float value;

    public State(int x, int y, float value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public String returnCoordinate(){
        return "(" + this.x + "," + this.y + ")";
    }

    public float wallProbability(int x){
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

    public boolean isTerminal(){
        if(x == 4 && y != 3){
            return true;
        }
        else return false;
    }

    public void updateBeliefState(int action, int observation){
        // todo
    }

    public void setValue(float value) {
        this.value = value;
    }
}
