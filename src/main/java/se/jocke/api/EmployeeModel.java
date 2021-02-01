package se.jocke.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.math.BigDecimal;

@ParametersAreNonnullByDefault
@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    @Nonnull
    private Integer employeeId;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    @Nonnull
    private BigDecimal salary;
    @Nonnull
    private Boolean fullTime;
    @Nonnull
    private Integer departmentId;


}