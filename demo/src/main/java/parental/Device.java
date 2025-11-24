package parental;

/**
 * A basic device class implementing Controllable.
 */
public class Device implements Controllable {

    private final String name;
    private final String os;
    private boolean restricted = false;

    public Device(String name, String os) {
        this.name = name;
        this.os = os;
    }

    @Override
    public void applyRestriction() {
        restricted = true;
        System.out.println("Restriction ON for device " + name);
    }

    @Override
    public void removeRestriction() {
        restricted = false;
        System.out.println("Restrictions OFF for device " + name);
    }

    public void showDeviceInfo() {
        System.out.println("Device: " + name + " | OS=" + os + " | Restricted=" + (restricted ? "YES" : "NO"));
    }

    public boolean isRestricted() {
        return restricted;
    }
}
