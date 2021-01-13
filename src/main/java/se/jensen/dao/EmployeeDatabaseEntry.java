package se.jensen.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Boolean fullTime;
    private Integer departmentId;
}
