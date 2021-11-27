package com.bz.hb.service.impl;
/**
 * created by srana on 07/10/20 at 10.58 AM
 */

import com.bz.hb.exception.NotFoundException;
import com.bz.hb.model.security.Role;
import com.bz.hb.repository.RoleRepository;
import com.bz.hb.service.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RoleServiceImpl implements RoleService {
    @NonNull private final RoleRepository roleRepository;

    @Override public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override public Role getRole(Long id) {
        return getRoles().stream().filter(role -> Objects.equals(id, role.getId())).findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
