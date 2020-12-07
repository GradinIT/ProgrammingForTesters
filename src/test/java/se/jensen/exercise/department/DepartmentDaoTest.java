package se.jensen.exercise.department;

import se.jensen.dao.DepartmentDao;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.dao.mapper.EmployeePojoMapper;
import se.jensen.entity.Department;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.jensen.exercise.DepartmentTestBuilder;

import javax.ws.rs.core.Application;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})

public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void testSaveNewDepartment() {


    }
}

