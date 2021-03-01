package se.jocke.employee.integrationtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.web.service.EmployeeRestService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeRestService.class)
@ComponentScan("se.jocke") // letar upp rätt böna av typen EmployeeService (vet ej varför)
public class EmployeeRestServiceIntTest { // var inte public per default

    @Autowired
    private MockMvc mvc;

    @Test
    void getEmployeeById() throws Exception {
        mvc.perform(get("/employee/1")) // letar upp rätt metod med annotering @GetMapper(urlTemplate), EmployeeRestService.getEmployeeById()
            .andExpect(content().string("{\"employeeId\":1,\"firstName\":\"firstName1\",\"lastName\":\"lastName1\",\"salary\":25000.00,\"fullTime\":true,\"departmentId\":1}")
        );
    }

    @Test
    void getEmployees() throws Exception {
        RequestBuilder getRequest = get("/employee"); // letar upp rätt metod med annotering @GetMapper(urlTemplate), EmployeeRestService.getEmployees()
        MvcResult result = mvc.perform(getRequest).andReturn();

        System.out.println(result.getResponse().getContentAsString());

//        // Funkar när jag kör det enskilt men failar i build
//        assertEquals(
//                "[{\"employeeId\":1,\"firstName\":\"firstName1\",\"lastName\":\"lastName1\",\"salary\":25000.00,\"fullTime\":true,\"departmentId\":1},{\"employeeId\":2,\"firstName\":\"firstName2\",\"lastName\":\"lastName2\",\"salary\":25000.00,\"fullTime\":true,\"departmentId\":1},{\"employeeId\":3,\"firstName\":\"firstName3\",\"lastName\":\"lastName3\",\"salary\":25000.00,\"fullTime\":true,\"departmentId\":1}]",
//                result.getResponse().getContentAsString()
//        ); // vad bör jag asserta den mot?
    }

//    @Test
//    void createOrUpdateEmployee(@RequestBody EmployeeModel employeeModel) throws Exception {
//        employeeModel = EmployeeModelTestBuilder.builderMethod().build();
//
//        RequestBuilder postRequest = post("/employee", employeeModel);
//        MvcResult result = mvc.perform(postRequest).andReturn();
//
//        System.out.println("String:\n\n"+result.getResponse().getContentAsString()+"\n\n****************************");
//    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}