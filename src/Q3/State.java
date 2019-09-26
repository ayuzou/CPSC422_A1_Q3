package Q3;

public class State {
    public String location;
    public float value;

    public State(String location, float value) {
        this.location = location;
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
