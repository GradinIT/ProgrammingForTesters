package se.jocke;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Tags;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags ="@Department And @Employee")
public class IntegrationTestv2 {
}