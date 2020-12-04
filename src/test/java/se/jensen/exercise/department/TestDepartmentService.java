package se.jensen.exercise.department;
import liquibase.pro.packaged.D;
import net.minidev.json.JSONUtil;
import java.nio.channels.ScatteringByteChannel;

import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;
import se.jensen.dao.*;
import se.jensen.service.*;
import se.jensen.exercise.DepartmentTestBuilder;

import org.junit.experimental.categories.Category;
import static org.junit.Assert.fail;
import org.junit.*;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Category(UnitTest.class)

public class TestDepartmentService {

    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService departmentServiceImpl = new DepartmentServiceImpl();

    private final Integer DEPARTMENTID = Integer.valueOf(1);
    private final String DEPARTMENTNAME = "Development";

//-------------------------------------------------------------------------------------------------------------
    @Before
    public void setUpp()
    {
        MockitoAnnotations.initMocks(this);
    }
//-------------------------------------------------------------------------------------------------------------

    @Test
    public void  testGetAllDepartments()
    {
        // mock departmentDao.findAll()
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);
        when(departmentDao.findAll()).thenReturn(list);

        List <Department> allDepartments = departmentServiceImpl.getDepartments(); //findAll()

        Assert.assertNotNull(allDepartments);
        Assert.assertEquals(1, allDepartments.size());
        Assert.assertEquals(DEPARTMENTID , allDepartments.get(0).getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, allDepartments.get(0).getDepartmentName());

        verify(departmentDao, atLeastOnce()).findAll();
        verify(departmentDao, times(1)).findAll();
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetDepartmentById ()
    {
// mock  departmentDao.findById()
        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));

        Department department = departmentServiceImpl.getDepartmentById(DEPARTMENTID);  //findById()

        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTID , department.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());

        verify(departmentDao, atLeastOnce()).findById(DEPARTMENTID);
        verify(departmentDao, times(1)).findById(any(Integer.class));
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreateDepartment()  //findByAll, save
    {
// mock  departmentDao.findById()
        when(departmentDao.findById(DEPARTMENTID)).thenReturn(Optional.empty());

// mock departmentDao.save()
        when(departmentDao.save(any()))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build());

        Department departmentToCreate = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();
        Department createdDepartment = departmentServiceImpl.create(departmentToCreate);

        Assert.assertNotNull(createdDepartment);
        Assert.assertEquals(DEPARTMENTID, createdDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, createdDepartment.getDepartmentName());

        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).save(any());
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testUpdateDepartment()  //findById, save
    {
// mock  departmentDao.findById()
        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));

        Integer NEWDEPARTMENTID = Integer.valueOf(2);

        when(departmentDao.save(any()))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(NEWDEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build());

        Department departmentToUpdate = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();
        Department updatedDepartment = departmentServiceImpl.update(departmentToUpdate);

        Assert.assertNotNull(updatedDepartment);
        Assert.assertEquals(NEWDEPARTMENTID, updatedDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, updatedDepartment.getDepartmentName());

        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).save(any());
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testRemoveDepartment()  //findByAll, delete
    {
        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));

// mock departmentDao.delete()
        doNothing().when(departmentDao).delete(any());

        Department departmentToRemove = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();
        Department removedDepartment = departmentServiceImpl.remove(departmentToRemove);

        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).delete(any());
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreateDepartmentIfDepartmentIsAlreadyInStorage() {

        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));

        try {
            Department depToCreate = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();
            Department createDepartment = departmentServiceImpl.create(depToCreate);
            Assert.assertNotNull(createDepartment);
            verify(departmentDao, times(1)).save(any());
            fail();
        }
        catch (EntityAlreadyInStorageException entityAlreadyInStorageException) {
            System.out.println("testCreateDepartmentIfDepartmentIsAlreadyInStorage: Entity with id 1 is already in storage");
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
        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));
        try
        {
            Department department = departmentServiceImpl.getDepartmentById(10);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            System.out.println("testDepartmentByIdNotFound: Entity with id 10 is not found by Id");
            Assert.assertEquals("Entity with id 10 not found", entityNotFoundException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testDepartmentToUpdateNotFound ()
    {
        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));

        Department departmentToUpdate = Department.builder()
                .departmentId(10)
                .departmentName("Department10")
                .build();
        try
        {
            Department department = departmentServiceImpl.update(departmentToUpdate);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            System.out.println("testDepartmentToUpdateNotFound: Entity with id 10 is not found to update");
            Assert.assertEquals("Entity with id 10 not found", entityNotFoundException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }
    //-------------------------------------------------------------------------------------------------------------
    @Test
    public void testDepartmentToDeleteNotFound ()
    {
        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));

        Department departmentToDelete = Department.builder()
                .departmentId(10)
                .departmentName("Department10")
                .build();
        try
        {
            Department department = departmentServiceImpl.remove(departmentToDelete);
            //Department department = departmentServiceImpl.getDepartmentById(departmentToDelete.getDepartmentId());
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            System.out.println("testDepartmentToDeleteNotFound: Entity with id 10 is not found to delete");
            Assert.assertEquals("Entity with id 10 not found", entityNotFoundException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }

}
