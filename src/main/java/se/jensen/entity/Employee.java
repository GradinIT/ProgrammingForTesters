package se.jensen.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import se.jensen.util.GenericToStringBuilder;

import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Employee {
    private final @NonNull Integer employeeId;
    private final @NonNull String firstName;
    private final @NonNull String lastName;
    private final @NonNull BigDecimal salary;
    private final @NonNull Boolean fullTime;
    private final @NonNull Integer departmentId;
}
