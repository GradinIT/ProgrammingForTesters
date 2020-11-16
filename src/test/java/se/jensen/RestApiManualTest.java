package se.jensen;

import org.junit.experimental.categories.Category;
import se.jensen.api.EmployeeModel;
import se.jensen.test.category.ManualTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Category(ManualTest.class)
public class RestApiManualTest {
    public static void main(String[] args) {
        printAllEmployees();
        List<EmployeeModel> employeeModelsById = getAllEmployees().stream()
                .map(employeeModel -> {
                            return RestServiceClient.getEmployeeById(employeeModel.getEmployeeId()).get();
                        }
                )
                .collect(Collectors.toList());
        EmployeeModel createdEmployeeModel = createNewEmployee();
        printAllEmployees();
        EmployeeModel updatedEmployee = EmployeeModel.builder()
                .setEmployeeId(createdEmployeeModel.getEmployeeId())
                .setLastName(createdEmployeeModel.getLastName())
                .setSalary(createdEmployeeModel.getSalary())
                .setFullTime(createdEmployeeModel.getFullTime())
                .setFirstName("Anders")
                .build();
        updateEmployee(updatedEmployee);
        printAllEmployees();
        deleteEmployee(updatedEmployee);
        printAllEmployees();
    }

    private static void deleteEmployee(EmployeeModel updatedEmployee) {
        RestServiceClient.deleteEmployee(updatedEmployee);
    }

    private static void printAllEmployees() {
        System.out.println("------------ listing all employees ------------");
        getAllEmployees().stream().forEach(System.out::println);
        System.out.println("------------ listing employees end ------------");

    }

    private static void updateEmployee(EmployeeModel employeeModel) {
        RestServiceClient.updateEmployee(employeeModel);
    }

    private static EmployeeModel createNewEmployee() {
        EmployeeModel employeeModel = EmployeeModel.builder()
                .setEmployeeId(80)
                .setFullTime(Boolean.TRUE)
                .setSalary(BigDecimal.valueOf(25000))
                .setLastName("Andersson")
                .setFirstName("Mr")
                .build();
        return RestServiceClient.createEmployee(employeeModel).get();
    }

    private static List<EmployeeModel> getAllEmployees() {
        return RestServiceClient.getAllEmployees().get();
    }
}
