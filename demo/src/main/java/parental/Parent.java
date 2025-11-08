package parental;

import java.util.ArrayList;
import java.util.List;

public class Parent {
    private String name;
    private String email;
    private List<Adolescent> adolescents = new ArrayList<>();

    public Parent(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void displayInfo() {
        System.out.println("👨 Parent: " + name + " | Email: " + email);
        if(adolescents.isEmpty()) {
            System.out.println("   No adolescents linked yet.");
        } else {
            System.out.println("   Linked adolescents:");
            for(Adolescent t : adolescents) {
                System.out.println("      - " + t.getName() + " (" + t.getAge() + " years)");
            }
        }
    }

    public void addAdolescent(Adolescent teen) {
        adolescents.add(teen);
    }

    public List<Adolescent> getAdolescents() {
        return adolescents;
    }

    public void setRestriction(Adolescent adolescent, Restriction restriction) {
        System.out.println(name + " applied restriction on " + adolescent.getName() +
                ": " + restriction.getType() + " for " + restriction.getDuration() + " hours.");
        adolescent.addRestriction(restriction);
    }

    public void triggerSecurityAlert(Adolescent adolescent, String notificationType) {
        System.out.println("[ALERTE] Risque de sécurité détecté pour " + adolescent.getName() +
                " via " + notificationType.toUpperCase());
    }
}
