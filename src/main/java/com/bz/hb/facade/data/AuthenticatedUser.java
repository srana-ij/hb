package com.bz.hb.facade.data;

/**
 * created by srana on 07/10/20 at 10.50 AM
 */

import com.bz.hb.model.common.UserType;
import com.bz.hb.model.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {
    private Long id;
    private String username;
    private String fullName;
    private Set<Role> roleSet;
    private UserType userType;

    public boolean isAdmin() {
       // return roleSet.stream().anyMatch(role -> StringUtils.equalsIgnoreCase(role.getRole(), "ADMIN"));
        return userType.equals(UserType.ADMIN);
    }

}
