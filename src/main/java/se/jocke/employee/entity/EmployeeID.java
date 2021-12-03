package se.jocke.employee.entity;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import se.jocke.common.entity.EntityID;

@SuperBuilder
@EqualsAndHashCode
public class EmployeeID extends EntityID<Integer> {
}
