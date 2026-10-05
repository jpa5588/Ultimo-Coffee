// Places this class in the controller package.
package com.example.ultimo_coffee.controller;

// Imports the menu item model used by the form.
import com.example.ultimo_coffee.model.MenuItem;

// Imports the service interface the controller calls.
import com.example.ultimo_coffee.service.MenuItemService;

// Allows Spring to supply the service dependency.
import org.springframework.beans.factory.annotation.Autowired;

// Identifies this class as a controller that returns HTML views.
import org.springframework.stereotype.Controller;

// Holds data that Thymeleaf can access when rendering the page.
import org.springframework.ui.Model;

// Holds form binding errors, such as an invalid numeric price.
import org.springframework.validation.BindingResult;

// Handles requests that open a page.
import org.springframework.web.bind.annotation.GetMapping;

// Binds submitted form fields to a Java object.
// Also supports methods that prepare shared page data.
import org.springframework.web.bind.annotation.ModelAttribute;

// Reads a value from the URL, such as an item's ID.
import org.springframework.web.bind.annotation.PathVariable;

// Handles submitted forms.
import org.springframework.web.bind.annotation.PostMapping;

// Carries a temporary message across a redirect.
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Connects the menu page and form to MenuItemService.
 *
 * Responsibilities:
 * - Display the menu items.
 * - Prepare an empty form for a new item.
 * - Load an existing item for editing.
 * - Submit an item to the service for saving.
 */
@Controller
public class MenuItemController {

    // Spring supplies the implementation of MenuItemService.
    // The controller depends on the interface, like your course example.
    @Autowired
    private MenuItemService menuItemService;

    /**
     * Displays the menu table and an empty add-item form.
     */
    @GetMapping("/manage/menu")
    public String viewMenuPage(Model model) {

        // Retrieves the items through the service.
        // The HTML will access them using ${listMenuItems}.
        model.addAttribute("listMenuItems", menuItemService.getAllMenuItems());

        // Creates the empty object used by the add-item form.
        // The HTML will bind its fields to ${menuItem}.
        model.addAttribute("menuItem", new MenuItem());

        // Tells Thymeleaf to render templates/menu.html.
        return "menu";
    }

    /**
     * Opens a fresh add-item form when "New item" is selected.
     */
    @GetMapping("/manage/menu/new")
    public String showNewMenuItemForm(Model model) {

        // Reuses the method that prepares the list and empty form.
        return viewMenuPage(model);
    }

    /**
     * Loads an existing menu item into the same form for editing.
     */
    @GetMapping("/manage/menu/edit/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {

        // Reads the item identified by the ID in the URL.
        MenuItem menuItem = menuItemService.getMenuItemById(id);

        // Supplies the existing item to prepopulate the form.
        // Its saved ID allows the submission to update that record.
        model.addAttribute("menuItem", menuItem);

        // Supplies the menu table displayed beside the editing form.
        model.addAttribute("listMenuItems", menuItemService.getAllMenuItems());

        // Displays the same template with the existing item's values.
        return "menu";
    }

    /**
     * Receives the menu form and saves its values.
     */
    @PostMapping("/manage/menu/save")
    public String saveMenuItem(
            @ModelAttribute("menuItem") MenuItem menuItem, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        // Spring has attempted to convert the form fields into MenuItem values.
        // BindingResult must immediately follow the object being bound.
        if (bindingResult.hasErrors()) {

            // Reloads the table while keeping the submitted form and its errors.
            model.addAttribute("listMenuItems", menuItemService.getAllMenuItems());

            // Returns the form so the employee can correct the values.
            return "menu";
        }

        // A non-null ID means the form is editing an existing item.
        if (menuItem.getId() != null) {

            // Retrieves the saved item before updating it.
            MenuItem existingItem = menuItemService.getMenuItemById(menuItem.getId());

            // Preserves the existing size and customization settings.
            // This first form will handle the basic menu fields.
            menuItem.setAllowedSizes(existingItem.getAllowedSizes());
            menuItem.setAllowedOptions(existingItem.getAllowedOptions());
        }

        // Sends the completed object to the service for saving.
        menuItemService.saveMenuItem(menuItem);

        // Supplies a message for the page displayed after the redirect.
        redirectAttributes.addFlashAttribute("successMessage", "Menu item saved."
        );

        // Opens the menu page again with an updated table and empty form.
        // Refreshing that page will not repeat the form submission.
        return "redirect:/manage/menu";
    }
}