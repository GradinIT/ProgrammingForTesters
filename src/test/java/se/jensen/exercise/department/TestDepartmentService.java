package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TestDepartmentService {
    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService departmentService = new DepartmentServiceImpl();

    private final Integer DEPARTMENTID = 1;
    private final String DEPARTMENTNAME = "Development";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        List<DepartmentDatabaseEntry> departmentList = new ArrayList<>();
        departmentList.add(departmentDatabaseEntry);

        when(departmentDao.findAll()).thenReturn(departmentList);
        when(departmentDao.findById(any())).thenReturn(Optional.of(departmentDatabaseEntry));
    }

    @Test
    public void testToGetAllDepartment() {
        List<Department> listForAllDepartments = departmentService.getDepartments();
        verify(departmentDao, times(1)).findAll();
        Assert.assertNotNull(listForAllDepartments);
        Assert.assertEquals(1, listForAllDepartments.size());
    }

    @Test
    public void testToGetDepartmentById() {
        Integer temporaryDepartmentId = Integer.valueOf(15);

        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(temporaryDepartmentId)
                .departmentName(DEPARTMENTNAME)
                .build();

        when(departmentDao.findById(temporaryDepartmentId)).thenReturn(Optional.of(departmentDatabaseEntry));

        Department department = departmentService.getDepartmentById(temporaryDepartmentId);
        verify(departmentDao, times(1)).findById(temporaryDepartmentId);
        Assert.assertNotNull(department);
        Assert.assertEquals(temporaryDepartmentId, department.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
    }

    @Test
    public void testToCreateDepartment() {
        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        //setting the rules for the mock
        when(departmentDao.findById(any())).thenReturn(Optional.empty());
        when(departmentDao.save(any())).thenReturn(departmentDatabaseEntry);

        //do the service call
        Department createdDepartment = departmentService.create(department);

        //verify that everything is ok
        Assert.assertEquals(department,createdDepartment);
        verify(departmentDao, times(1)).findById(department.getDepartmentId());
        verify(departmentDao, times(1)).save(departmentDatabaseEntry);
    }

    @Test
    public void testToUpdateDepartment() {
        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        //setting the rules for the mock
        when(departmentDao.findById(any())).thenReturn(Optional.of(departmentDatabaseEntry));
        when(departmentDao.save(any())).thenReturn(departmentDatabaseEntry);

        //do the service call
        Department updatedDepartment = departmentService.update(department);

        //check that everything is ok
        Assert.assertEquals(department,updatedDepartment);
        verify(departmentDao, times(1)).save(departmentDatabaseEntry);

    }

    @Test
    public void testToDeleteDepartment() {
        departmentService.remove(DepartmentTestBuilder.build());
        verify(departmentDao, atLeastOnce()).delete(any());
    }

}
