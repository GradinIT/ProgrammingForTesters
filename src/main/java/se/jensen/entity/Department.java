package se.jensen.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Department {
    private final @NonNull Integer departmentId;
    private final @NonNull String departmentName;
}
