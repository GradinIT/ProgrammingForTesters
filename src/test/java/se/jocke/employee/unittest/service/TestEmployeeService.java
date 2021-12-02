package se.jocke.department.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.common.dao.EntityNotFoundException;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.department.service.DepartmentService;
import se.jocke.department.service.DepartmentServiceImpl;
import se.jocke.department.test.builder.DepartmentDatabaseEntryTestBuilder;
import se.jocke.department.test.builder.DepartmentTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.service.EmployeeService;
import se.jocke.employee.service.EmployeeServiceImpl;
import se.jocke.employee.unittest.api.entity.Employee;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestDepartmentService {

    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();
    // Klassen går inte att ändra pga final.
    // Vi skapar en DEPARTMENT av typen Department och lägger till DepartmentTestBuilder.build() som värde
    private static final DepartmentDatabaseEntry EMPLOYEE_DATABASE_ENTRY = DepartmentDatabaseEntryTestBuilder.build();
    //
    @Mock
    //Låtsasobjekt
    private EmployeeDao employeeDao;

    @InjectMocks
    //Får informationen i låtsasobjekt
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @Test
    public void testFindById() {
        when(EmployeeDao.findById(EMPLOYEE.getDepartmentId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));

        Department department = systemUnderTest.getEmployeeById(EMPLOYEE.getDepartmentId().getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(department),
                () -> Assertions.assertEquals(EMPLOYEE, department)
        );
        verify(employeeDao, times(1)).findById(EMPLOYEE.getDepartmentId().getId());
    }
    @Test
    public void testEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getDepartmentId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()-> systemUnderTest.getEmployeeById(EMPLOYEE.getDepartmentId().getId()));
    }
}
