package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestEmployeeModelsMapper {
    EmployeeModelsMapper list1 = new EmployeeModelsMapper();
    EmployeeModelsMapper list2 = new EmployeeModelsMapper();

    @Test
    public void testEmployeeModelsMapper() {
        Assertions.assertTrue(list1.equals(list2));

    }

    //   public void testEmployeeModelsMapper() {
    //     Assertions.assertEquals(list1,list2);
    //}


}
