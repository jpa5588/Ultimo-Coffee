// Places this interface in the repository package.
package com.example.ultimo_coffee.repository;

// Imports the model this repository reads and saves.
import com.example.ultimo_coffee.model.MenuItem;

// Provides standard database operations such as save, findAll, and findById.
import org.springframework.data.jpa.repository.JpaRepository;

// Identifies this as part of the database access layer.
import org.springframework.stereotype.Repository;

// Holds multiple menu items returned by a query.
import java.util.List;

/**
 * Provides database access for menu items.
 *
 * JpaRepository<MenuItem, Long> means:
 * - MenuItem is the entity managed by this repository.
 * - Long is the type of its primary key, matching MenuItem.id.
 *
 * Spring Data provides the standard operations inherited from JpaRepository.
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    /**
     * Returns all menu items, including unavailable items.
     *
     * Results are sorted by category first, then by name within each category.
     *
     * Spring Data builds the query from the method name:
     * - findAllBy: retrieves all items without a filtering condition.
     * - OrderBy: specifies the result ordering.
     * - CategoryAsc: sorts category in ascending order.
     * - NameAsc: sorts name in ascending order within each category.
     *
     * List<MenuItem> is the collection returned to the calling service.
     */
    List<MenuItem> findAllByOrderByCategoryAscNameAsc();
}