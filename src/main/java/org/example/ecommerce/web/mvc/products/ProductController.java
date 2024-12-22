package org.example.ecommerce.web.mvc.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getAllProducts(Model model) {
        // Add attributes to the model to be used in the view
        model.addAttribute("products", "List of products");
        return "products"; // Return the view name
    }
}