package se.jocke.employee.integrationtest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// public class TestEmployeeRestAPI extends EmployeeTestClient {

   // private List<EmployeeModel> employees;
   // private EmployeeModel employeeModel;


   // @When("the client calls \\/employee")
   // public void the_client_calls_employee() {
       // employees = getAllEmployees().get();
  //  }

   // @Then("the client receives {int} employees")
    //public void the_client_receives_employees(Integer amountOfEmployees) {
      //  Assertions.assertEquals(amountOfEmployees, employees.size());
   // }


  //  @When("the client gets employee {int}")
   // public void the_client_gets_employee(Integer employeeId) {
     //   employeeModel = getEmployeeById(employeeId).get();
   // }

   // @Then("firstname is (.+)")
    //public void firstname_is(String Runar) {
      //  Assertions.assertEquals(Runar, employeeModel.getFirstName());
    // }

    //@When("^the client updates firstname for employee with id {int} to (.+)$")
   // public void the_client_updates_firstname_for_employee_with_id_to_runar(Integer employeeId, String runar) {
     //   updateEmployee(EmployeeModel.builder().employeeId(1).firstName(runar).build());
   // }

   // @Then("the firstname is updated to (.+)")
   // public void the_firstname_is_updated_to_runar(String runar) {
     //   Optional<EmployeeModel> employee = getEmployeeById(1);
       // Assert.assertEquals(runar, employee.get().getFirstName());
    // }

   // @Given("the employees")
   // public void the_employees(DataTable dataTable) {
     //   makeDepartmentList(dataTable.asList())
       //         .stream()
         //       .forEach(employeeModel -> createEmployee(employeeModel));
   // }

   // private List<EmployeeModel> makeDepartmentList(List<String> given) {
     //   List<EmployeeModel> employeeModels = new ArrayList<>();
      //  for (int i = 0; i < given.size(); ) {
        //    employeeModels.add(EmployeeModel.builder()
          //          .employeeId(Integer.valueOf(given.get(i++)))
            //        .firstName(given.get(i++))
              //      .lastName(given.get(i++))
                //    .salary(new BigDecimal(given.get(i++)))
                  //  .fullTime(Boolean.valueOf(given.get(i++)))
                    //.departmentId(Integer.parseInt(given.get(i++)))
                    //.build());
        //}
        //return employeeModels;
    //}
//}
