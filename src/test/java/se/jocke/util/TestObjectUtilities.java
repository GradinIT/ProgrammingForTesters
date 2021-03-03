package se.jocke.util;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;

import java.math.BigDecimal;
import java.util.List;

public class TestObjectUtilities {

    @Test
    public void testEmployeeAttributesUtility() {
        Employee employee = Employee.builder()
                .firstName("Rikard")
                .lastName("Hedlund")
                .employeeId(EmployeeID.builder().id(1).build())
                .departmentId(Integer.valueOf(1))
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000))
                .build();
        ObjectUtility objectUtility = new ObjectUtility();
        List<Object> employeeAttributes = objectUtility.ObjectUtility(employee);
        boolean result = employeeAttributes.stream().anyMatch(e->!e.equals(null));
        Assertions.assertEquals(6,employeeAttributes.size());//Check if list has values
        Assertions.assertTrue(result); //Check if none of the values are null

    }


}
