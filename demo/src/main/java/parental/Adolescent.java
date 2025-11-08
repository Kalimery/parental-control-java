package parental;

import java.util.ArrayList;
import java.util.List;

public class Adolescent {
    private String name;
    private int age;
    private List<Restriction> restrictions = new ArrayList<>();

    public Adolescent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void addRestriction(Restriction restriction) {
        restrictions.add(restriction);
    }

    public void showRestrictions() {
        System.out.println("📱 Restrictions for " + name + ":");
        if (restrictions.isEmpty()) {
            System.out.println("   No restrictions applied.");
        } else {
            for (Restriction r : restrictions) {
                System.out.println("   - " + r.getType() + " (" + r.getDuration() + " hours)");
            }
        }
    }
}
