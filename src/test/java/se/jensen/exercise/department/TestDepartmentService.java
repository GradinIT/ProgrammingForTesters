package se.jensen.exercise.department;
import com.google.common.cache.CacheBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.DepartmentTestBuilder;
import se.jensen.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.mockito.Mockito.*;

public class TestDepartmentService {
    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    private final Integer DEPARTMENTID = Integer.valueOf(1);
    private final String DEPARTNAME = "DepartmentName";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry databaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTNAME)
                .build();
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(databaseEntry);
        when(departmentDao.findAll()).thenReturn(list);
        when(departmentDao.findById(any())).thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTNAME)
                .build()));
        when(departmentDao.save(any())).thenReturn(DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTNAME)
                .build());
            }

    @Test
    public void testGetAllDepartments() {
        List<Department> all = departmentService.getDepartments();
        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
        verify(departmentDao, times(1)).findAll();
    }
    @Test
    public void testGetDepartmentById() {
        Department dep2 = departmentService.getDepartmentById(DEPARTMENTID);
        verify(departmentDao, times(1)).findById(DEPARTMENTID);
        Assert.assertEquals(DEPARTMENTID, dep2.getDepartmentId());
        Assert.assertEquals(DEPARTNAME, dep2.getDepartmentName());
    }
    @Test
    public void testCreateDepartment() {
        when(departmentDao.findById(any()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTNAME)
                        .build()));
        Department dep1 = Department.builder()
                .departmentName(DEPARTNAME)
                .departmentId(DEPARTMENTID)
                .build();

       Department createDeppartment = departmentService.create(dep1); //then return: empty
       Assert.assertNotNull(createDeppartment);
       verify(departmentDao, times(1)).save(any());
       Department savedDepartment= departmentService.getDepartmentById(DEPARTMENTID); // then return: databaseentry
       Assert.assertNotNull(savedDepartment);
       Assert.assertEquals(DEPARTMENTID,savedDepartment.getDepartmentId());
       Assert.assertEquals(DEPARTNAME,savedDepartment.getDepartmentName());
        verify(departmentDao, times(2)).findById(DEPARTMENTID);
    }
    @Test
    public void testRemoveDepartment(){
     Department dep3= DepartmentTestBuilder.build();
     departmentService.remove(dep3);
    }
   @Test
    public void testUdateDepartment(){
        Department dep= Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTNAME)
                .build();
        Department updatedDepartment= departmentService.update(dep);
        Assert.assertNotNull(updatedDepartment);
        verify(departmentDao,times(1)).findById(any());
        verify(departmentDao,times(1)).save(any());
        Assert.assertEquals(DEPARTMENTID,updatedDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTNAME,updatedDepartment.getDepartmentName());
   }
}
