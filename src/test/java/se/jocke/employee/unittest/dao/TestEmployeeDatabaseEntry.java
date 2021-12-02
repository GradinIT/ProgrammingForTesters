package se.jocke.employee.unittest.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;

public class TestEmployeeDatabaseEntry {

        private static final EmployeeDatabaseEntry ENTRY = EmployeeDatabaseEntryTestBuilder.build();

        @Test
        public void testThatEmployeeIsCreated() {
            EmployeeDatabaseEntry employee = EmployeeDatabaseEntry.builder()
                    .departmentId(ENTRY.getDepartmentId())
                    .firstName(ENTRY.getFirstName())
                    .lastName(ENTRY.getLastName())
                    .salary(ENTRY.getSalary())
                    .fullTime(ENTRY.getFullTime())
                    .employeeId(ENTRY.getEmployeeId())  // varför ingen .getId() här?
                    .build();
            Assertions.assertEquals(ENTRY, employee);
            Assertions.assertEquals(ENTRY.getDepartmentId(),employee.getEmployeeId());
            Assertions.assertEquals(ENTRY.getSalary(), employee.getSalary());
            Assertions.assertEquals(ENTRY.getFirstName(), employee.getFirstName());
            Assertions.assertEquals(ENTRY.getLastName(),employee.getLastName());
            Assertions.assertEquals(ENTRY.getFullTime(),employee.getFullTime());
            Assertions.assertEquals(ENTRY.getEmployeeId(), employee.getEmployeeId());  // varför ingen .getId() här?

        }

}
