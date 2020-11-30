package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.EmployeeDao;
import se.jensen.entity.Department;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TestDepartmentService {

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
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);

        when(departmentDao.findAll()).thenReturn(list);

    }

    @Test
    public void testGetAllDepartment() {
        List<Department> all = service.getDepartments();
        verify(departmentDao, times(1)).findAll();
        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());

    }

    @Test
    public void testGetDepartmentById() {
        Integer departmentId = Integer.valueOf(15);

        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(departmentId)
                .departmentName(DEPARTMENT_NAME)
                .build();

        when(departmentDao.findById(departmentId)).thenReturn(Optional.of(departmentDatabaseEntry));

        Department department = service.getDepartmentById(departmentId);
        verify(departmentDao, times(1)).findById(departmentId);
        Assert.assertNotNull(department);
        Assert.assertEquals(departmentId, department.getDepartmentId());
        Assert.assertEquals(DEPARTMENT_NAME, department.getDepartmentName());
    }

}
