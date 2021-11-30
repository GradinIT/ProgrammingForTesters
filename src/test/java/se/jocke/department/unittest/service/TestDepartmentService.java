package se.jocke.department.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.department.service.DepartmentService;
import se.jocke.department.service.DepartmentServiceImpl;
import se.jocke.department.test.builder.DepartmentDatabaseEntryTestBuilder;
import se.jocke.department.test.builder.DepartmentTestBuilder;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestDepartmentService {

    private static final Department DEPARTMENT = DepartmentTestBuilder.build();
    private static final DepartmentDatabaseEntry DEPARTMENT_DATABASE_ENTRY = DepartmentDatabaseEntryTestBuilder.build();

    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();

    @Test
    public void testFindById() {
        when(departmentDao.findById(DEPARTMENT.getDepartmentId())).thenReturn(Optional.of(DEPARTMENT_DATABASE_ENTRY));

        Department department = systemUnderTest.getDepartmentById(DEPARTMENT.getDepartmentId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(department),
                () -> Assertions.assertEquals(DEPARTMENT, department)
        );
        verify(departmentDao, times(1)).findById(DEPARTMENT.getDepartmentId());
    }
}
