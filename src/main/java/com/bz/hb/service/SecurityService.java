package com.bz.hb.service;


import com.bz.hb.facade.data.Menu;
import com.bz.hb.model.security.MenuGroup;
import com.bz.hb.model.security.MenuItem;
import com.bz.hb.model.security.Privilege;
import com.bz.hb.model.security.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * User: sohel
 * Date: 30/12/20
 * Time: 1:02 PM
 */
public interface SecurityService {
    List<Privilege> savePrivileges(Role role, List<Long> selectedMenus);

    List<Privilege> findPrivileges(Role... roles);

    List<Menu> getMenus(Set<Role> roles);

    List<Menu> getMenus();

    List<Menu> getMenus(Role role);

    List<MenuGroup> findMenuGroups();

    List<MenuItem> findMenuItems();

    List<MenuItem> findMenuItems(MenuGroup menuGroup);

    Optional<MenuItem> findMenuItemById(Long id);
}
