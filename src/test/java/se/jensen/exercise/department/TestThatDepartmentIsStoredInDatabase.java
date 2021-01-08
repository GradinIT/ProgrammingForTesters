package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestThatDepartmentIsStoredInDatabase {
    private final Integer DEPARTMENT_ID = 15;
    private final String DEPARTMENT_NAME = "name";

    DepartmentDao departmentDao = mock(DepartmentDao.class);

    @Before
    public void setUp() {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);

    }
    @Test
    public void testsave() {
        departmentDao.save(DepartmentDatabaseEntry.builder()
                .departmentName(DEPARTMENT_NAME)
                .departmentId(DEPARTMENT_ID)
                .build());

        verify(departmentDao,times(1)).save(any());
    }
    @Test
    public void testdelete() {
       DepartmentDatabaseEntry deleteDataentry = departmentDao.save(DepartmentDatabaseEntry.builder()
                .departmentName("Delete")
                .departmentId(100)
                .build());

        verify(departmentDao,times(1)).save(any());

        departmentDao.delete(deleteDataentry);
        verify(departmentDao,times(1)).delete(any());
    }

    @Test
    public void testFindById() {

        departmentDao.findById(DEPARTMENT_ID);
        verify(departmentDao,times(1)).findById(any());
    }

    @Test
    public void testFindall() {
        List<DepartmentDatabaseEntry> findAllDataentry = new ArrayList<>();
        findAllDataentry = departmentDao.findAll();


        verify(departmentDao,times(1)).findAll();


    }
}
