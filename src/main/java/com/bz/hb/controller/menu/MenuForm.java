package com.bz.hb.controller.menu;

import com.bz.hb.model.security.MenuGroup;
import com.bz.hb.model.security.MenuItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuForm {

    private Long id;
    private String groupName;
    private Long sequenceNo;
    private Long lastSequenceNo;
    private Long menuItemId;
    private String menuItemName;
    private String url;
    private boolean activeStatus;

    public boolean isPersisted() {
        return id != null;
    }
    public boolean isItemPersisted() {
        return menuItemId != null;
    }

    public MenuForm(MenuGroup menuGroup){
     populateMenuGroupInfo(menuGroup);
    }

    private void populateMenuGroupInfo(MenuGroup menuGroup){
        this.id=menuGroup.getId();
        this.groupName=menuGroup.getName();
        this.sequenceNo=menuGroup.getSequenceNo();
        if(menuGroup.getActiveStatus()==1){
            this.activeStatus=true;
        }
        else this.activeStatus=false;
    }

    public MenuForm(MenuGroup menuGroup, MenuItem menuItem){
        populateMenuGroupInfo(menuGroup);
        populateMenuItemInfo(menuItem);
    }

    private void populateMenuItemInfo(MenuItem menuItem){
       this.menuItemId=menuItem.getId();
       this.menuItemName=menuItem.getName();
       this.url=menuItem.getUrl();
    }
}
