package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})


public class DepartmentRestApiTestGetByID extends DepartmentRestApiTestsSetUpp{



    @Test
    public void testGetDepartmentById()
    {
        Optional<DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(3);
        Assert.assertTrue(department.isPresent());
        DepartmentModel departmentModel = department.get();
        Assert.assertEquals(Integer.valueOf(3), departmentModel.getDepartmentId());
        Assert.assertEquals("Management", departmentModel.getDepartmentName());
    }

    @Test
    public void TestErrorHandling()
    {
        try
        {
            Optional <DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(10);
        }
        catch (HttpClientErrorException e)
        {
            Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            Assert.assertEquals("404 : [Entity with id 10 not found]", e.getMessage());
        }
    }
}
