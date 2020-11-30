package se.jensen.exercise.department;
import liquibase.pro.packaged.D;

import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

import org.junit.experimental.categories.Category;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.BDDMockito.*;
import java.util.Optional;
import se.jensen.dao.*;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.service.*;
import se.jensen.exercise.DepartmentTestBuilder;
import net.minidev.json.JSONUtil;


import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Category(UnitTest.class)

public class TestDepartmentService {

    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService departmentServiceImpl = new DepartmentServiceImpl();

    DepartmentTestBuilder departmentTestBuilder = new DepartmentTestBuilder();

    private final Integer DEPARTMENTID = Integer.valueOf(1);
    private final String DEPARTMENTNAME = "Development";
    //-------------------------------------------------------------------------------------------------------------
    @Before
    public void setUpp()
    {
        MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);

        when(departmentDao.findAll()).thenReturn(list);
        when(departmentDao.findById(DEPARTMENTID)).thenReturn(Optional.of(departmentDatabaseEntry));

        doNothing().when(departmentDao).delete(any());
        when(departmentDao.save(any(DepartmentDatabaseEntry.class))).thenReturn(departmentDatabaseEntry);
        // There are two alternatives of department.save() mocking. Which option is correct? Can both be correct?
        //when(departmentDao.save(any())).thenReturn(DepartmentDatabaseEntryMapper.map(departmentTestBuilder.build()));

    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void  testGetAllDepartments()
    {
        List <Department> allDepartments = departmentServiceImpl.getDepartments(); //findAll()
        Assert.assertNotNull(allDepartments);
        Assert.assertEquals(1, allDepartments.size());

        verify(departmentDao, atLeastOnce()).findAll();
        verify(departmentDao, times(1)).findAll();
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetDepartmentById ()
    {
        Department department = departmentServiceImpl.getDepartmentById(DEPARTMENTID);    //findById()
        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTID , department.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
        verify(departmentDao, atLeastOnce()).findById(DEPARTMENTID);
        verify(departmentDao, times(1)).findById(DEPARTMENTID);
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreateDepartment()  //findByAll, save
    {
        Department dep = Department.builder()
                .departmentId(2)
                .departmentName("Department2")
                .build();

        Department createDepartment = departmentServiceImpl.create(dep);
        Assert.assertNotNull(createDepartment);
        verify(departmentDao, times(1)).save(any(DepartmentDatabaseEntry.class));
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testUpdateDepartment()  //findByAll, save
    {
        Department updateDepartment = departmentServiceImpl.update(departmentTestBuilder.build());
        Assert.assertNotNull(updateDepartment);
        verify(departmentDao, times(1)).save(any());
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testRemoveDepartment()  //findByAll, delete
    {
        Department removeDepartment = departmentServiceImpl.remove(departmentTestBuilder.build());
        Assert.assertNotNull(removeDepartment);
        verify(departmentDao, times(1)).findById(any(Integer.class));
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreateDepartmentIfDepartmentAlreadyFinns() {


        try {
            Department createDepartment = departmentServiceImpl.create(departmentTestBuilder.build());
            Assert.assertNotNull(createDepartment);
            verify(departmentDao, times(1)).save(any());
            fail();
        }
        catch (EntityAlreadyInStorageException entityAlreadyInStorageException) {
            System.out.println("Entity with id 1 already in storage");
            Assert.assertEquals("Entity with id 1 already in storage", entityAlreadyInStorageException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testDepartmentByIdNotFound()
    {
        try
        {
            Department department = departmentServiceImpl.getDepartmentById(10);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            System.out.println("Entity with id 10 not found");
            Assert.assertEquals("Entity with id 10 not found", entityNotFoundException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }

}
