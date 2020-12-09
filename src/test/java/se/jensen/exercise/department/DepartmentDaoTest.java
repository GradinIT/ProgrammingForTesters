package se.jensen.exercise.department;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
//import se.jensen.exercise.DepartmentTestBuilder;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

import javax.ws.rs.core.Application;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})
public class DepartmentDaoTest {
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void testSaveNewDepartment(){
        Department department = DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(
                DepartmentTestBuilder.build())));

        List<Department> departments = DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
        Assert.assertNotNull(department);
    }
}
