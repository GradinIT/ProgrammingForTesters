package se.jocke;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
// Övning 5
@RunWith(JUnitPlatform.class)
@SelectPackages("se.jocke.excersices.basic")
public class CalculationTestSuite {

}

