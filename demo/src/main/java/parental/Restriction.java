package parental;

public class Restriction {
    private String type;      // e.g. "App Block", "Screen Time Limit"
    private int duration;     // in hours

    public Restriction(String type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }
}