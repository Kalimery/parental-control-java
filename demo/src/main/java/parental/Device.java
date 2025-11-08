package parental;

public class Device {
    private String name;
    private String os;
    private boolean restricted;

    public Device(String name, String os) {
        this.name = name;
        this.os = os;
        this.restricted = false;
    }

    public void applyRestriction() {
        this.restricted = true;
        System.out.println("🔒 Restriction activated on device: " + name);
    }

    public void removeRestriction() {
        this.restricted = false;
        System.out.println("✅ Restrictions removed from device: " + name);
    }

    public void showDeviceInfo() {
        System.out.println("Device: " + name + " | OS: " + os +
                " | Restricted: " + (restricted ? "Yes" : "No"));
    }
}