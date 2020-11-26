package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;
import se.jensen.entity.Department;
import se.jensen.entity.Employee;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestDepartmentService {
    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService service = new DepartmentServiceImpl();
    private final Integer DEPARTMENTID = 1;
    private final String DEPARTMENTNAME = "ACCOUNTS";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);
        when(departmentDao.findAll()).thenReturn(list);
    }

    @Test
    public void testGetAll() {
        List<Department> all = service.getDepartments();
        verify(departmentDao, times(1)).findAll();
        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
    }

    @Test
    public void testGetById() {
        Integer departmentId = Integer.valueOf(1);

        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder() // setup test reply from database
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        when(departmentDao.findById(departmentId)).thenReturn(Optional.of(departmentDatabaseEntry)); // Mock the call

        Department department = service.getDepartmentById(departmentId);
        verify(departmentDao, times(1)).findById(departmentId);
        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());

    }
}