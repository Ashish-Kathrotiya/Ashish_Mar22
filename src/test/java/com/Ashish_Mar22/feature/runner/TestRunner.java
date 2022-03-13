package com.Ashish_Mar22.feature.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(features = "src/test/resources/feature/validateWordle.feature", glue = {
		"com.Ashish_Mar22.feature.steps" })

public class TestRunner {

}
