package com.bz.hb.repository;

/**
 * created by srana on 07/10/20 at 10.58 AM
 */

import com.bz.hb.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByRole(String role);
}
