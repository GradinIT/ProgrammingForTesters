package se.jocke.employee.builder;

import se.jocke.employee.dao.EmployeeDatabaseEntry;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class EmployeeDataBaseEntryListBuilder {
    public static List<EmployeeDatabaseEntry> buildAllEmployeesList() {
        return Arrays.asList(
                EmployeeDatabaseEntry.builder()
                        .employeeId(1)
                        .firstName("firstName1")
                        .lastName("LastName1")
                        .salary(new BigDecimal(25000))
                        .fullTime(true)
                        .departmentId(1)
                        .build(),
                EmployeeDatabaseEntry.builder()
                        .employeeId(2)
                        .firstName("firstname2")
                        .lastName("firstname2")
                        .salary(new BigDecimal(25000))
                        .fullTime(true)
                        .departmentId(1)
                        .build(),
                EmployeeDatabaseEntry.builder()
                        .employeeId(3)
                        .firstName("firstname3")
                        .lastName("firstname3")
                        .salary(new BigDecimal(25000))
                        .fullTime(true)
                        .departmentId(1)
                        .build());
    }
}