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
    public void testOneElementInTheList() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(EmployeeTestBuilder.build());

        List<EmployeeModel> actualModelsList = EmployeeModelsMapper.map(employeeList);

        List<EmployeeModel> expectedModelList = new ArrayList<>();
        EmployeeModel model = EmployeeModelTestBuilder.build();
        expectedModelList.add(model);

        org.assertj.core.api.Assertions.assertThat(actualModelsList).containsExactlyInAnyOrder(model);

        org.assertj.core.api.Assertions.assertThat(actualModelsList).hasSameElementsAs(expectedModelList);

        Assertions.assertTrue(expectedModelList.containsAll(actualModelsList));

    }

    @Test
    public void testNull() {

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
