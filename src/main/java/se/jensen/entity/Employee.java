package se.jensen.entity;

import lombok.*;

import java.math.BigDecimal;
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee  {
    private final @NonNull Integer employeeId;
    private final @NonNull String firstName;
    private final @NonNull String lastName;
    private final @NonNull BigDecimal salary;
    private final @NonNull Boolean fullTime;

}
