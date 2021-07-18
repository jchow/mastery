package com.contineo.inventory.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category")
    private String category;

    @Column(name = "sub_category")
    private String sub_category;

    private String name;

    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(String name, String category, String subCategory, int quantity) {
        this.sub_category = subCategory;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product[product_id=" + id +
                ", name=" + name +
                ", category=" + category +
                ", sub_category=" + sub_category +
                ", quantity=" + quantity +
                "]";
    }
}
