package se.jocke.employee.builder;

import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.department.entity.EntityID;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {

    private final static EmployeeModel.EmployeeModelBuilder builder = EmployeeModel.builder();

    public static EmployeeModel.EmployeeModelBuilder builder() {
        return builder
                .employeeId(100)
                .departmentId(1)
                .firstName("TestKalle")
                .lastName("TestPersson")
                .fullTime(true)
                .salary(BigDecimal.valueOf(26000.00));
    }

}
