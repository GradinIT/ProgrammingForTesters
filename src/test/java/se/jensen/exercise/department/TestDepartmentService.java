package se.jensen.exercise.department;
import liquibase.pro.packaged.D;
import org.mockito.ArgumentCaptor;
import se.jensen.entity.DepartmentTestBuilder;
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
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper.*;
import se.jensen.service.*;

import net.minidev.json.JSONUtil;


import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Category(UnitTest.class)
public class TestDepartmentService {

    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService service = new DepartmentServiceImpl();

    DepartmentTestBuilder departmentTestBuilder = new DepartmentTestBuilder();
    DepartmentDatabaseEntryMapper departmentDatabaseEntryMapper = new DepartmentDatabaseEntryMapper();

    Integer DEPARTMENTID = Integer.valueOf(2);
    String DEPARTMENTNAME = "Testers";

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
        when(departmentDao.save(any())).thenReturn(DepartmentDatabaseEntryMapper.map(departmentTestBuilder.build()));
        //(departmentDao.delete(any()));
    }

    @Test
    public void  testGetAllDepartments()
    {
        List <Department> allDepartments = service.getDepartments(); //findAll()
        Assert.assertNotNull(allDepartments);
        Assert.assertEquals(1, allDepartments.size());

        verify(departmentDao, atLeastOnce()).findAll();
        verify(departmentDao, times(1)).findAll();
    }

    @Test
    public void testGetDepartmentById ()
    {
        Department department = service.getDepartmentById(DEPARTMENTID);    //findById()

        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTID, department.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
        verify(departmentDao, atLeastOnce()).findById(DEPARTMENTID);
        verify(departmentDao, times(1)).findById(DEPARTMENTID);
    }

    @Test
    public void testCreateDepartment()
    {
        try {
            Department createDepartment = service.create(departmentTestBuilder.build());
            Assert.assertNotNull(createDepartment);
            verify(departmentDao, times(1)).save(any());
        }
        catch (EntityAlreadyInStorageException entityAlreadyInStorageException)
        {
          Assert.assertEquals("Entity with id 1 already in storage", entityAlreadyInStorageException.getMessage());
        }
    }

    @Test
    public void testUpdateDepartment()
    {
        try
        {
            Department updateDepartment = service.update(departmentTestBuilder.build());
            Assert.assertNotNull(updateDepartment);
            verify(departmentDao, times(1)).save(any());
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            Assert.assertEquals("Entity with id 1 not found", entityNotFoundException.getMessage());
        }
    }

    @Test
    public void testRemoveDepartment()
    {
        Department removeDepartment = service.remove(departmentTestBuilder.build());
        Department dep = service.remove(any());

        Assert.assertNotNull(removeDepartment);
        verify(departmentDao, times(1)).findById(any());
    }

}

