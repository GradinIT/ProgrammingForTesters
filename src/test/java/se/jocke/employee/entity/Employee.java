package se.jocke.employee.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Getter
@ToString

public class Employee {
    private final @NonNull Integer employeeId;
    private final @NonNull String employeeName;


}
