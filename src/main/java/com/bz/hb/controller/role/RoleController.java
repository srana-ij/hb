package com.bz.hb.controller.role;

import com.bz.hb.controller.WebLinkFactory;
import com.bz.hb.facade.SessionManagementService;
import com.bz.hb.model.security.Role;
import com.bz.hb.model.security.User;
import com.bz.hb.service.RoleService;
import com.bz.hb.service.SecurityService;
import com.bz.hb.service.UserService;
import com.bz.hb.validator.RoleFormValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: sohel
 * Date: 30/12/20
 * Time: 22:18
 */

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class RoleController {

    @NonNull
    private final RoleService roleService;
    @NonNull private final SecurityService securityService;
    @NonNull private final WebLinkFactory webLinkFactory;
    @NonNull private final SessionManagementService sessionManagementService;
    @NonNull private final UserService userService;
    @NonNull private final RoleFormValidator roleFormValidator;

    private static final String BASE_ROUTE = "/role";
    private static final String ROUTE_CREATE =BASE_ROUTE + "/create";
    private static final String ROUTE_SAVE =BASE_ROUTE + "/save";
    public static final String ROUTE_EDIT=BASE_ROUTE + "/edit/{id}";
    public static final String ROUTE_DELETE=BASE_ROUTE + "/delete/{id}";
    public static final String ROUTE_PRIVILEGE = BASE_ROUTE + "/privileges";
    private static final String ROUTE_SAVE_PRIVILEGE = BASE_ROUTE + "/save-privileges";
    public static final String ROUTE_ROLE_ASSIGNMENT = BASE_ROUTE + "/role-assignment";
    private static final String ROUTE_SAVE_ROLE_ASSIGNMENT = BASE_ROUTE + "/save-role-assignment";
    private static final String ROUTE_AJAX_USERS = BASE_ROUTE + "/ajax-users";
    private static final String ROUTE_AJAX_USER_ROLES = BASE_ROUTE + "/ajax-user-roles";

    private static final String REDIRECT = "redirect:";

    private static final int PAGE_SIZE = 25;


    @GetMapping(ROUTE_PRIVILEGE)
    public String rolePrivileges(Model model, @RequestParam(required = false) Long roleId) {
        model.addAttribute("selectedRoleId", roleId);
        model.addAttribute("roles", roleService.getRoles());
        if (roleId == null) {
            model.addAttribute("menus", securityService.getMenus());
        } else {
            model.addAttribute("menus", securityService.getMenus(roleService.getRole(roleId)));
        }
        return "/web/pages/role/privileges";
    }
    @PostMapping(ROUTE_SAVE_PRIVILEGE)
    public String saveRolePrivileges(HttpServletRequest request, Model model, @RequestParam Long roleId, @RequestParam(required = false) Long[] selectedMenuIds, RedirectAttributes redirectAttributes) {
        Role role = roleService.getRole(roleId);
        if (selectedMenuIds != null && selectedMenuIds.length > 0) {
            securityService.savePrivileges(role, Arrays.asList(selectedMenuIds));
            request.getSession().setAttribute("userMenus", securityService.getMenus(sessionManagementService.getAuthenticatedUser().getRoleSet()));
            redirectAttributes.addFlashAttribute("message", "role.privilege.saved");
        } else {
            redirectAttributes.addFlashAttribute("error", "role.privilege.select.menu.error");
        }
        return REDIRECT + webLinkFactory.rolePrivilege(roleId);
    }

    @GetMapping(ROUTE_ROLE_ASSIGNMENT)
    public String roleAssignment(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("users", userService.findAllUsers());
        return "/web/pages/role/role-assignment";
    }

    @PostMapping(ROUTE_SAVE_ROLE_ASSIGNMENT)
    public String saveRoleAssignment(HttpServletRequest request, Model model, @RequestParam Long userId, @RequestParam(required = false) Long[] selectedRoleIds, RedirectAttributes redirectAttributes) {
        User user = userService.findUserById(userId).orElse(null);
        model.addAttribute("roles", roleService.getRoles());
        if (user == null || selectedRoleIds == null || selectedRoleIds.length == 0) {
            if (user == null) {
                model.addAttribute("error", "role.assignment.user.not.found.error");
            } else {
                model.addAttribute("error", "role.assignment.select.role.error");
            }
            model.addAttribute("selectedUserId", userId);
            return "/web/pages/role/role-assignment";
        }
        userService.saveRoleAssignment(user, Arrays.asList(selectedRoleIds));
        request.getSession().setAttribute("userMenus", securityService.getMenus(sessionManagementService.getAuthenticatedUser().getRoleSet()));
        redirectAttributes.addFlashAttribute("message", "role.assignment.saved");
        return REDIRECT + ROUTE_ROLE_ASSIGNMENT;
    }

    @GetMapping(value = ROUTE_AJAX_USER_ROLES)
    @ResponseBody
    public List<Long> getUserRoleIds(@RequestParam Long userId) {
        User user = userService.findUserById(userId).orElse(null);
        if (user == null) {
            return Collections.emptyList();
        }
        return user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList());
    }

    @GetMapping(ROUTE_CREATE)
    public String roleCreate(Model model) {
        populateRoleForm(model,new RoleForm());
        return "/web/pages/role/create";
    }

    private void populateRoleForm(Model model,RoleForm roleForm){
        model.addAttribute("roleList",roleService.getRoles());
        model.addAttribute("roleForm",roleForm);
    }

    @PostMapping(ROUTE_SAVE)
    public String saveRole(Model model, @ModelAttribute RoleForm roleForm, BindingResult result, RedirectAttributes redirectAttributes) {
        roleFormValidator.validate(roleForm,result);
        if (result.hasErrors()) {
            populateRoleForm(model,roleForm);
            return "/web/pages/role/create";
        }
        Role role=prepareRole(roleForm);
        roleService.saveRole(role);
        redirectAttributes.addFlashAttribute("message", "role.saved");
        return REDIRECT + ROUTE_CREATE;
    }


    private Role prepareRole(RoleForm roleForm){
        Role role;
        if(roleForm.isPersisted()){
            role=roleService.getRole(roleForm.getId());
        }
        else{
            role=Role.builder()
                    .build();
        }
        role.setRole(roleForm.getRoleName());

        return role;
    }

    @GetMapping(ROUTE_EDIT)
    public String editRole(Model model, @PathVariable Long id){
        Role role=roleService.getRole(id);
        populateRoleForm(model,new RoleForm(role));
        return "/web/pages/role/create";
    }

    @GetMapping(ROUTE_DELETE)
    public String deleteRole(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes){
        roleService.deleteRole(id);
        redirectAttributes.addFlashAttribute("message", "role.deleted");
        return REDIRECT + ROUTE_CREATE;
    }

}
