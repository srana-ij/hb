package com.bz.hb.service;



import com.bz.hb.model.security.MenuItem;

import java.util.List;
import java.util.Optional;

/**
 * User: sohel
 * Date: 30/12/20
 * Time: 11:24 AM
 */

public interface MenuItemService {
    MenuItem saveMenuItem(MenuItem menuItem);
    List<MenuItem> getMenuItemList(Long menuGroupId);
    List<Long>getAllSequences();
    void deleteMenuItem(Long id);
    Optional<MenuItem> findMenuItemById(Long id);
    MenuItem getMenuItem(Long id);
}
