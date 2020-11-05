package se.jensen.entity;

import lombok.*;
import se.jensen.util.GenericToStringBuilder;

import java.math.BigDecimal;
@Builder
@Getter
@EqualsAndHashCode
public class Employee  {
    private final @NonNull Integer employeeId;
    private final @NonNull String firstName;
    private final @NonNull String lastName;
    private final @NonNull BigDecimal salary;
    private final @NonNull Boolean fullTime;

    @Override
    public String toString() {
        return GenericToStringBuilder.toString(this);
    }
}
