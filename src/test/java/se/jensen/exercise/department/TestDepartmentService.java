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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class TestDepartmentService {
    DepartmentDao departmentDao = mock(DepartmentDao.class);

    @InjectMocks
    DepartmentService departmentService = new DepartmentServiceImpl();

    private final Integer DEPARTMENTID = 10;
    private final String DEPARTMENTNAME = "Finans";

    @Before
    public void setUp (){
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
    public void testGetAll(){

        List<Department> all = departmentService.getDepartments();
        verify(departmentDao, times(1)).findAll();
        Assert.assertNotNull(all);
        Assert.assertEquals(1,all.size());

    }

    @Test
    public void testGetById() {
         Integer DepermentId = 12;
         DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                 .departmentId(DepermentId)
                 .departmentName("Säkerhet")
                 .build();
         when(departmentDao.findById(DepermentId)).thenReturn(Optional.of(departmentDatabaseEntry));
         Department department = departmentService.getDepartmentById(DepermentId);
        verify(departmentDao, times(1)).findById(DepermentId);
        Assert.assertNotNull(department);
        Assert.assertEquals(DepermentId, department.getDepartmentId());
        Assert.assertEquals("Säkerhet",department.getDepartmentName());
    }


}
