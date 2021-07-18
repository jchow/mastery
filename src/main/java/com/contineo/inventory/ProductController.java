package com.contineo.inventory;

import com.contineo.inventory.model.Product;
import com.contineo.inventory.validation.InvalidProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product create(@RequestBody Product product) throws InvalidProductException {
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

    @ExceptionHandler({InvalidProductException.class})
    public ResponseEntity customExceptionHandler(InvalidProductException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
