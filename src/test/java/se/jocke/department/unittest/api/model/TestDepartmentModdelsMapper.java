package se.jocke.department.unittest.api.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.api.DepartmentModel;
import se.jocke.department.api.mapper.DepartmentModelsMapper;
import se.jocke.department.entity.Department;
import se.jocke.department.test.builder.DepartmentModelTestBuilder;
import se.jocke.department.test.builder.DepartmentTestBuilder;

import java.util.Arrays;
import java.util.List;

public class TestDepartmentModdelsMapper {
   @Test
   public void testDepartmentModelsMapper() {
       List<Department> DEPARTMENTS = Arrays.asList(DepartmentTestBuilder.build());
       List<DepartmentModel> DEPARTMENT_MODELS= Arrays.asList(DepartmentModelTestBuilder.build());
        List<DepartmentModel> departmentModels = DepartmentModelsMapper.map(DEPARTMENTS);
       Assertions.assertAll(
               ()->Assertions.assertNotNull(departmentModels),
               ()->Assertions.assertEquals(DEPARTMENT_MODELS,departmentModels)
       );
   }
}
