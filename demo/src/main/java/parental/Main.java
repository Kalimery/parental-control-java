package parental;

import java.util.List;
import java.util.Scanner;

/**
 * Interactive console menu. Friendly and simple to use.
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Parent parent = null;
            Device device = null;
            Settings settings = null;

            while (true) {
                System.out.println("\n=== PARENTAL CONTROL MENU ===");
                System.out.println("1. Create Parent & Adolescent");
                System.out.println("2. Create Device & Settings");
                System.out.println("3. Add Restriction");
                System.out.println("4. Show Info");
                System.out.println("5. Apply Device Restriction");
                System.out.println("6. Update Settings");
                System.out.println("7. Set & Check Quiet Hours");
                System.out.println("8. Send Security Alert");
                System.out.println("9. Exit");
                System.out.print("Choose: ");

                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                int choice;
                try {
                    choice = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1 -> {
                        System.out.print("Parent name: ");
                        String pname = scanner.nextLine().trim();
                        System.out.print("Parent email: ");
                        String pemail = scanner.nextLine().trim();
                        parent = new Parent(pname, pemail);

                        System.out.print("Adolescent name: ");
                        String tname = scanner.nextLine().trim();
                        System.out.print("Adolescent age: ");
                        int age = safeInt(scanner);
                        parent.addAdolescent(new Adolescent(tname, age));
                        System.out.println("Parent + adolescent created.");
                    }
                    case 2 -> {
                        System.out.print("Device name: ");
                        String d = scanner.nextLine().trim();
                        System.out.print("OS: ");
                        String os = scanner.nextLine().trim();
                        device = new Device(d, os);

                        System.out.print("Night mode (true/false): ");
                        boolean nm = Boolean.parseBoolean(scanner.nextLine().trim());
                        System.out.print("Notifications ON (true/false): ");
                        boolean notif = Boolean.parseBoolean(scanner.nextLine().trim());
                        settings = new Settings(nm, notif);

                        System.out.println("Device + settings created.");
                    }
                    case 3 -> {
                        if (parent == null) { System.out.println("Parent not created."); break; }
                        List<Adolescent> teens = parent.getAdolescents();
                        if (teens.isEmpty()) { System.out.println("No adolescents available."); break; }
                        printTeens(teens);
                        System.out.print("Choose adolescent: ");
                        Adolescent selected = safeSelect(scanner, teens);
                        if (selected == null) break;

                        System.out.print("Restriction type: ");
                        String rtype = scanner.nextLine().trim();
                        System.out.print("Duration (hours): ");
                        int rdur = safeInt(scanner);

                        parent.setRestriction(selected, new Restriction(rtype, rdur));
                    }
                    case 4 -> {
                        if (parent != null) parent.displayInfo();
                        if (device != null) device.showDeviceInfo();
                        if (settings != null) settings.displaySettings();
                    }
                    case 5 -> {
                        if (device != null) device.applyRestriction();
                        else System.out.println("No device created yet.");
                    }
                    case 6 -> {
                        if (settings != null) {
                            System.out.print("Night mode (true/false): ");
                            settings.setNightMode(Boolean.parseBoolean(scanner.nextLine().trim()));
                            System.out.print("Notifications (true/false): ");
                            settings.setNotificationsEnabled(Boolean.parseBoolean(scanner.nextLine().trim()));
                            System.out.println("Settings updated.");
                        } else {
                            System.out.println("Create device + settings first.");
                        }
                    }
                    case 7 -> {
                        if (parent == null) { System.out.println("Parent not created."); break; }
                        List<Adolescent> teens = parent.getAdolescents();
                        if (teens.isEmpty()) { System.out.println("No adolescents available."); break; }
                        printTeens(teens);
                        System.out.print("Choose adolescent: ");
                        Adolescent selected = safeSelect(scanner, teens);
                        if (selected == null) break;

                        System.out.print("Quiet start (0..23): ");
                        int qs = safeInt(scanner);
                        System.out.print("Quiet end (0..23): ");
                        int qe = safeInt(scanner);

                        selected.setQuietHours(qs, qe);

                        System.out.print("Check hour (0..23): ");
                        int h = safeInt(scanner);
                        System.out.println(selected.isAccessAllowed(h) ? "Access allowed." : "Access blocked.");
                    }
                    case 8 -> {
                        if (parent == null) { System.out.println("Parent not created."); break; }
                        List<Adolescent> teens = parent.getAdolescents();
                        if (teens.isEmpty()) { System.out.println("No adolescents available."); break; }
                        printTeens(teens);
                        System.out.print("Choose adolescent: ");
                        Adolescent selected = safeSelect(scanner, teens);
                        if (selected == null) break;

                        System.out.print("Alert type (sms/email/push): ");
                        String alert = scanner.nextLine().trim();
                        parent.triggerSecurityAlert(selected, alert);
                    }
                    case 9 -> {
                        System.out.println("Goodbye.");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        }
    }

    private static void printTeens(List<Adolescent> teens) {
        for (int i = 0; i < teens.size(); i++) {
            System.out.println((i + 1) + ". " + teens.get(i).getName());
        }
    }

    private static Adolescent safeSelect(Scanner scanner, List<Adolescent> teens) {
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (idx < 0 || idx >= teens.size()) {
                System.out.println("Invalid selection.");
                return null;
            }
            return teens.get(idx);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return null;
        }
    }

    private static int safeInt(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }
}