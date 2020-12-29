package se.jensen.exercise.department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
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
    DepartmentService service = new DepartmentServiceImpl();

    private final String DEPARTMENTNAME = "Intellij";
    private final Integer DEPARTMENTID = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentName(DEPARTMENTNAME)
                .departmentId(1)
                .build();
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);

        when(departmentDao.findAll()).thenReturn(list);

    }
    @Test
    @DisplayName("Test To Get All Departments ")
    public void TestToGetAllDepartments() {
        List<Department> all = service.getDepartments();
        verify(departmentDao, times(1)).findAll();
        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
    }
    @Test
    @DisplayName("Test To Get Departments By Id")
    public void TestToGetDepartmentsById() {
        Integer departmentId = Integer.valueOf(1);

        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName(DEPARTMENTNAME)
                .build();

        when(departmentDao.findById(departmentId)).thenReturn(Optional.of(departmentDatabaseEntry));

        Department department = service.getDepartmentById(departmentId);
        verify(departmentDao, times(1)).findById(departmentId);
        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
        Assert.assertEquals(DEPARTMENTID, department.getDepartmentId());
    }

    @Test
    @DisplayName("Test To Delete Department")
    public void TestToDeleteDepartment(){
        List<Department> departmentsBeforeDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("\nDepartment before delete");
        departmentsBeforeDelete.stream().forEach(System.out::println);
        departmentDao.delete(DepartmentDatabaseEntryMapper.map(Department.builder()
                .departmentId(5)
                .departmentName("Engineer")
                .build()));
        List<Department> departmentsAfterDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("Departments after delete");
        departmentsAfterDelete.stream().forEach(System.out::println);
        Assert.assertEquals(1,departmentsAfterDelete.size());
    }
}
