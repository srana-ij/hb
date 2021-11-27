package com.bz.hb.service;

/**
 * created by srana on 07/10/20 at 10.55 AM
 */

import com.bz.hb.facade.data.UserData;
import com.bz.hb.model.common.UserStatus;
import com.bz.hb.model.common.UserType;
import com.bz.hb.model.security.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findUserById(Long id);
    User getUser(Long id);
    User saveUser(User user);
    boolean isUsernameExists(String username);
    boolean isUserEmailExists(String email);
    List<User> findAllUsers();
    User saveRoleAssignment(User user, List<Long> roleIds);
    boolean isUserCreated(Long objectRefId);
    List<UserData>userList(String userName, UserType userType, UserStatus userStatus);

}
