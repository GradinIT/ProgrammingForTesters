package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.DepartmentModel;
import se.jocke.api.mapper.DepartmentModelMapper;
import se.jocke.department.builder.DepartmentModelTestBuilder;
import se.jocke.department.builder.DepartmentTestBuilder;
import se.jocke.department.entity.Department;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelMapper {

        private final DepartmentModel EMPLOYEE_MODEL = DepartmentModelTestBuilder.builder().build();
        private final Department EMPLOYEE = DepartmentTestBuilder.builder().build();

        @Test
        public void testDepartmentToDepartmentModelMapping() {
            DepartmentModel model = DepartmentModelMapper.map(EMPLOYEE);
            Assertions.assertAll(
                    () -> assertEquals(EMPLOYEE.getDepartmentId(), model.getDepartmentId()),
                    () -> assertEquals(EMPLOYEE.getDepartmentName(), model.getDepartmentName())
            );
        }

        @Test
        public void testDepartmentModelToDepartmentMapping() {
            Department department = DepartmentModelMapper.map(EMPLOYEE_MODEL);
            Assertions.assertAll(
                    () -> assertEquals(EMPLOYEE_MODEL.getDepartmentId(), department.getDepartmentId()),
                    () -> assertEquals(EMPLOYEE_MODEL.getDepartmentName(), department.getDepartmentName())
            );
        }
    }

