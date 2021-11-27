package com.bz.hb.service.impl;

/**
 * created by srana on 07/10/20 at 10.55 AM
 */

import com.bz.hb.exception.NotFoundException;
import com.bz.hb.facade.data.UserData;
import com.bz.hb.model.common.UserStatus;
import com.bz.hb.model.common.UserType;
import com.bz.hb.model.security.Role;
import com.bz.hb.model.security.User;
import com.bz.hb.repository.RoleRepository;
import com.bz.hb.repository.UserRepository;
import com.bz.hb.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    @NonNull private final UserRepository userRepository;
    @NonNull private final RoleRepository roleRepository;

    @Override public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUser(Long id) {
        return findUserById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.countByUsername(username) >0;
    }

    @Override
    public boolean isUserEmailExists(String email) {
        return userRepository.countByEmailAddress(email) >0;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional
    @Override public User saveRoleAssignment(User user, List<Long> roleIds) {
        Set<Role> roles = roleIds.stream()
                .map(roleId -> roleRepository.findById(roleId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public boolean isUserCreated(Long objectRefId) {
        return userRepository.countByObjectRefId(objectRefId)>0;
    }

    @Override
    public List<UserData> userList(String userName, UserType userType, UserStatus userStatus) {
        return userRepository.findUsers(userName,Objects.nonNull(userType) ? userType.getId() : null,Objects.nonNull(userStatus) ? userStatus.getId() : null);
    }
}
