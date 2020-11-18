package se.jensen.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class DepartmentModel {
    private final Integer departmentId;
    private final String departmentName;
}
