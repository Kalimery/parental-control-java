package parental;

import java.util.Scanner;

public class DemoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parent info
        System.out.print("Enter parent name: ");
        String parentName = scanner.nextLine();
        System.out.print("Enter parent email: ");
        String parentEmail = scanner.nextLine();
        Parent parent = new Parent(parentName, parentEmail);

        // Adolescent info
        System.out.print("Enter adolescent name: ");
        String teenName = scanner.nextLine();
        System.out.print("Enter adolescent age: ");
        int teenAge = Integer.parseInt(scanner.nextLine());
        Adolescent teen = new Adolescent(teenName, teenAge);

        // Device info
        System.out.print("Enter device name: ");
        String deviceName = scanner.nextLine();
        System.out.print("Enter device OS: ");
        String deviceOS = scanner.nextLine();
        Device device = new Device(deviceName, deviceOS);

        // Settings info
        System.out.print("Is Night Mode ON? (true/false): ");
        boolean nightMode = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Are Notifications ON? (true/false): ");
        boolean notifications = Boolean.parseBoolean(scanner.nextLine());
        Settings settings = new Settings(nightMode, notifications);

        // Display info
        parent.displayInfo();
        device.showDeviceInfo();
        settings.displaySettings();

        // Restrictions
        System.out.print("Enter first restriction (name): ");
        String r1Name = scanner.nextLine();
        System.out.print("Enter first restriction duration (hours): ");
        int r1Duration = Integer.parseInt(scanner.nextLine());
        Restriction r1 = new Restriction(r1Name, r1Duration);

        System.out.print("Enter second restriction (name): ");
        String r2Name = scanner.nextLine();
        System.out.print("Enter second restriction duration (hours): ");
        int r2Duration = Integer.parseInt(scanner.nextLine());
        Restriction r2 = new Restriction(r2Name, r2Duration);

        // Apply restrictions
        parent.setRestriction(teen, r1);
        parent.setRestriction(teen, r2);

        // Show restrictions
        teen.showRestrictions();

        // Apply restrictions to device
        device.applyRestriction();

        scanner.close();
    }
}
