package se.jocke.employee.unittest.entity;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import se.jocke.common.entity.EntityID;

@SuperBuilder
@EqualsAndHashCode
public class EmployeeID extends EntityID<Integer>{
}
