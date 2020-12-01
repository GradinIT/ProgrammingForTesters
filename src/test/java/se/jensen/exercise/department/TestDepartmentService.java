package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;
import se.jensen.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestDepartmentService {
    DepartmentDao departmentDao= mock(DepartmentDao.class);
    @InjectMocks
    DepartmentServiceImpl departmentService= new DepartmentServiceImpl();

    private final Integer DEPARTMENTID= 1;
    private final String DEPARTNAME = "DepartmentName";

    @Before
    public void setup(){
       MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry databaseEntry= DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTNAME)
                .build();
        List<DepartmentDatabaseEntry> list= new ArrayList<>();
        list.add(databaseEntry);
        when(departmentDao.findAll()).thenReturn(list);
    }

    @Test
    public void testgetAllDepartments(){
        List<Department> all= departmentService.getDepartments();
        Assert.assertNotNull(all);
        Assert.assertEquals(1,all.size());
       verify(departmentDao,times(1)).findAll();
    }

}
