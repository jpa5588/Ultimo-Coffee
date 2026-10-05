// Places this implementation in the service package.
package com.example.ultimo_coffee.service;

// Imports the menu item model.
import com.example.ultimo_coffee.model.MenuItem;

// Imports the repository used to access the database.
import com.example.ultimo_coffee.repository.MenuItemRepository;

// Allows Spring to supply the repository dependency.
import org.springframework.beans.factory.annotation.Autowired;

// Marks this class as a service managed by Spring.
import org.springframework.stereotype.Service;

// Holds multiple menu items.
import java.util.List;

// Represents a result that may contain an item or may be empty.
import java.util.Optional;

/**
 * Implements the operations declared by MenuItemService.
 *
 * Controllers call the service.
 * The service calls the repository to read or save database records.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    // Spring supplies the repository object when creating this service.
    // This follows the @Autowired pattern in your CourseServiceImpl.
    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * Retrieves all menu items, including unavailable items.
     */
    // Confirms that this method implements a method from the interface.
    @Override
    public List<MenuItem> getAllMenuItems() {

        // Calls the sorting method declared in MenuItemRepository.
        // Returns items ordered by category, then name.
        return menuItemRepository.findAllByOrderByCategoryAscNameAsc();
    }

    /**
     * Saves the supplied menu item.
     */
    @Override
    public void saveMenuItem(MenuItem menuItem) {

        // A new item has a null ID, so the repository creates a record.
        // An existing item's ID identifies the record being updated.
        menuItemRepository.save(menuItem);
    }

    /**
     * Retrieves one menu item by ID.
     */
    @Override
    public MenuItem getMenuItemById(long id) {

        // Requests the item from the repository.
        // Optional contains the item when found, or is empty when not found.
        Optional<MenuItem> optional = menuItemRepository.findById(id);

        // Checks whether a matching item was found.
        if (optional.isPresent()) {

            // Extracts and returns the item from the Optional.
            return optional.get();
        }

        // Stops the operation if the requested item does not exist.
        // This follows the missing-record check in your course service.
        throw new RuntimeException("Menu item not found for ID: " + id);
    }

    // Implements getAvailableMenuItems() from the service interface.
    @Override
    public List<MenuItem> getAvailableMenuItems() {

        // Requests the available items from the repository.
        // The repository retrieves these records from MySQL.
        return menuItemRepository.findByAvailableTrueOrderByCategoryAscNameAsc();
    }
}