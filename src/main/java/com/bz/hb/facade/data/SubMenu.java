package com.bz.hb.facade.data;

import lombok.*;

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
public class SubMenu {
    private Long subMenuId;
    private String subMenuName;
    private String url;
    private boolean selected;

    public String getUrlFriendlyName() {
        return "menu-item-" + subMenuId;
    }
}
