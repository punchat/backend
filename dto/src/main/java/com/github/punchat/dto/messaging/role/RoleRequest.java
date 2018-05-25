package com.github.punchat.dto.messaging.role;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoleRequest {
    private String name;
    private Long channelId;
    private List<Permission> permissions;
}
