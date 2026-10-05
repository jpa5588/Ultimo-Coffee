// Places this interface in the service package.
package com.example.ultimo_coffee.service;

// Imports the menu item model used by these operations.
import com.example.ultimo_coffee.model.MenuItem;

// Allows an operation to return multiple menu items.
import java.util.List;

/**
 * Defines the operations available for managing menu items.
 *
 * This interface declares what the service can do.
 * MenuItemServiceImpl contains the code that performs these operations.
 */
public interface MenuItemService {

    // Returns all menu items for display on the management page.
    List<MenuItem> getAllMenuItems();

    // Saves a new menu item or updates an existing item.
    // void means this method does not return a value.
    void saveMenuItem(MenuItem menuItem);

    // Retrieves one menu item using its database ID.
    // The controller will use this to load an item for editing.
    MenuItem getMenuItemById(long id);

    // Returns the menu items currently available to order.
    // The POS controller will call this method.
    List<MenuItem> getAvailableMenuItems();
}