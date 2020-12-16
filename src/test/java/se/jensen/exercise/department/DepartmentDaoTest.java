package se.jensen.exercise.department;


import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class DepartmentDaoTest {
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void a_testFindAllDepartment() {
        List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());

        Assert.assertNotNull(departments);
        Assert.assertEquals(3,departments.size());
    }

    @Test
    public void b_testFindDepartmentById() {
        Integer departmentId = Integer.valueOf(1);
        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.findById(departmentId).get());
        Assert.assertNotNull(department);
    }

    @Test
    public void testSaveNewDepartment() {
        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(
                Department.builder()
                        .departmentId(4)
                        .departmentName("Post")
                        .build())));

        List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        Assert.assertNotNull(departments);
        Assert.assertEquals(4,departments.size());
    }

    @Test
    public void c_testDeleteDepartment() {
        List<Department> departmentsBeforeDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("\nDepartment before delete");
        departmentsBeforeDelete.stream().forEach(System.out::println);
        departmentDao.delete(DepartmentDatabaseEntryMapper.map(Department.builder()
                .departmentId(4)
                .departmentName("Post")
                .build()));
        List<Department> departmentsAfterDelete = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        System.out.println("Departments after delete");
        departmentsAfterDelete.stream().forEach(System.out::println);
        Assert.assertEquals(3, departmentsAfterDelete.size());

    }
}
