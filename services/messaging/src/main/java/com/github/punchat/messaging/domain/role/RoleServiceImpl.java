package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.id.IdService;
import org.springframework.stereotype.Service;

import java.util.List;


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
        if (!roleRepository.existsByName(name)) {
            return roleRepository.findRoleByName(name);
        } else {
            throw new RoleAlreadyExistsException(name);
        }
    }

    @Override
    public Role editRole(String name, Role newRole) {
        if (!roleRepository.existsByName(name)) {
            Role role = roleRepository.findRoleByName(name);
            if (!role.getName().equals(newRole.getName())) {
                if (!roleRepository.existsByName(newRole.getName())) {
                    role.setName(newRole.getName());
                } else {
                    throw new RoleAlreadyExistsException(newRole.getName());
                }
            }
            if (!newRole.getPermissions().isEmpty()) {
                role.setPermissions(newRole.getPermissions());
            }
            return roleRepository.save(role);
        } else {
            throw new RoleAlreadyExistsException(name);
        }
    }

    @Override
    public Role addPermissions(String name, Permission[] permissions) {
        if (roleRepository.existsByName(name)) {
            Role role = roleRepository.findRoleByName(name);
            for (Permission permission : permissions) {
                if (!role.getPermissions().contains(permission)) {
                    role.getPermissions().add(permission);
                }
            }
            return roleRepository.save(role);
        } else {
            throw new RoleDoesNotExistException(name);
        }
    }

    @Override
    public Role excludePermissions(String name, Permission[] permissions) {
        if (roleRepository.existsByName(name)) {
            Role role = roleRepository.findRoleByName(name);
            for (Permission permission : permissions) {
                if (role.getPermissions().contains(permission)) {
                    role.getPermissions().add(permission);
                }
            }
            return roleRepository.save(role);
        } else {
            throw new RoleDoesNotExistException(name);
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
