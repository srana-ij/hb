package com.bz.hb.service.impl;

import com.bz.hb.exception.NotFoundException;
import com.bz.hb.model.security.MenuGroup;
import com.bz.hb.repository.MenuGroupRepository;
import com.bz.hb.service.MenuGroupService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class MenuGroupServiceImpl implements MenuGroupService {


    @NonNull
    private final MenuGroupRepository menuGroupRepository;

    @Override
    public Page<MenuGroup> findAllMenuGroups(Pageable pageable) {
        return menuGroupRepository.findAllMenuGroups(pageable);
    }

    @Override
    public MenuGroup saveMenuGroup(MenuGroup menuGroup) {
        return menuGroupRepository.save(menuGroup);
    }

    @Override
    public Optional<MenuGroup> findMenuGroupById(Long id) {
        return menuGroupRepository.findById(id);
    }

    @Override
    public MenuGroup getMenuGroup(Long id) {
        return findMenuGroupById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Long> getAllSequences() {
        return menuGroupRepository.findAllSequence();
    }

    @Override
    public boolean isSequenceNoExists(Long sequenceNo) {
        return menuGroupRepository.countBySequenceNo(sequenceNo)>0;
    }
}
