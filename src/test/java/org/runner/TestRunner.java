package org.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.utilities.Utilities;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/features",glue ={"org.stepDefinitions","org.utilities"}, dryRun = false,
        plugin = {"pretty", "html:target/cucumber-html-report.html","json:target/cucumber-reports/Cucumber.json"})

public class TestRunner {



}
