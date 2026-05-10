import java.util.ArrayList;

/**
 * Main system class for the personal expense tracker.
 * Author: James Paek
 */
public class ExpenseTracker {
    private ArrayList<Expense> expenses;
    private FileManager fileManager;

    /**
     * Creates an expense tracker and loads saved data.
     */
    public ExpenseTracker() {
        fileManager = new FileManager("expenses.txt");
        expenses = fileManager.loadExpenses();
    }

    /**
     * Adds a new expense to the tracker.
     *
     * @param expense expense to add
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added successfully.");
    }

    /**
     * Displays every recorded expense.
     */
    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        System.out.println("\n--- All Expenses ---");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    /**
     * Calculates total spending.
     *
     * @return total amount spent
     */
    public double getTotalSpending() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    /**
     * Calculates spending for one category.
     *
     * @param categoryName selected category name
     * @return total amount spent in that category
     */
    public double getSpendingByCategory(String categoryName) {
        double total = 0;

        for (Expense expense : expenses) {
            if (expense.getCategory().getCategoryName().equalsIgnoreCase(categoryName)) {
                total += expense.getAmount();
            }
        }

        return total;
    }

    /**
     * Saves all current expense data.
     */
    public void saveData() {
        fileManager.saveExpenses(expenses);
    }
}
