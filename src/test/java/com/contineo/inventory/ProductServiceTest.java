package com.contineo.inventory;

import com.contineo.inventory.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock ProductRepository repository;

    @InjectMocks
    private final ProductService productService = new ProductService();

    private final Product testProduct = new Product("test", "animal", "cat", 123);
    private final List<Product> products = Collections.singletonList(testProduct);

    @Captor
    private ArgumentCaptor<Integer> captor;

    @BeforeEach
    void setup() {
        Mockito.lenient().when(repository.save(testProduct)).thenReturn(testProduct);
        Mockito.lenient().when(repository.findAll()).thenReturn(products);
        Mockito.lenient().when(repository.findById(anyInt())).thenReturn(Optional.of(testProduct));
    }

    @Test
    void addProduct() {
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

}