package parental;

import java.util.ArrayList;
import java.util.List;

/**
 * Parent holds adolescents and can apply restrictions or send alerts.
 */
public class Parent {

    private final String name;
    private final String email;
    private final List<Adolescent> adolescents = new ArrayList<>();

    public Parent(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addAdolescent(Adolescent a) {
        if (a != null) adolescents.add(a);
    }

    public List<Adolescent> getAdolescents() {
        return adolescents;
    }

    public void displayInfo() {
        System.out.println("Parent: " + name + " / " + email);
        if (adolescents.isEmpty()) {
            System.out.println("  (no adolescents)");
            return;
        }
        System.out.println(" Kids:");
        for (Adolescent a : adolescents) {
            System.out.println("  - " + a.getName() + " (" + a.getAge() + ")");
        }
    }

    public void setRestriction(Adolescent a, Restriction r) {
        if (a == null || r == null) {
            System.out.println("Cannot apply restriction: missing adolescent or restriction.");
            return;
        }
        a.addRestriction(r);
        System.out.println(name + " applied -> " + r.getType() + " (" + r.getDuration() + "h) to " + a.getName());
    }

    public void triggerSecurityAlert(Adolescent a, String channel) {
        if (a == null) return;
        System.out.println("!!! SECURITY ALERT for " + a.getName() + " via " + (channel == null ? "UNKNOWN" : channel.toUpperCase()));
    }
}