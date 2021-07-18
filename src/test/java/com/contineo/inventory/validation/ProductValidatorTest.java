package com.contineo.inventory.validation;

import com.contineo.inventory.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductValidatorTest {

    private final ProductValidator validator = new ProductValidator();
    private Product validProduct = new Product("apple", "veggie", "fruit", 20);
    private Product invalidProduct = new Product("slipper", "veggie", "fruit", 20);

    @Test
    void validate() {
        Assert.assertTrue(validator.validate(validProduct).valid());
        Assert.assertFalse(validator.validate(invalidProduct).valid());
        Assert.assertEquals("Product has invalid category: Product[product_id=0, name=slipper, category=veggie, sub_category=fruit, quantity=20]", validator.validate(invalidProduct).message());
    }
}