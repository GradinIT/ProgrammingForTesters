package se.jocke.employee.builder;

import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModelTestBuilder {
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(200)
                .departmentId(1)
                .firstName("Anders")
                .lastName("Andersson")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000));
    }

    public static List<EmployeeModel> buildList(){
        List<EmployeeModel> buildList = new ArrayList<>();
        buildList.add(
                EmployeeModel.builder().employeeId(100)
                        .firstName("Testare")
                        .lastName("Testarsson")
                        .salary(BigDecimal.valueOf(25000.0))
                        .fullTime(Boolean.TRUE)
                        .departmentId(1).build());
        buildList.add(
                EmployeeModel.builder().employeeId(200)
                        .firstName("Coder")
                        .lastName("Codersson")
                        .salary(BigDecimal.valueOf(35000.0))
                        .fullTime(Boolean.TRUE)
                        .departmentId(2).build());
        return buildList;
    }
}
