package se.jensen.exercise.department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.entity.Department;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
<<<<<<< HEAD
    Integer departmentId = Integer.valueOf(1);
    private final Integer DEPARTMENTID = 1;
    private final String DEPARTMENTNAME = "ACCOUNTS";
=======

    private final String DEPARTMENTNAME = "Intellij";
    private final Integer DEPARTMENTID = 2020;
>>>>>>> feature/GROUP_MUSSE

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
<<<<<<< HEAD
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);
        when(departmentDao.findAll()).thenReturn(list);
        when(departmentDao.findById(any())).thenReturn((Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build())));
        when(departmentDao.save(any())).thenReturn(DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build());
    }

    @Test
    public void testGetAll() {
=======
                .departmentName(DEPARTMENTNAME)
                .departmentId(2020)
                .build();
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);

        when(departmentDao.findAll()).thenReturn(list);

    }
    @Test
    @DisplayName("Test To Get All Departments ")
    public void TestToGetAllDepartments() {
>>>>>>> feature/GROUP_MUSSE
        List<Department> all = service.getDepartments();
        verify(departmentDao, times(1)).findAll();
        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
    }
<<<<<<< HEAD

    @Test
    public void testGetById() {
       //Integer departmentId = Integer.valueOf(1);



=======
    @Test
    @DisplayName("Test To Get Departments By Id")
    public void TestToGetDepartmentsById() {
        Integer departmentId = Integer.valueOf(15);

        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder() // setup test reply from database
                .departmentId(2020)
                .departmentName(DEPARTMENTNAME)
                .build();

        when(departmentDao.findById(departmentId)).thenReturn(Optional.of(departmentDatabaseEntry)); // Mock the call
>>>>>>> feature/GROUP_MUSSE

        Department department = service.getDepartmentById(departmentId);
        verify(departmentDao, times(1)).findById(departmentId);
        Assert.assertNotNull(department);
<<<<<<< HEAD
        Assert.assertEquals(DEPARTMENTID, department.getDepartmentId());

    }
    @Test
    public void testCreate(){
        when(departmentDao.findById(any()))
                .thenReturn(Optional.empty())
              .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build()));

        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        Department createDepartment = service.create(department);
        Assert.assertNotNull(createDepartment);
        verify(departmentDao, times(1)).save(any());
       Department savedDepartment = service.getDepartmentById(DEPARTMENTID);

        Assert.assertNotNull(savedDepartment);
        Assert.assertEquals(DEPARTMENTID, savedDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, savedDepartment.getDepartmentName());
System.out.println(savedDepartment.getDepartmentId());
    }
    @Test
    public void testDelete(){
        Department department = Department.builder()
                .departmentId(3)
                .departmentName(DEPARTMENTNAME)
                .build();
        System.out.println(department.getDepartmentId());
        Department departmentDelete = service.remove(department);
        verify(departmentDao,times(1)).delete(any());
        //Department deleteDepartment = service.getDepartmentById(DEPARTMENTID);

        //when(departmentDao.existsById(departmentId)).thenReturn(false);
       // DepartmentDatabaseEntry departmentDatabaseEntrydelete = departmentDao.delete(DepartmentDatabaseEntry);
//Assert.assertNull(departmentDelete);
        System.out.println(DEPARTMENTID);
        System.out.println(departmentDelete.getDepartmentId());
//Assert.assertNull(department.getDepartmentId());
System.out.println(department.getDepartmentId());
    }
    @Test
    public void testUpdate(){
        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        Department departmentUpdate = service.update(department);
        verify(departmentDao,times(1)).save(any());
        Assert.assertNotNull(departmentUpdate);
        Assert.assertEquals(DEPARTMENTID, departmentUpdate.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, departmentUpdate.getDepartmentName());
        System.out.println("Departments =:"+departmentUpdate.getDepartmentId());
    }
}
=======
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
        Assert.assertEquals(DEPARTMENTID, department.getDepartmentId());
    }
}
>>>>>>> feature/GROUP_MUSSE
