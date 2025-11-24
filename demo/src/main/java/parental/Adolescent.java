package parental;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple model for an adolescent. Keeps a list of restrictions
 * and supports "quiet hours" where access may be blocked.
 */
public class Adolescent {

    private final String name;
    private final int age;
    private final List<Restriction> restrictions = new ArrayList<>();

    // Quiet hours: -1 means not set
    private int quietStart = -1;
    private int quietEnd = -1;

    public Adolescent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }

    public int getAge() { return age; }

    public void addRestriction(Restriction r) {
        if (r == null) return;
        restrictions.add(r);
    }

    public void showRestrictions() {
        System.out.println("Restrictions for " + name + ":");
        if (restrictions.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        for (Restriction r : restrictions) {
            System.out.println("  - " + r.getType() + " (" + r.getDuration() + "h)");
        }
    }

    /**
     * Set quiet hours (0-23). Works if start == end or across midnight.
     */
    public void setQuietHours(int startHour, int endHour) {
        if (startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23) {
            System.out.println("Invalid hours. Use values between 0 and 23.");
            return;
        }
        this.quietStart = startHour;
        this.quietEnd = endHour;
        System.out.println("Quiet hours set for " + name + ": " + startHour + "h to " + endHour + "h");
    }

    /**
     * Returns true if access is allowed at the provided hour (0..23).
     */
    public boolean isAccessAllowed(int hour) {
        if (hour < 0 || hour > 23) {
            System.out.println("Hour must be between 0 and 23.");
            return false;
        }
        // quiet not set
        if (quietStart == -1 || quietEnd == -1) return true;

        if (quietStart < quietEnd) {
            // normal interval, e.g. 22 -> 23
            return !(hour >= quietStart && hour < quietEnd);
        } else if (quietStart > quietEnd) {
            // across midnight, e.g. 22 -> 6
            return !(hour >= quietStart || hour < quietEnd);
        } else {
            // start == end means quiet all day
            return false;
        }
    }
}