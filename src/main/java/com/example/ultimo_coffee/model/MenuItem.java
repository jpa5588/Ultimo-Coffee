// Places this model in the Ultimo Coffee model package.
package com.example.ultimo_coffee.model;

// Imports the annotations used to map Java fields to database columns.
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Stores prices as decimal values.
import java.math.BigDecimal;

/**
 * Represents one item on the Ultimo Coffee menu.
 *
 * One saved MenuItem corresponds to one row in the menu_items table.
 *
 * Examples include a latte, tea, or sandwich.
 *
 * Private fields hold the item's data.
 * Getters allow other classes to read that data.
 * Setters allow other classes to update that data.
 */

// Marks this class as an entity that JPA can store in the database.
@Entity

// Specifies the table used in your configured ultimo_coffee_db database.
@Table(name = "menu_items")
public class MenuItem {

    // Marks id as the primary key identifying each menu item.
    @Id

    // Uses the database's identity column to generate the ID.
    // In MySQL, this corresponds to an AUTO_INCREMENT column.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Long allows a new item's ID to be null before it is saved.
    private Long id;

    // Maps name to a required database column with a maximum length of 80.
    @Column(name = "name", nullable = false, length = 80)
    private String name;

    // Stores the category code, such as COFFEE, TEA, or FOOD.
    // The column is required and holds up to 20 characters.
    @Column(name = "category", nullable = false, length = 20)
    private String category;

    // Stores an optional description of up to 250 characters.
    @Column(name = "description", length = 250)
    private String description;

    // Stores the base price before size or customization charges.
    // precision = 10 allows 10 total digits.
    // scale = 2 reserves two digits after the decimal point.
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // Determines whether tax applies to this item.
    // New Java objects start with true; the menu form can change it.
    @Column(name = "taxable", nullable = false)
    private boolean taxable = true;

    // Determines whether employees can currently order this item.
    // New Java objects start as available.
    @Column(name = "available", nullable = false)
    private boolean available = true;

    // Stores the size codes used by the existing prototype.
    // Example: "REGULAR,LARGE".
    // A new item initially offers only REGULAR.
    @Column(name = "allowed_sizes", nullable = false, length = 80)
    private String allowedSizes = "REGULAR";

    // Stores the customization codes used by the existing prototype.
    // Example: "OAT_MILK,EXTRA_SHOT".
    // An empty string means the item offers no customizations.
    @Column(name = "allowed_options", nullable = false, length = 180)
    private String allowedOptions = "";

    /**
     * Creates an empty menu item.
     *
     * JPA needs a constructor with no arguments to create entity objects
     * when loading database records.
     *
     * The form controller can also use new MenuItem() for an add-item form.
     */
    public MenuItem() {
    }

    // Returns this item's database ID.
    public Long getId() {
        return id;
    }

    // Assigns the supplied ID to this object's id field.
    // "this.id" is the field; "id" is the method parameter.
    public void setId(Long id) {
        this.id = id;
    }

    // Returns the menu item's name.
    public String getName() {
        return name;
    }

    // Updates the menu item's name.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the category code.
    public String getCategory() {
        return category;
    }

    // Updates the category code.
    public void setCategory(String category) {
        this.category = category;
    }

    // Returns the description.
    public String getDescription() {
        return description;
    }

    // Updates the description.
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns the base price.
    public BigDecimal getPrice() {
        return price;
    }

    // Updates the base price.
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Returns whether this item is taxable.
    // Boolean getters commonly use "is" instead of "get".
    public boolean isTaxable() {
        return taxable;
    }

    // Updates whether tax applies.
    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    // Returns whether the item is available to order.
    public boolean isAvailable() {
        return available;
    }

    // Updates the item's availability.
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Returns the stored size codes.
    public String getAllowedSizes() {
        return allowedSizes;
    }

    // Updates the stored size codes.
    public void setAllowedSizes(String allowedSizes) {
        this.allowedSizes = allowedSizes;
    }

    // Returns the stored customization codes.
    public String getAllowedOptions() {
        return allowedOptions;
    }

    // Updates the stored customization codes.
    public void setAllowedOptions(String allowedOptions) {
        this.allowedOptions = allowedOptions;
    }
}