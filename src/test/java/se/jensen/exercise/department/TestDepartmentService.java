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
import se.jensen.test.category.UnitTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TestDepartmentService implements UnitTest {

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
        when(departmentDao.findById((DEPARTMENT_ID))).thenReturn(Optional.of(departmentDatabaseEntry));//update
        when(departmentDao.findById(any())).thenReturn(Optional.of(departmentDatabaseEntry));//remove

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
    @Test
    public void testThatDepartmentCreated() {
        Integer DEPARTMENT_ID = 10;
        String DEPARTMENT_NAME = "Development";
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();
        when(departmentDao.findById(any())).thenReturn(Optional.empty());
        when(departmentDao.save(any())).thenReturn(departmentDatabaseEntry); //any(DepartmentDatabaseEntry.class)
        Department createDepartment = service.create(Department.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build());
        Assert.assertNotNull(createDepartment);
        Assert.assertEquals(DEPARTMENT_ID, createDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTMENT_NAME, createDepartment.getDepartmentName());
       // System.out.println("The Newly created Department is \n" +"DEPARTMENT_ID  "+createDepartment.getDepartmentId() +" \n"+"DEPARTMENT_NAME  "+createDepartment.getDepartmentName());
        verify(departmentDao, times(1)).save(any());
        verify(departmentDao,times(1)).findById(any());
    }
    @Test
    public void testThatDepartmentIsUpdated(){
        String DEPARTMENT_NAME2= "Coding";
        Department department = Department.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME2)
                .build();
        when(departmentDao.save(any(DepartmentDatabaseEntry.class))).thenReturn(DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME2)
                .build());
        Department updateDepartment = service.update(department);
        Assert.assertNotNull(updateDepartment);
        Assert.assertEquals(DEPARTMENT_ID,updateDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTMENT_NAME2,updateDepartment.getDepartmentName());
        //System.out.println("The newly updated department is " +DEPARTMENT_NAME2);
        verify(departmentDao,times(1)).save(any());
        verify(departmentDao,times(1)).findById(any());
    }

    @Test
    public void testThatDepartmentIsDeleted(){
        Department department = service.remove(Department.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build());
        verify(departmentDao,times(1)).findById(any());
        verify(departmentDao,times(1)).delete(any());
    }
}
