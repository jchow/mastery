package com.contineo.inventory;

import com.contineo.inventory.model.Product;
import com.contineo.inventory.validation.InvalidProductException;
import com.contineo.inventory.validation.ProductValidator;
import com.contineo.inventory.validation.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductValidator validator;

    public Product addProduct(Product product) throws InvalidProductException {
        ValidationResponse validationResponse = validator.validate(product);
        if (!validationResponse.valid()){
            throw new InvalidProductException(validationResponse.message());
        }
        return productRepository.save(product);
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(int id) {
        return productRepository.findById(id).get();
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
