package se.jensen;

import se.jensen.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.List;

public class RestApiManualTest {
    public static void main(String[] args) {
        List<EmployeeModel> employeeModels = RestServiceClient.getAllEmployees().get();
        for (EmployeeModel employeeModel : employeeModels) {
            EmployeeModel employeeModelById = RestServiceClient.getEmployeeById(employeeModel.getEmployeeId()).get();
        }

        //TODO: using the RestClient add a new Employee
        EmployeeModel employeeModel = EmployeeModel.builder()
                .setEmployeeId(80)
                .setFullTime(Boolean.TRUE)
                .setSalary(BigDecimal.valueOf(25000))
                .setLastName("Andersson")
                .setFirstName("Mr")
                .build();

        employeeModel = RestServiceClient.createEmployee(employeeModel).get();
        System.out.println("after create before update: "+employeeModel);

        EmployeeModel updatedEmployee = EmployeeModel.builder()
                .setEmployeeId(employeeModel.getEmployeeId())
                .setLastName(employeeModel.getLastName())
                .setSalary(employeeModel.getSalary())
                .setFullTime(employeeModel.getFullTime())
                .setFirstName("Anders")
                .build();
        //TODO: using te RestClient update the Employee with new First name
        updatedEmployee = RestServiceClient.updateEmployee(updatedEmployee).get();
        System.out.println("after update "+updatedEmployee);

        employeeModels = RestServiceClient.getAllEmployees().get();
        for (EmployeeModel e : employeeModels) {
            System.out.println(e);
        }

        //TODO: using te Rest client remove the new Employee

        EmployeeModel deletedEmpoyee = RestServiceClient.deleteEmployee(updatedEmployee).get();

        System.out.println("was deleted "+deletedEmpoyee);

        employeeModels = RestServiceClient.getAllEmployees().get();
        for (EmployeeModel e : employeeModels) {
            System.out.println(e);
        }

    }
}
