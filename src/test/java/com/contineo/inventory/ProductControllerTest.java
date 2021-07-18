package com.contineo.inventory;

import com.contineo.inventory.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    ProductService productService;

    @Captor
    private ArgumentCaptor<Integer> captor;

    @InjectMocks
    private final ProductController controller = new ProductController();

    private final Product testProduct = new Product("test", "animal", "cat", 123);
    private final List<Product> products = Collections.singletonList(testProduct);

    @BeforeEach
    void setup() {
        Mockito.lenient().when(productService.addProduct(testProduct)).thenReturn(testProduct);
        Mockito.lenient().when(productService.getAll()).thenReturn(products);
        Mockito.lenient().when(productService.getById(anyInt())).thenReturn(testProduct);
    }

    @Test
    void create() {
        Assert.assertEquals(testProduct, controller.create(testProduct));
    }

    @Test
    void getAll() {
        Assert.assertEquals(products, controller.getAll());
    }

    @Test
    void getById() {
        Assert.assertEquals(testProduct, controller.getById(1));
    }

    @Test
    void deleteById() {
        controller.deleteById(1);
        Mockito.verify(productService).deleteById(captor.capture());
        Assert.assertEquals(1, captor.getValue().intValue());
    }
}