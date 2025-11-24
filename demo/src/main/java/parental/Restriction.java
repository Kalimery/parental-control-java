package parental;

/**
 * Small value class describing a restriction.
 */
public class Restriction {

    private final String type;
    private final int duration; // hours

    public Restriction(String type, int duration) {
        this.type = type == null ? "Unknown" : type.trim();
        this.duration = Math.max(0, duration);
    }

    public String getType() { return type; }
    public int getDuration() { return duration; }
}