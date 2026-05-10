/**
 * Abstract category class for expenses.
 * Author: James Paek
 */
public abstract class Category {
    protected String categoryName;

    /**
     * Creates a category.
     *
     * @param categoryName name of the category
     */
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Returns the category name.
     *
     * @return category name
     */
    public String getCategoryName() {
        return categoryName;
    }
}
