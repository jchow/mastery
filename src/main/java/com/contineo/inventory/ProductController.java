package com.contineo.inventory;

import com.contineo.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/getAll")
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/get/{id}")
    public Product getById(@PathVariable("id") int id) {
        return productService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int id){
        productService.deleteById(id);
    }

}
