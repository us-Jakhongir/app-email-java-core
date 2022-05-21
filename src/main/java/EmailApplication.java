import java.util.Scanner;

public class EmailApplication {

    public static Scanner scanner;
    public static User[] users;
    public static Email[] emails;
    public static Integer index = 0;
    public static Integer indexEmail = 0;

    public static User onlineUser;

    public static void main(String[] args) {
        users = new User[10000];
        emails = new Email[10000];

        while (true) {
            showMainMenu();
            scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    signIn();
                    break;
                case 2:
                    signOn();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     *
     */

    private static void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Sign In");   // logging in
        System.out.println("2. Sign On");   // registering
        System.out.println("0. Exit");
    }

    private static void signOn() {
        scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("Firstname: ");
        String firstname = scanner.next();
        System.out.println("Lastname: ");
        String lastname = scanner.next();

        System.out.println("Password: ");
        String password = scanner.next();
        while (true) {
            System.out.println("Retype Password: ");
            String retype_password = scanner.next();
            if (password.equals(retype_password))
                break;
        }

        while (true) {
            System.out.println("Email Address (alex@gmail.com): ");
            String email = scanner.next();
            boolean validateEmail = user.validateEmail(email);
            if (validateEmail) {
                user.setEmail(email);
                break;
            } else {
                System.out.println("Email Not Valid");
            }
        }


        /**
         *    isEmpty => ""
         *    isBlank => "      "
         *
         */

        if (!firstname.isEmpty() && !lastname.isEmpty() && (!password.isEmpty())) {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setPassword(password);
        }
        users[index++] = user;
        System.out.println("Successfully Registered!\n");
    }

    private static void signIn() {

        scanner = new Scanner(System.in);


        System.out.println("Email: ");
        String email = scanner.next();

        for (User user : users) {
            if (user != null) {
                if (user.getEmail().equals(email))
                    onlineUser = user;
            }
        }

        boolean success = false;
        if (onlineUser != null) {
            System.out.println("Password: ");
            String password = scanner.next();
            if (onlineUser.getPassword().equals(password))
                success = true;
        }

        if (success) {
            System.out.println("Welcome To Gmail");
            countOfUnread();
            showEmailMenu();
        }
    }

    private static void countOfUnread() {
        int count = 0;
        for (Email email : emails) {
            if (email != null) {
                if (email.getReceiver().equals(onlineUser) && !email.isRead())
                    count++;
            }
        }
        System.out.println("You have " + count + " unread message(s)");
    }


    private static void showEmailMenu() {

        scanner = new Scanner(System.in);


        System.out.println("1. Send");
        System.out.println("2. Unread");
        System.out.println("3. Inbox");
        System.out.println("4. Outbox");
        System.out.println("0. Sign Out");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                send();
                break;

            case 2:
                unread();
                break;

            case 3:
                inbox();
                break;

            case 4:
                outbox();
                break;
            case 0:
                break;
        }


    }

    private static void outbox() {
        for (Email email : emails) {
            if (email != null) {
                if (email.getSender().equals(onlineUser)) {
                    System.out.println("-------------------------------------");
                    System.out.println("Receiver: " + email.getReceiver().getEmail());
                    System.out.println("Title: " + email.getTitle());
                    System.out.println("Message: " + email.getBody());
                }
            }
        }


    }

    private static void inbox() {
        for (Email email : emails) {
            if (email != null) {
                if (email.getReceiver().equals(onlineUser)) {

                    System.out.println("---------------------------------------");
                    System.out.println("Sender: " + email.getSender().getEmail());
                    System.out.println("Title: " + email.getTitle());
                    System.out.println("Message: " + email.getBody());
                }

            }
        }

    }

    private static void unread() {
        for (Email email : emails) {
            if (email != null && !email.isRead()) {
                System.out.println("Sender: " + email.getSender().getEmail());
                System.out.println("Message title: " + email.getTitle());
                System.out.println("Message: " + email.getBody());
                email.setRead(true);
            }
        }
    }

    private static void send() {
        System.out.println("===========Email Addresses=========");
        for (User user : users) {
            if (user != null) {
                if (!user.equals(onlineUser)) {
                    System.out.println(user.getEmail());
                }
            }
        }
        System.out.println("=================================");
        scanner = new Scanner(System.in);
        System.out.println("To: ");
        String receiverEmailAddress = scanner.next();
        User receiver = null;
        for (User user : users) {
            if (user != null) {
                if (user.getEmail().equals(receiverEmailAddress))
                    receiver = user;
            }
        }

        System.out.println("Subject");
        scanner = new Scanner(System.in);
        String subject = scanner.nextLine();
        System.out.println("Message");
        scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        emails[indexEmail++] = new Email(subject, message, onlineUser, receiver, false);
        System.out.println("Successfully sent!\n");
    }


    private static void findUnreadEmails() {
        int count = 0;
        for (Email email : emails) {
            if (email != null) {
                for (User user : users) {
                    if (!user.getEmail().equals(email.getSender())) {
                        System.out.println("Your unread email: " + count);
                        break;
                    }
                }
                count++;

            }

        }


    }


}

