# Inventory Service

## Summary

A simple REST API inventory service that provides the following operations:

* create a product
* find a product by id
* view all products
* delete a product

H2, maven, Spring boot REST, JPA are used.

Cucumber is used for integration test [InventoryIntegrationTest](src/test/java/com.contineo.inventory/InventoryIntegrationTest.java). Please find the feature file [here](src/test/java/resource/features.feature).

More requirements can be added on to this including more validations and performances impacts.