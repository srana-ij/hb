package com.bz.hb.service.impl;

import com.bz.hb.facade.SessionManagementService;
import com.bz.hb.facade.data.Menu;
import com.bz.hb.facade.data.SubMenu;
import com.bz.hb.model.security.MenuGroup;
import com.bz.hb.model.security.MenuItem;
import com.bz.hb.model.security.Privilege;
import com.bz.hb.model.security.Role;
import com.bz.hb.repository.MenuGroupRepository;
import com.bz.hb.repository.MenuItemRepository;
import com.bz.hb.repository.PrivilegeRepository;
import com.bz.hb.service.SecurityService;
import com.bz.hb.util.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: sohel
 * Date: 29/12/20
 * Time: 1:12 PM
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SecurityServiceImpl implements SecurityService {

    @NonNull
    private final MenuGroupRepository menuGroupRepository;
    @NonNull
    private final MenuItemRepository menuItemRepository;
    @NonNull
    private final PrivilegeRepository privilegeRepository;
    @NonNull
    private final SessionManagementService sessionManagementService;

    private List<MenuGroup> menuGroups = null;
    private List<MenuItem> menuItems = null;

    @Transactional
    @Override public List<Privilege> savePrivileges(Role role, List<Long> selectedMenuItemIds) {
        privilegeRepository.deletePrivilegesByRoleId(role.getId());
        List<Privilege> privileges = selectedMenuItemIds.stream()
                .map(menItemId -> buildPrivilege(role, menItemId))
                .collect(Collectors.toList());
        return privilegeRepository.saveAll(privileges);
    }

    private Privilege buildPrivilege(Role role, Long menuItemId) {
        return Privilege.builder()
                .roleId(role.getId())
                .menuGroupId(findMenuItemById(menuItemId).map(MenuItem::getMenuGroupId).orElse(0L))
                .menuItemId(menuItemId)
                .createdBy(sessionManagementService.getAuthenticatedUser().getId())
                .updatedBy(sessionManagementService.getAuthenticatedUser().getId())
                .build();
    }

    @Override public List<Privilege> findPrivileges(Role... roles) {
        return null;
    }

    @Override public List<Menu> getMenus(Set<Role> roles) {
        Set<Long> permittedMenuItemIds = new HashSet<>();
        roles.forEach(role ->
                permittedMenuItemIds.addAll(privilegeRepository.findPrivilegesByRoleId(role.getId()).stream()
                        .map(Privilege::getMenuItemId)
                        .collect(Collectors.toSet()))
        );
        List<Menu> menus = getMenusInternal(permittedMenuItemIds);
        menus.forEach(menu ->
                menu.setMenuName(menu.getMenuName().trim()));
        menus.forEach(menu ->
                menu.setSubMenus(menu.getSubMenus().stream()
                        .filter(SubMenu::isSelected)
                        .collect(Collectors.toList())));
        return menus.stream()
                .filter(Menu::hasSubMenu)
                .collect(Collectors.toList());
    }

    @Override public List<Menu> getMenus() {
        return getMenusInternal(null);
    }

    @Override public List<Menu> getMenus(Role role) {
        Set<Long> permittedMenuItemIds = privilegeRepository.findPrivilegesByRoleId(role.getId()).stream()
                .map(Privilege::getMenuItemId)
                .collect(Collectors.toSet());
        return getMenusInternal(permittedMenuItemIds);
    }

    private List<Menu> getMenusInternal(@Nullable Set<Long> permittedMenuItemIds) {
        List<MenuGroup> menuGroups = findMenuGroups();
        System.out.println("the menu group size is : " + menuGroups.size());
        return menuGroups.stream()
                .map(menuGroup -> mapMenuGroup(menuGroup, permittedMenuItemIds))
                .collect(Collectors.toList());
    }

    private Menu mapMenuGroup(MenuGroup menuGroup, @Nullable Set<Long> permittedMenuItemIds) {
        return Menu.builder()
                .menuId(menuGroup.getId())
                .menuName(menuGroup.getName())
                .subMenus(findMenuItems(menuGroup).stream()
                        .map(menuItem -> mapMenuItem(menuItem, permittedMenuItemIds))
                        .collect(Collectors.toList()))
                .build();
    }

    private SubMenu mapMenuItem(MenuItem menuItem, @Nullable Set<Long> permittedMenuItemIds) {
        return SubMenu.builder()
                .subMenuId(menuItem.getId())
                .subMenuName(menuItem.getName().trim())
                .url(menuItem.getUrl())
                .selected(permittedMenuItemIds != null && permittedMenuItemIds.contains(menuItem.getId()))
                .build();
    }

    @Override public List<MenuGroup> findMenuGroups() {
       /* if (menuGroups == null) {
            menuGroups = menuGroupRepository.findAllByActiveStatusOrderBySequenceNo(Constants.ACTIVE_STATUS);
        }
        return menuGroups; */
       return menuGroupRepository.findAllByActiveStatusOrderBySequenceNo(Constants.ACTIVE_STATUS);
    }

    @Override public List<MenuItem> findMenuItems() {
       /* if (menuItems == null) {
            menuItems = menuItemRepository.findAllByActiveStatusOrderBySequenceNo(Constants.ACTIVE_STATUS);
        }
        return menuItems; */
       return menuItemRepository.findAllByActiveStatusOrderBySequenceNo(Constants.ACTIVE_STATUS);
    }

    @Override public List<MenuItem> findMenuItems(MenuGroup menuGroup) {
        return findMenuItems().stream()
                .filter(menuItem -> Objects.equals(menuGroup.getId(), menuItem.getMenuGroupId()))
                .collect(Collectors.toList());
    }

    @Override public Optional<MenuItem> findMenuItemById(Long id) {
        return findMenuItems().stream()
                .filter(menuItem -> Objects.equals(id, menuItem.getMenuGroupId()))
                .findFirst();
    }
}

