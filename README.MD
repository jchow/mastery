# Inventory Service

## Summary

A simple REST API inventory service that provides the following operations:

* create a product
* find a product by id
* view all products
* delete a product

H2, maven, Spring boot REST, JPA are used.

Cucumber is used for integration test [InventoryIntegrationTest](https://github.com/jchow/mastery/blob/main/src/test/java/com/contineo/inventory/InventoryApplicationTests.java). Please find the feature file [here](https://github.com/jchow/mastery/blob/main/src/test/resources/features.feature).

More requirements can be added on to this including more validations and performances impacts.
