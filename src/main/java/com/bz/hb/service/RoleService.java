package com.bz.hb.service;

import com.bz.hb.model.security.Role;

import java.util.List;

/**
 * created by srana on 07/10/20 at 10.57 AM
 */


public interface RoleService {
    List<Role> getRoles();
    Role getRole(Long id);
    Role saveRole(Role role);
    void deleteRole(Long id);
}
