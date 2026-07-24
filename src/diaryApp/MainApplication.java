package diaryApp;

import java.util.List;
import java.util.Scanner;

public class MainApplication {

    private final Diaries diaries;
    private final Scanner scanner;

    public MainApplication() {
        this.diaries = new Diaries();
        this.scanner = new Scanner(System.in);
    }

    static void main(String[] args) {
        MainApplication app = new MainApplication();
        app.run();
    }

    // ----- Main menu -----

    public void run() {
        boolean running = true;

        System.out.println("=== Welcome to the Diary App ===");

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addDiary();
                    break;
                case "2":
                    openDiary();
                    break;
                case "3":
                    deleteDiary();
                    break;
                case "4":
                    listDiaries();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("----- Main Menu -----");
        System.out.println("1. Add a new diary");
        System.out.println("2. Open a diary");
        System.out.println("3. Delete a diary");
        System.out.println("4. List all diaries");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // ----- Main menu actions -----

    private void addDiary() {
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter a new password: ");
        String password = scanner.nextLine().trim();

        try {
            diaries.add(username, password);
            System.out.println("Diary created for '" + username + "'.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not create diary: " + e.getMessage());
        }
    }

    private void openDiary() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        Diary diary = diaries.findByUsername(username);

        if (diary == null) {
            System.out.println("No diary found for username '" + username + "'.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        diary.unlockDiary(password);

        if (diary.isLocked()) {
            System.out.println("Wrong password. Diary is still locked.");
            return;
        }

        System.out.println("Diary unlocked. Welcome, " + username + "!");
        diaryMenu(diary);
    }

    private void deleteDiary() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        diaries.delete(username, password);

        if (diaries.findByUsername(username) == null) {
            System.out.println("Diary '" + username + "' deleted (if it existed and the password was correct).");
        } else {
            System.out.println("Could not delete diary. Check the username and password.");
        }
    }

    private void listDiaries() {
        List<Diary> allDiaries = diaries.getDiaries();

        if (allDiaries.isEmpty()) {
            System.out.println("There are no diaries yet.");
            return;
        }

        System.out.println("----- All Diaries -----");
        for (Diary diary : allDiaries) {
            String status;
            if (diary.isLocked()) {
                status = "locked";
            } else {
                status = "unlocked";
            }
            System.out.println("- " + diary.getUsername() + " (" + status + ")");
        }
    }

    // ----- Diary sub-menu (once a diary is unlocked and open) -----

    private void diaryMenu(Diary diary) {
        boolean inDiary = true;

        while (inDiary) {
            printDiaryMenu(diary.getUsername());
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createEntry(diary);
                    break;
                case "2":
                    listEntries(diary);
                    break;
                case "3":
                    findEntry(diary);
                    break;
                case "4":
                    updateEntry(diary);
                    break;
                case "5":
                    deleteEntry(diary);
                    break;
                case "6":
                    diary.lockDiary();
                    System.out.println("Diary locked.");
                    inDiary = false;
                    break;
                case "0":
                    inDiary = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void printDiaryMenu(String username) {
        System.out.println();
        System.out.println("----- " + username + "'s Diary -----");
        System.out.println("1. Create a new entry");
        System.out.println("2. List all entries");
        System.out.println("3. Find an entry by id");
        System.out.println("4. Update an entry");
        System.out.println("5. Delete an entry");
        System.out.println("6. Lock diary and return to main menu");
        System.out.println("0. Return to main menu (stay unlocked)");
        System.out.print("Choose an option: ");
    }

    // ----- Diary sub-menu actions -----

    private void createEntry(Diary diary) {
        System.out.print("Entry title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Entry body: ");
        String body = scanner.nextLine().trim();

        diary.createEntry(title, body);
        System.out.println("Entry created.");
    }

    private void listEntries(Diary diary) {
        List<Entry> entries = diary.getEntries();

        if (entries.isEmpty()) {
            System.out.println("This diary has no entries yet.");
            return;
        }

        System.out.println("----- Entries -----");
        for (Entry entry : entries) {
            System.out.println(entry);
        }
    }

    private void findEntry(Diary diary) {
        Integer id = readEntryId();
        if (id == null) {
            return;
        }

        Entry entry = diary.findEntryById(id);
        if (entry == null) {
            System.out.println("No entry found with id " + id + ".");
        } else {
            System.out.println(entry);
        }
    }

    private void updateEntry(Diary diary) {
        Integer id = readEntryId();
        if (id == null) {
            return;
        }

        if (diary.findEntryById(id) == null) {
            System.out.println("No entry found with id " + id + ".");
            return;
        }

        System.out.print("New title: ");
        String title = scanner.nextLine().trim();
        System.out.print("New body: ");
        String body = scanner.nextLine().trim();

        diary.updateEntry(id, title, body);
        System.out.println("Entry updated.");
    }

    private void deleteEntry(Diary diary) {
        Integer id = readEntryId();
        if (id == null) {
            return;
        }

        if (diary.findEntryById(id) == null) {
            System.out.println("No entry found with id " + id + ".");
            return;
        }

        diary.deleteEntry(id);
        System.out.println("Entry deleted.");
    }

    // ----- Small helper -----

    private Integer readEntryId() {
        System.out.print("Entry id: ");
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number.");
            return null;
        }
    }
}