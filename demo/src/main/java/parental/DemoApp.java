package parental;

import java.util.List;
import java.util.Scanner;

public class DemoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parent parent = null;
        Device device = null;
        Settings settings = null;

        while(true) {
            System.out.println("\n=== Parental Control Menu ===");
            System.out.println("1. Create Parent & Adolescent");
            System.out.println("2. Create Device & Settings");
            System.out.println("3. Add Restriction");
            System.out.println("4. Show Info");
            System.out.println("5. Apply Restrictions to Device");
            System.out.println("6. Toggle Settings");
            System.out.println("7. Set & Check Quiet Hours");
            System.out.println("8. Trigger Security Alert");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1:
                    System.out.print("Enter parent name: ");
                    String pName = scanner.nextLine();
                    System.out.print("Enter parent email: ");
                    String pEmail = scanner.nextLine();
                    parent = new Parent(pName, pEmail);

                    System.out.print("Enter adolescent name: ");
                    String tName = scanner.nextLine();
                    System.out.print("Enter adolescent age: ");
                    int tAge = Integer.parseInt(scanner.nextLine());
                    Adolescent teen = new Adolescent(tName, tAge);
                    parent.addAdolescent(teen);
                    System.out.println("Parent and adolescent created!");
                    break;

                case 2:
                    System.out.print("Enter device name: ");
                    String dName = scanner.nextLine();
                    System.out.print("Enter device OS: ");
                    String dOS = scanner.nextLine();
                    device = new Device(dName, dOS);

                    System.out.print("Is Night Mode ON? (true/false): ");
                    boolean night = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Are Notifications ON? (true/false): ");
                    boolean notif = Boolean.parseBoolean(scanner.nextLine());
                    settings = new Settings(night, notif);
                    System.out.println("Device and settings created!");
                    break;

                case 3:
                    if(parent == null || parent.getAdolescents().isEmpty()) {
                        System.out.println("Create parent and adolescent first!");
                        break;
                    }
                    System.out.println("Choose adolescent:");
                    List<Adolescent> teens = parent.getAdolescents();
                    for(int i=0; i<teens.size(); i++) {
                        System.out.println((i+1)+". "+teens.get(i).getName());
                    }
                    int idx = Integer.parseInt(scanner.nextLine())-1;
                    Adolescent selected = teens.get(idx);

                    System.out.print("Enter restriction name: ");
                    String rName = scanner.nextLine();
                    System.out.print("Enter restriction duration (hours): ");
                    int rDuration = Integer.parseInt(scanner.nextLine());
                    Restriction r = new Restriction(rName, rDuration);
                    parent.setRestriction(selected, r);
                    break;

                case 4:
                    if(parent != null) parent.displayInfo();
                    if(device != null) device.showDeviceInfo();
                    if(settings != null) settings.displaySettings();
                    break;

                case 5:
                    if(device != null) device.applyRestriction();
                    else System.out.println("Create device first!");
                    break;

                case 6:
                    if(settings != null) {
                        System.out.print("Toggle Night Mode (true/false): ");
                        settings.setNightMode(Boolean.parseBoolean(scanner.nextLine()));
                        System.out.print("Toggle Notifications (true/false): ");
                        settings.setNotificationsEnabled(Boolean.parseBoolean(scanner.nextLine()));
                        System.out.println("Settings updated!");
                    } else System.out.println("Create settings first!");
                    break;

                case 7:
                    if(parent == null || parent.getAdolescents().isEmpty()) {
                        System.out.println("Create adolescent first!");
                        break;
                    }
                    System.out.println("Choose adolescent:");
                    teens = parent.getAdolescents();
                    for(int i=0; i<teens.size(); i++) {
                        System.out.println((i+1)+". "+teens.get(i).getName());
                    }
                    idx = Integer.parseInt(scanner.nextLine())-1;
                    selected = teens.get(idx);

                    System.out.print("Enter quiet hours start (0-23): ");
                    int start = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter quiet hours end (0-23): ");
                    int end = Integer.parseInt(scanner.nextLine());
                    selected.setQuietHours(start, end);

                    System.out.print("Enter current hour (0-23) to check: ");
                    int hour = Integer.parseInt(scanner.nextLine());
                    if(selected.isAccessAllowed(hour)) System.out.println("Access allowed.");
                    else System.out.println("Access blocked (heures calmes).");
                    break;

                case 8:
                    if(parent != null && !parent.getAdolescents().isEmpty()) {
                        System.out.println("Choose adolescent:");
                        teens = parent.getAdolescents();
                        for(int i=0; i<teens.size(); i++) {
                            System.out.println((i+1)+". "+teens.get(i).getName());
                        }
                        idx = Integer.parseInt(scanner.nextLine())-1;
                        selected = teens.get(idx);

                        System.out.print("Enter notification type (email/SMS/push): ");
                        String notifType = scanner.nextLine();
                        parent.triggerSecurityAlert(selected, notifType);
                    }
                    break;

                case 9:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}