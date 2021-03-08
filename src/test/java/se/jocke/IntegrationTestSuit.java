package se.jocke;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import se.jocke.department.integrationtest.DepartmentIntegrationTest;
import se.jocke.employee.integrationtest.EmployeeIntegrationTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({EmployeeIntegrationTest.class, DepartmentIntegrationTest.class})
public class IntegrationTestSuit {
}
