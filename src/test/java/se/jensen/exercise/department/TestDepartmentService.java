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
        when(departmentDao.findById(any())).thenReturn(Optional.of(databaseEntry));
        when(departmentDao.save(any(DepartmentDatabaseEntry.class))).thenReturn(databaseEntry);
            }

    @Test
    public void testgetAllDepartments() {
        List<Department> all = departmentService.getDepartments();
        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
        verify(departmentDao, times(1)).findAll();
    }
    @Test
    public void testGetDepartmentById() {
        Department dep2 = departmentService.getDepartmentById(1);
        verify(departmentDao, times(1)).findById(DEPARTMENTID);
        Assert.assertEquals(DEPARTMENTID, dep2.getDepartmentId());
        Assert.assertEquals(DEPARTNAME, dep2.getDepartmentName());
    }
    @Test
    public void testCreateDepartment() {
        Department dep1 = Department.builder()
                .departmentId(9)
                .departmentName("Accounts")
                .build();
       // Department dep9=departmentService.getDepartmentById(9);
       departmentDao.findById(9);

       Department createDeppartment = departmentService.create(dep1);
       Assert.assertNotNull(createDeppartment);
       verify(departmentDao, times(1)).save(any());
    }


   /* @Test
    public void testRemoveDepartment(){
     Department dep3= DepartmentTestBuilder.build();
     departmentService.remove(dep3);
     Assert.assertNotNull(dep3);
    }
}*/
}