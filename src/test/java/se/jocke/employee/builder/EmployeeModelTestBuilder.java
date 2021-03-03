package se.jocke.employee.builder;

import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {

    private final static EmployeeModel.EmployeeModelBuilder builder = EmployeeModel.builder();

    public static EmployeeModel.EmployeeModelBuilder builder() {
        return builder
                .employeeId(1)
                .departmentId(2)
                .firstName("TestKalle")
                .lastName("TestPersson")
                .fullTime(true)
                .salary(BigDecimal.valueOf(26000.00));
    }

}
