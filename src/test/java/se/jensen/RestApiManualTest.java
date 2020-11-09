package se.jensen;

import se.jensen.api.EmployeeModel;

import java.util.List;

public class RestApiManualTest {
    public static void main(String[] args) {
        List<EmployeeModel> employeeModels = RestServiceClient.getAllEmployees().get();
        for (EmployeeModel employeeModel : employeeModels) {
            EmployeeModel employeeModelById = RestServiceClient.getEmployeeById(employeeModel.getEmployeeId()).get();
        }

        //TODO: using the RestClient add a new Employee

        //TODO: using te RestClient update the Employee with new First name

        //TODO: using te Rest client remove the new Employee

    }
}
