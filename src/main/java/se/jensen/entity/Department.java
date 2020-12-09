package se.jensen.entity;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Department {
    private final @NonNull Integer departmentId;
    private final @NonNull String departmentName;
}
