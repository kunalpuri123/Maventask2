import java.util.HashMap;
import java.util.Scanner;

public class LoginAutomation {
    private HashMap<String, String> users; // Stores usernames and passwords

    public LoginAutomation() {
        users = new HashMap<>();
        initializeUsers();
    }

    private void initializeUsers() {
        // Add default users
        users.put("admin", "admin123");
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    public String login(String username, String password) {
        if (!users.containsKey(username)) {
            return "Error: Username not found.";
        }
        if (!users.get(username).equals(password)) {
            return "Error: Incorrect password.";
        }
        return "Login successful! Welcome, " + username + "!";
    }

    public String register(String username, String password) {
        if (users.containsKey(username)) {
            return "Error: Username already exists.";
        }
        users.put(username, password);
        return "Registration successful! You can now log in.";
    }

    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Login System ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.println(loginSystem.login(username, password));
                    break;

                case 2:
                    System.out.print("Enter a new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter a new password: ");
                    String newPassword = scanner.nextLine();
                    System.out.println(loginSystem.register(newUsername, newPassword));
                    break;

                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
