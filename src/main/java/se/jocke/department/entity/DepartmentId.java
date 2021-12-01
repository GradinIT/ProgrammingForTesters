package se.jocke.department.entity;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import se.jocke.common.entity.EntityID;
@SuperBuilder
@EqualsAndHashCode
public class DepartmentId extends EntityID<Integer> {
}
