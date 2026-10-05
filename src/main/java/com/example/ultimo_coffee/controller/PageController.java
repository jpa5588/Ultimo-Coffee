// Places this class in the controller package.
package com.example.ultimo_coffee.controller;

// Identifies this class as a controller for pages and navigation.
import org.springframework.stereotype.Controller;

// Allows a method to handle an HTTP GET request.
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles the application's home address.
 *
 * Opening "/" redirects the browser to the menu management page.
 * MenuItemController then loads the menu items and prepares the form.
 */
@Controller
public class PageController {

    // Runs when the browser requests the application's home address.
    @GetMapping("/")
    public String viewHomePage() {

        // "redirect:" tells Spring to send the browser to another URL.
        // That URL is already handled by MenuItemController.
        return "redirect:/manage/menu";
    }
}