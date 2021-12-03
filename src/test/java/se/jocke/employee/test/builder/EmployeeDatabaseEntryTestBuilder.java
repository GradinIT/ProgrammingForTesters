package se.jocke.employee.test.builder;

import se.jocke.employee.old.dao.EmployeeDatabaseEntry;

import java.math.BigDecimal;

public class EmployeeDatabaseEntryTestBuilder {
    public static EmployeeDatabaseEntry.EmployeeDatabaseEntryBuilder builder (){
        return EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Jocke")
                .lastName("Gradin")
                .fullTime(false)
                .salary(new BigDecimal(16000))
                .departmentId(400);
    }

}
