package parental;

public class Parent {
    private String name;
    private String email;

    public Parent(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void displayInfo() {
        System.out.println("👨 Parent: " + name + " | Email: " + email);
    }

    public void setRestriction(Adolescent adolescent, Restriction restriction) {
        System.out.println(name + " applied restriction on " + adolescent.getName() +
                ": " + restriction.getType() + " for " + restriction.getDuration() + " hours.");
        adolescent.addRestriction(restriction);
    }
}