/**
 * Represents one personal expense.
 * Author: James Paek
 */
public class Expense {
    private String name;
    private double amount;
    private String date;
    private Category category;

    /**
     * Creates an expense object.
     *
     * @param name expense name
     * @param amount expense amount
     * @param date expense date
     * @param category expense category
     */
    public Expense(String name, double amount, String date, Category category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    /**
     * Converts the expense into a file-safe format.
     *
     * @return expense data as one line
     */
    public String toFileString() {
        return name + "," + amount + "," + date + "," + category.getCategoryName();
    }

    @Override
    public String toString() {
        return date + " | " + name + " | " + category.getCategoryName()
                + " | $" + String.format("%.2f", amount);
    }
}
