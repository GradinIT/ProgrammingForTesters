package se.jensen.dao;

import lombok.*;

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
    private  Integer employeeId;
    private  String firstName;
    private  String lastName;
    private  BigDecimal salary;
    private  Boolean fullTime;

}
