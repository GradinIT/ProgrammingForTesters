package se.jocke.employee.unittest.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class TestEmployeeDao {

    // gå igenom och förstå, lägg till mer?

    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        Integer departmetId = 1; // ?
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(departmetId);
     /*   Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
              // fixa här  () -> assertEquals("?", optionalEmployeeDatabaseEntry.get().getEmployeeId()),
              //  () -> assertEquals(departmetId, optionalEmployeeDatabaseEntry.get().getDepartmentId())
        ); */
    }
}
