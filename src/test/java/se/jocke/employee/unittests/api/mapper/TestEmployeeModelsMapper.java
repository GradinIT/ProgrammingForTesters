package se.jocke.employee.unittests.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import se.jocke.employee.api.mapper.EmployeeModelsMapper;
import se.jocke.employee.unittests.Builder.EmployeeModelTestBuilder;
import se.jocke.employee.unittests.Builder.EmployeeModelTestBuilderTwo;
import se.jocke.employee.unittests.Builder.EmployeeTestBuilder;
import se.jocke.employee.unittests.Builder.EmployeeTestBuilderTwo;
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
    public void testListWithTwoEntryMapFunction() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(EmployeeTestBuilder.build());
        employeeList.add(EmployeeTestBuilderTwo.build());

        List<EmployeeModel> actualModelsList = EmployeeModelsMapper.map(employeeList);

        List<EmployeeModel> expectedModelList = new ArrayList<>();
        expectedModelList.add(EmployeeModelTestBuilder.build());
        expectedModelList.add(EmployeeModelTestBuilderTwo.build());


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
