package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})

public class DepartmentRestApiTestGetAll extends DepartmentRestApiTestsSetUpp {


    @Test
    public void testGetAllDepartments()
    {
        Optional<List<DepartmentModel>> department = DepartmentRestServiceClient.getAllDepartments();
        Assert.assertTrue(department.isPresent());
        Assert.assertEquals(3, department.get().stream().count());
    }
}
