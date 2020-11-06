package se.jensen;

import org.junit.Assert;
import se.jensen.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.List;

public class RestApiManualTest {
    public void testGetAllEmployees() {
        List<EmployeeModel> allEmployees = RestServiceClient.getAllEmployees().get();
        Assert.assertNotNull(allEmployees);
        Assert.assertEquals(3, allEmployees.size());
    }

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

    public void testCreateEmployee() {
        EmployeeModel newEmployee = EmployeeModel.builder()
                .setEmployeeId(10)
                .setFirstName("Number-10")
                .setLastName("Number-10")
                .setSalary(BigDecimal.valueOf(10000))
                .setFullTime(Boolean.TRUE)
                .build();

        EmployeeModel stored = RestServiceClient.createEmployee(newEmployee).get();
        Assert.assertNotNull(stored);
    }

    public void testDeleteEmployee() {
        EmployeeModel newEmployee = EmployeeModel.builder()
                .setEmployeeId(10)
                .setFirstName("Number-10")
                .setLastName("Number-10")
                .setSalary(BigDecimal.valueOf(10000))
                .setFullTime(Boolean.TRUE)
                .build();

        EmployeeModel stored = RestServiceClient.deleteEmployee(newEmployee).get();
        Assert.assertNotNull(stored);
    }
}
