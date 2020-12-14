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
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

import javax.ws.rs.core.Application;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes ={Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void a_testSaveNewDepartment(){
        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper
                .map(DepartmentTestBuilder.build())));
        Assert.assertNotNull(department);
    }

    @Test
    public void b_testFindAllDepartments(){
        List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        Assert.assertNotNull(departments);
    }
    @Test
    public void c_testFindDepartmentById(){
        Integer department_Id = Integer.valueOf(1);
       Department department  = DepartmentDatabaseEntryMapper.map(departmentDao.findById(department_Id).get());
       Assert.assertNotNull(department);
       Assert.assertEquals(department_Id,department.getDepartmentId());
    }
    @Test
    public void d_testDeleteDepartment(){
       List<Department> departmentList = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
       Assert.assertEquals(3,departmentList.size());
        departmentDao.delete(DepartmentDatabaseEntryMapper.map(DepartmentTestBuilder.build()));
        List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        Assert.assertEquals(2,departments.size());

    }



}
