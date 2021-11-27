package com.bz.hb.service;

import com.bz.hb.model.security.MenuGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: sohel
 * Date: 30/12/20
 * Time: 11:24 AM
 */

import java.util.List;
import java.util.Optional;

public interface MenuGroupService {
      Page<MenuGroup> findAllMenuGroups(Pageable pageable);
      MenuGroup saveMenuGroup(MenuGroup menuGroup);
      Optional<MenuGroup> findMenuGroupById(Long id);
      MenuGroup getMenuGroup(Long id);
      List<Long>getAllSequences();
      boolean isSequenceNoExists(Long sequenceNo);
}
