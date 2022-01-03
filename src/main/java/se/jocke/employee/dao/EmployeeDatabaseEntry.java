package se.jocke.employee.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "EMPLOYEE")
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDatabaseEntry {
    @Id
    private @NonNull Integer employeeId;
    private @NonNull String firstName;
    private @NonNull String lastName;
    private @NonNull BigDecimal salary;
    private @NonNull Boolean fullTime;
    private @NonNull Integer departmentId;
}
