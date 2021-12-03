package se.jocke.department.unittest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.department.service.DepartmentService;
import se.jocke.department.service.DepartmentServiceImpl;
import se.jocke.department.test.builder.DepartmentDatabaseEntryTestBuilder;
import se.jocke.department.test.builder.DepartmentTestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
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
    public void testGetDepartmentById() {
        when(departmentDao.findById(DEPARTMENT.getDepartmentId().getId())).thenReturn(Optional.of(DEPARTMENT_DATABASE_ENTRY));
        Department department = systemUnderTest.getDepartmentById(DEPARTMENT.getDepartmentId().getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(department),
                () -> Assertions.assertEquals(DEPARTMENT, department)
        );
        verify(departmentDao, times(1)).findById(DEPARTMENT.getDepartmentId().getId());
    }

    @Test
    public void testGetDepartmentByIdEntityNotFoundException() {
        when(departmentDao.findById(DEPARTMENT.getDepartmentId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.getDepartmentById(DEPARTMENT.getDepartmentId().getId()));
    }

    @Test
    public void testCreateDepartment() {
        when(departmentDao.findById(DEPARTMENT.getDepartmentId().getId())).thenReturn(Optional.empty());
        when(departmentDao.save(DEPARTMENT_DATABASE_ENTRY)).thenReturn(DEPARTMENT_DATABASE_ENTRY);
        Department department = systemUnderTest.create(DEPARTMENT);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(department),
                () -> Assertions.assertEquals(DEPARTMENT, department)
        );
    }

    @Test
    public void testCreateDepartmentEntityAlreadyInStorageException() {
        when(departmentDao.findById(DEPARTMENT.getDepartmentId().getId())).thenReturn(Optional.of(DEPARTMENT_DATABASE_ENTRY));
        Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> systemUnderTest.create(DEPARTMENT));
    }
    @Test
    public void testRemoveDepartment() {
        when(departmentDao.findById(DEPARTMENT.getDepartmentId().getId())).thenReturn(Optional.of(DEPARTMENT_DATABASE_ENTRY));
        doNothing().when(departmentDao).delete(DEPARTMENT_DATABASE_ENTRY);

        Department department = systemUnderTest.remove(DEPARTMENT);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(department),
                () -> Assertions.assertEquals(DEPARTMENT, department)
        );
        verify(departmentDao,times(1)).delete(DEPARTMENT_DATABASE_ENTRY);
    }

    @Test
    public void testRemoveDepartmentEntityNotFound() {
        when(departmentDao.findById(DEPARTMENT.getDepartmentId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.remove(DEPARTMENT));
    }


    @Test
    public void testGetAllDepartments() {
        when(departmentDao.findAll()).thenReturn(Arrays.asList(DEPARTMENT_DATABASE_ENTRY));

        List<Department> departments = systemUnderTest.getDepartments();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(departments),
                () -> Assertions.assertEquals(1, departments.size()),
                () -> Assertions.assertTrue(departments.contains(DEPARTMENT))
        );
    }
}
