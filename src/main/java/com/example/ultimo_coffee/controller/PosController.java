package com.example.ultimo_coffee.controller;

// Gives the controller access to the existing menu service.
import com.example.ultimo_coffee.service.MenuItemService;

// Allows Spring to supply the service automatically.
import org.springframework.beans.factory.annotation.Autowired;

// Identifies this class as a controller that returns HTML views.
import org.springframework.stereotype.Controller;

// Carries data from the controller to the HTML template.
// This Spring MVC Model is different from your MenuItem entity.
import org.springframework.ui.Model;

// Connects a browser GET request to a controller method.
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PosController {

    // Spring supplies the MenuItemServiceImpl object here.
    @Autowired
    private MenuItemService menuItemService;

    // Runs when the browser requests http://localhost:8080/pos.
    @GetMapping("/pos")
    public String viewPosPage(Model model) {

        // Retrieves the available menu items through the service.
        // Makes them available to pos.html under "listMenuItems".
        model.addAttribute(
                "listMenuItems",
                menuItemService.getAvailableMenuItems()
        );

        // Tells Spring to render templates/pos.html.
        return "pos";
    }
}