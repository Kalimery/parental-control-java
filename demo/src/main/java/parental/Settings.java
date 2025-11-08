package parental;

public class Settings {
    private boolean nightMode;
    private boolean notificationsEnabled;

    public Settings(boolean nightMode, boolean notificationsEnabled) {
        this.nightMode = nightMode;
        this.notificationsEnabled = notificationsEnabled;
    }

    public void displaySettings() {
        System.out.println("⚙️ Settings:");
        System.out.println("   - Night Mode: " + (nightMode ? "ON" : "OFF"));
        System.out.println("   - Notifications: " + (notificationsEnabled ? "ON" : "OFF"));
    }
}