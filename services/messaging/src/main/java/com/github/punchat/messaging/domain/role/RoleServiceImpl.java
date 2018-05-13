package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.id.IdService;
import org.springframework.stereotype.Service;

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
        role.setId(idService.next());
        return roleRepository.save(role);
    }
}
