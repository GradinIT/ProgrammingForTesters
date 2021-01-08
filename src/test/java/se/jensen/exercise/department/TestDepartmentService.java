package se.jensen.exercise.department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;


import se.jensen.dao.EntityAlreadyInStorageException;
import se.jensen.dao.EntityNotFoundException;
import se.jensen.entity.Department;
import se.jensen.service.DepartmentService;
import se.jensen.service.DepartmentServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        when(departmentDao.findById(DEPARTMENTID)).thenReturn(Optional.empty());
        when(departmentDao.findById(DEPARTMENTID)).thenReturn(Optional.of(departmentDatabaseEntry));
        when(departmentDao.save(any())).thenReturn(DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build());



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
        Integer DepartmentId = 12;
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DepartmentId)
                .departmentName("Security")
                .build();
        when(departmentDao.findById(DepartmentId)).thenReturn(Optional.of(departmentDatabaseEntry));

        Department department = departmentService.getDepartmentById(DepartmentId);

        verify(departmentDao, times(1)).findById(DepartmentId);

        Assert.assertNotNull(department);
        Assert.assertEquals(DepartmentId, department.getDepartmentId());
        Assert.assertEquals("Security",department.getDepartmentName());
    }



@Test(expected = EntityAlreadyInStorageException.class)
public void createDepartment(){
        Department department = Department.builder()
                .departmentName(DEPARTMENTNAME)
                .departmentId(DEPARTMENTID)
                .build();

        Department savedDepartment = departmentService.create(department);
        verify(departmentDao,times(1)).save(any());

        Assert.assertNotNull(savedDepartment);
        Assert.assertEquals(DEPARTMENTID, savedDepartment.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, savedDepartment.getDepartmentName());


        departmentService.create(department);
    }

    @Test
    public void updateDepartment() {
        Department department = Department.builder()    // Skapa våran "department"
                .departmentName(DEPARTMENTNAME)
                .departmentId(DEPARTMENTID)
                .build();





        Department updateDepartment = departmentService.update(department);

        Assert.assertEquals(department.getDepartmentName(), updateDepartment.getDepartmentName());
        Assert.assertEquals(department.getDepartmentId(), updateDepartment.getDepartmentId());
        verify(departmentDao,times(1)).save(any());



    }









    @Test
    public void deleteDepartment() {

        Department removeDepartment = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();




        departmentService.remove(removeDepartment);
        verify(departmentDao, times(1)).delete(any());


    }



}