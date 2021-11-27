package com.bz.hb.facade.data;

import lombok.*;

import java.util.List;

/**
 * User: rafiq
 * Date: 22/12/19
 * Time: 1:08 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    private Long menuId;
    private String menuName;
    private List<SubMenu> subMenus;

    public String getUrlFriendlyName() {
        return "menu-group-" + menuId;
    }

    public boolean hasSubMenu() {
        return subMenus != null && !subMenus.isEmpty();
    }
}
