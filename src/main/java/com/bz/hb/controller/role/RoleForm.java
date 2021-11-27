package com.bz.hb.controller.role;
/**
 * created by srana on 31/12/20 at 11.53 PM
 */
import com.bz.hb.model.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@NoArgsConstructor
public class RoleForm {
    private Long id;
    private String roleName;
    private String shortCode;
    private String remarks;
    private boolean activeStatus;

    public boolean isPersisted() {
        return id != null;
    }

    public RoleForm(Role role){
        populateRoleInfo(role);
    }

    private void populateRoleInfo(Role role){
       this.id=role.getId();
       this.roleName=role.getRole();
    }
}
