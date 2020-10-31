package se.jensen;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.EmployeeModel;

import java.util.List;

public class RestApiManualTest {
    @Test
    public void testGetAllEmployees() {
        List<EmployeeModel> allEmployees = RestServiceClient.getAllEmployees().get();
        Assert.assertNotNull(allEmployees);
        Assert.assertEquals(3, allEmployees.size());
    }

    @Test
    public void testGetEmployeesById() {
        List<EmployeeModel> allEmployees = RestServiceClient.getAllEmployees().get();
        Assert.assertNotNull(allEmployees);
        for (EmployeeModel employeeModel : allEmployees) {
            EmployeeModel employeeModel1 = RestServiceClient.getEmployeeById(employeeModel.getEmployeeId()).get();
            Assert.assertNotNull(employeeModel1);
            Assert.assertEquals(employeeModel.getEmployeeId(), employeeModel1.getEmployeeId());
            Assert.assertEquals(employeeModel.getFirstName(), employeeModel1.getFirstName());
            Assert.assertEquals(employeeModel.getLastName(), employeeModel1.getLastName());
            Assert.assertEquals(employeeModel.getFullTime(), employeeModel1.getFullTime());
        }
    }
}
