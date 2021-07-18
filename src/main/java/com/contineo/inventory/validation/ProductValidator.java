package com.contineo.inventory.validation;

import com.contineo.inventory.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductValidator {

    // Predefined product with category. Ideally should be another service providing the rules.
    private final Map<String, String> productCategory =
            Map.of("apple", "veggie",
            "slipper", "clothes",
            "cake", "food");

    public ValidationResponse validate(Product product) {
        if (productCategory.containsKey(product.getName()) && productCategory.get(product.getName()).equalsIgnoreCase(product.getCategory())){
            return new ValidationResponse(true);
        }
        return new ValidationResponse(false, "Product has invalid category: " + product.toString());
    }
}
