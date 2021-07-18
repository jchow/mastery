package com.contineo.inventory;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features.feature",
        plugin = {"pretty", "json:target/cucumber-report.json"})
public class InventoryIntegrationTest {


}
