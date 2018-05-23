package com.github.punchat.messaging.domain.role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    Role getRole(String name);

    Role editRole(String name, String newName, List<Permission> permissions);

    Role addPermissions(String name, Permission[] permissions);

    Role excludePermissions(String name, Permission[] permissions);

    List<Role> getAllRoles();
}
