import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading expense data.
 * Author: James Paek
 */
public class FileManager {
    private String fileName;

    /**
     * Creates a file manager.
     *
     * @param fileName name of the data file
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves all expenses to the data file.
     *
     * @param expenses list of expenses to save
     */
    public void saveExpenses(ArrayList<Expense> expenses) {
        try {
            FileWriter writer = new FileWriter(fileName);

            for (Expense expense : expenses) {
                writer.write(expense.toFileString() + "\n");
            }

            writer.close();
            System.out.println("Expenses saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Could not save expenses.");
        }
    }

    /**
     * Loads expenses from the data file.
     *
     * @return list of saved expenses
     */
    public ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return expenses;
        }

        try {
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String name = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String date = parts[2];
                    Category category = createCategory(parts[3]);

                    expenses.add(new Expense(name, amount, date, category));
                }
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Data file was not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Saved data has an invalid number.");
        }

        return expenses;
    }

    /**
     * Creates the correct category object from text.
     *
     * @param categoryName category name from file
     * @return matching category object
     */
    private Category createCategory(String categoryName) {
        if (categoryName.equalsIgnoreCase("Food")) {
            return new FoodCategory();
        } else if (categoryName.equalsIgnoreCase("Transportation")) {
            return new TransportationCategory();
        } else if (categoryName.equalsIgnoreCase("Entertainment")) {
            return new EntertainmentCategory();
        } else {
            return new OtherCategory();
        }
    }
}
