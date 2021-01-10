package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.EntityAlreadyInStorageException;
import se.jensen.dao.EntityNotFoundException;
import se.jensen.entity.Department;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;
import se.jensen.test.category.UnitTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class TestDepartmentServiceExceptions implements UnitTest {
    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService service = new DepartmentServiceImpl();

    private final Integer DEPARTMENT_ID = 1;
    private final String DEPARTMENT_NAME = "Junit";
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();
    }
    @Test
    public void testEntityNotFoundException() {

        try {
            when(departmentDao.findById(50)).thenReturn(Optional.empty());
            Department department = service.getDepartmentById(50);
            fail("Exception not thrown");
        } catch (EntityNotFoundException entityNotFoundException) {
            Assert.assertEquals("Entity with id 50 not found", entityNotFoundException.getMessage());
        } catch (Exception exception) {
            fail("wrong exception caught");
        }
    }
    @Test(expected = EntityNotFoundException.class)
    public void testDepartmentIdNotFound(){
        when(departmentDao.findById(50)).thenReturn(Optional.empty());
        Department department = service.getDepartmentById(50);
        verify(departmentDao,times(1)).findById(50);

    }
    @Test(expected = EntityAlreadyInStorageException.class)
    public void testDepartmentCreate() {
        Integer DEPARTMENT_ID = Integer.valueOf(10);
        String DEPARTMENT_NAME = "Finance";
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();
        when(departmentDao.findById(DEPARTMENT_ID)).thenReturn(Optional.of(departmentDatabaseEntry));
        Department createDepartment = service.create(Department.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build());
        Assert.assertNotNull(createDepartment);
        Assert.assertEquals(DEPARTMENT_ID, createDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTMENT_NAME, createDepartment.getDepartmentName());
        verify(departmentDao, times(1)).findById(DEPARTMENT_ID);
    }

}

