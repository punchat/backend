package com.github.punchat.dto.messaging.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleResponse {
    private Long id;
    private String name;
    private List<Permission> permissions;
}
