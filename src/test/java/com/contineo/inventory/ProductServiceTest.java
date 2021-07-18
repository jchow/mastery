package com.contineo.inventory;

import com.contineo.inventory.model.Product;
import com.contineo.inventory.validation.InvalidProductException;
import com.contineo.inventory.validation.ProductValidator;
import com.contineo.inventory.validation.ValidationResponse;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock ProductRepository repository;

    @Mock
    ProductValidator productValidator;

    @InjectMocks
    private final ProductService productService = new ProductService();

    private final Product testProduct = new Product("test", "animal", "cat", 123);
    private final List<Product> products = Collections.singletonList(testProduct);
    private final ValidationResponse invalidResponse = new ValidationResponse(false, "Test");
    private final ValidationResponse validResponse = new ValidationResponse(true);

    @Captor
    private ArgumentCaptor<Integer> captor;
    private Product wrongProduct = new Product();

    @BeforeEach
    void setup() {
        Mockito.lenient().when(repository.save(testProduct)).thenReturn(testProduct);
        Mockito.lenient().when(repository.findAll()).thenReturn(products);
        Mockito.lenient().when(repository.findById(anyInt())).thenReturn(Optional.of(testProduct));
        Mockito.lenient().when(productValidator.validate(wrongProduct)).thenReturn(invalidResponse);
        Mockito.lenient().when(productValidator.validate(testProduct)).thenReturn(validResponse);
    }

    @Test
    void addProduct() throws InvalidProductException {
        Assert.assertEquals(testProduct, productService.addProduct(testProduct));
    }

    @Test
    void getAll() {
        Assert.assertEquals(products, productService.getAll());
    }

    @Test
    void getById() {
        Assert.assertEquals(testProduct, productService.getById(3));
    }

    @Test
    void deleteById() {
        productService.deleteById(1);
        Mockito.verify(repository).deleteById(captor.capture());
        Assert.assertEquals(1, captor.getValue().intValue());
    }

    @Test
    void invalidProduct() {
        InvalidProductException exception = Assert.assertThrows(InvalidProductException.class, ()-> productService.addProduct(wrongProduct));
        Assert.assertEquals("Test", exception.getMessage());

    }

}