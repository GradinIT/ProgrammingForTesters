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
import se.jensen.dao.DepartmentDatabaseEntry;


import javax.ws.rs.core.Application;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})

public class DaoDepartmentFindAll {
    @Autowired
    private DepartmentDao departmentDao;


    @Test
    public void TestFindAllDepartment(){


        List <DepartmentDatabaseEntry> allDeparments = departmentDao.findAll();

        Assert.assertEquals(3, allDeparments.size());




    }





}