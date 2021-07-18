package com.contineo.inventory.steps;

import com.contineo.inventory.InventoryApplicationTests;
import com.contineo.inventory.model.Product;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class Definitions extends InventoryApplicationTests {

    RequestSpecification request = RestAssured.given();

    private String baseUrl = "http://localhost:" + 8080;
    private Response response;
    private int targetId = 0;

    @DataTableType
    public Product productEntry(Map<String, String> entry){
        return new Product(entry.get("name"),
                entry.get("category"),
                entry.get("sub-category"),
                Integer.parseInt(entry.get("quantity")));
    }

    @Given("user wants to create/update a product with the following attributes")
    public void user_wants_to_create_a_product_with_the_following_attributes(io.cucumber.datatable.DataTable dataTable) {
        List<Product> products = dataTable.asList(Product.class);
        Product product = products.get(0);

        request.contentType(ContentType.JSON)
                .body(product);

    }

    @Given("user wants to view a product with id {int}")
    public void user_wants_to_view_a_product_with_id(int id) {
        targetId = id;
    }

    @Given("user wants to delete a product with id {int}")
    public void user_wants_to_delete_a_product_with_id(int id) {
        targetId = id;
    }

    @When("user send {string} to RESTFUL API")
    public void user_send_create_to_restful_api(String action) {

        sendRequest(action, baseUrl);
    }

    private void sendRequest(String action, String apiUrl) {
        String actionUrl = apiUrl + "/api/product/";
        switch (action){
            case "CREATE":
                response = request.accept(ContentType.JSON).log().all().post(actionUrl + "create");
                break;
            case "VIEW":
                response = request.log().all().get(actionUrl + "get/" + targetId);
                break;
            case "VIEW ALL":
                response = request.log().all().get(actionUrl + "getAll");
                break;
            case "DELETE":
                response = request.log().all().delete(actionUrl + "delete/" + targetId);
                break;
            default:
                break;
        }
    }

    @Then("it returns {string}")
    public void it_returns(String expected) {
        if (expected.equals("IS SUCCESSFUL")) {
            Assert.assertEquals(200, response.statusCode());
        } else if (expected.equals("INVALID PRODUCT")){
            Assert.assertEquals(500, response.statusCode());
            Assert.assertEquals("Product has invalid category: Product[product_id=0, name=apple, category=clothes, sub_category=shoe, quantity=101]", response.body().prettyPrint());
        }
    }
}
