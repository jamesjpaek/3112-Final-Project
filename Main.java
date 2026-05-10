import java.util.Scanner;

/**
 * Runs the console menu for the personal expense tracker.
 * Author: James Paek
 */
public class Main {
    private static Scanner input = new Scanner(System.in);

    /**
     * Starts the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        boolean running = true;

        System.out.println("Personal Expense Tracker");

        while (running) {
            printMenu();
            int choice = getIntInput("Choose an option: ");

            if (choice == 1) {
                addExpense(tracker);
            } else if (choice == 2) {
                tracker.viewExpenses();
            } else if (choice == 3) {
                System.out.printf("Total spending: $%.2f\n", tracker.getTotalSpending());
            } else if (choice == 4) {
                viewCategoryTotal(tracker);
            } else if (choice == 5) {
                tracker.saveData();
            } else if (choice == 6) {
                tracker.saveData();
                running = false;
                System.out.println("Goodbye.");
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Prints the main menu.
     */
    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. View Total Spending");
        System.out.println("4. View Spending By Category");
        System.out.println("5. Save Data");
        System.out.println("6. Save and Exit");
    }

    /**
     * Adds an expense using user input.
     *
     * @param tracker expense tracker object
     */
    private static void addExpense(ExpenseTracker tracker) {
        System.out.print("Expense name: ");
        String name = input.nextLine();

        double amount = getDoubleInput("Amount: ");

        System.out.print("Date (example: 05/08/2026): ");
        String date = input.nextLine();

        Category category = chooseCategory();
        Expense expense = new Expense(name, amount, date, category);
        tracker.addExpense(expense);
    }

    /**
     * Allows the user to select a category.
     *
     * @return selected category object
     */
    private static Category chooseCategory() {
        System.out.println("\nChoose category:");
        System.out.println("1. Food");
        System.out.println("2. Transportation");
        System.out.println("3. Entertainment");
        System.out.println("4. Other");

        int choice = getIntInput("Category option: ");

        if (choice == 1) {
            return new FoodCategory();
        } else if (choice == 2) {
            return new TransportationCategory();
        } else if (choice == 3) {
            return new EntertainmentCategory();
        } else {
            return new OtherCategory();
        }
    }

    /**
     * Displays the total for a selected category.
     *
     * @param tracker expense tracker object
     */
    private static void viewCategoryTotal(ExpenseTracker tracker) {
        Category category = chooseCategory();
        double total = tracker.getSpendingByCategory(category.getCategoryName());
        System.out.printf("Total for %s: $%.2f\n", category.getCategoryName(), total);
    }

    /**
     * Gets a valid integer from the user.
     *
     * @param prompt text to show the user
     * @return integer input
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int number = Integer.parseInt(input.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    /**
     * Gets a valid double from the user.
     *
     * @param prompt text to show the user
     * @return double input
     */
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double number = Double.parseDouble(input.nextLine());

                if (number < 0) {
                    System.out.println("Amount cannot be negative.");
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}
