package com.bz.hb.service.impl;

import com.bz.hb.service.MenuItemService;
import com.bz.hb.exception.NotFoundException;
import com.bz.hb.model.security.MenuItem;
import com.bz.hb.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User: sohel
 * Date: 29/12/20
 * Time: 11:53 PM
 */

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItem saveMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItem> getMenuItemList(Long menuGroupId) {
        return menuItemRepository.findMenusByMenuGroupIdOrderBySequenceNo(menuGroupId);
    }

    @Override
    public List<Long> getAllSequences() {
        return menuItemRepository.findAllSequence();
    }

    @Override
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public Optional<MenuItem> findMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    @Override
    public MenuItem getMenuItem(Long id) {
        return findMenuItemById(id).orElseThrow(NotFoundException::new);
    }
}
