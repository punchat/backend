package com.github.punchat.messaging.domain.role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    Role getRole(String name);

    Role editRole(String name, Role newRole);

    Role addPermission(String name, Permission permission);

    Role excludePermission(String name, Permission permission);

    List<Role> getAllRoles();
}
