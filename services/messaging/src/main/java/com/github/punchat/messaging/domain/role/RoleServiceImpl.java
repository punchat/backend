package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.id.IdService;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO add mewmbership check
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final IdService idService;

    public RoleServiceImpl(RoleRepository roleRepository, IdService idService) {
        this.roleRepository = roleRepository;
        this.idService = idService;
    }

    @Override
    public Role createRole(Role role) {
        if (!roleRepository.existsByName(role.getName())) {
            role.setId(idService.next());
            return roleRepository.save(role);
        } else {
            throw new RoleAlreadyExistsException(role.getName());
        }
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleDoesNotExistException(name));
    }

    @Override
    public Role editRole(String name, String newName, List<Permission> permissions) {
        Role role = getRole(name);
        if (!role.getName().equals(newName)) {
            if (!roleRepository.existsByName(newName)) {
                role.setName(newName);
            } else {
                throw new RoleAlreadyExistsException(newName);
            }
        }
        if (!permissions.isEmpty()) {
            role.setPermissions(permissions);
        }
        return roleRepository.save(role);
    }

    @Override
    public Role addPermissions(String name, Permission[] permissions) {
        Role role = getRole(name);
        for (Permission permission : permissions) {
            if (!role.getPermissions().contains(permission)) {
                role.getPermissions().add(permission);
            }
        }
        return roleRepository.save(role);
    }

    @Override
    public Role excludePermissions(String name, Permission[] permissions) {
        Role role = getRole(name);
        for (Permission permission : permissions) {
            if (role.getPermissions().contains(permission)) {
                role.getPermissions().add(permission);
            }
        }
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
