package com.bz.hb.repository;

import com.bz.hb.model.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: sohel
 * Date: 30/12/20
 * Time: 11:24 AM
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    List<Privilege> findPrivilegesByRoleId(Long roleId);

    @Modifying
    void deletePrivilegesByRoleId(Long roleId);
}
