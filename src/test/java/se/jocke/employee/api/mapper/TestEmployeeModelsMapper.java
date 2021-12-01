package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import se.jocke.employee.Builder.EmployeeModelTestBuilder;
import se.jocke.employee.Builder.EmployeeTestBuilder;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class TestEmployeeModelsMapper {

    @Test
    public void testListWithOneEntryMapFunction() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(EmployeeTestBuilder.build());

        List<EmployeeModel> actualModelsList = EmployeeModelsMapper.map(employeeList);

        List<EmployeeModel> expectedModelList = new ArrayList<>();
        EmployeeModel model = EmployeeModelTestBuilder.build();
        expectedModelList.add(model);

        Assertions.assertTrue(expectedModelList.containsAll(actualModelsList));

    }

    @Test
    public void testNullOnEmployeeModelsMapper() {

        Assertions.assertThrows(NullPointerException.class,
                () ->
                        EmployeeModelsMapper.map(null));

    }

    @Test
    public void testNullInTheList() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(null);
        Assertions.assertThrows(NullPointerException.class,
                () ->
                        EmployeeModelsMapper.map(employeeList));
    }
}
