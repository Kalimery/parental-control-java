package parental;

import java.util.ArrayList;
import java.util.List;

public class Adolescent {
    private String name;
    private int age;
    private List<Restriction> restrictions = new ArrayList<>();

    private int quietStart = -1; // quiet hours start
    private int quietEnd = -1;   // quiet hours end

    public Adolescent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addRestriction(Restriction restriction) {
        restrictions.add(restriction);
    }

    public void showRestrictions() {
        System.out.println("📱 Restrictions for " + name + ":");
        if(restrictions.isEmpty()) {
            System.out.println("   No restrictions applied.");
        } else {
            for(Restriction r : restrictions) {
                System.out.println("   - " + r.getType() + " (" + r.getDuration() + " hours)");
            }
        }
    }

    // Quiet hours methods
    public void setQuietHours(int start, int end) {
        this.quietStart = start;
        this.quietEnd = end;
        System.out.println("Quiet hours set for " + name + ": " + start + "h to " + end + "h");
    }

    public boolean isAccessAllowed(int hour) {
        if(quietStart == -1 || quietEnd == -1) return true;
        if(quietStart < quietEnd) {
            return !(hour >= quietStart && hour < quietEnd);
        } else {
            return !(hour >= quietStart || hour < quietEnd);
        }
    }
}