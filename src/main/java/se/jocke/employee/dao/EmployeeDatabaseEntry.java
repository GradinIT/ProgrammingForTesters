package se.jocke.employee.dao;

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
    private Integer employeeId; //Kan vara samma på flera ställen men får inte ha olika mappings med firstname och lastname
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Boolean fullTime;
    private Integer departmentId; //Behöver kanske inte vara samma, man kan ju jobba på flera olika butiker samtidigt?
}
