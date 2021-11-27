package com.bz.hb.controller.menu;


import com.bz.hb.facade.SessionManagementService;
import com.bz.hb.service.MenuGroupService;
import com.bz.hb.service.MenuItemService;
import com.bz.hb.validator.MenuFormValidator;
import com.bz.hb.model.security.MenuGroup;
import com.bz.hb.model.security.MenuItem;
import com.bz.hb.util.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class MenuController {

    @NonNull
    private final MenuGroupService menuGroupService;
    @NonNull
    private final SessionManagementService sessionManagementService;
    @NonNull
    private final MenuFormValidator menuFormValidator;
    @NonNull
    private final MenuItemService menuItemService;

    private static final String BASE_ROUTE = "/menu";
    private static final String ROUTE_GROUP_SEARCH = BASE_ROUTE + "/group/search";
    private static final String ROUTE_ITEM_SEARCH = BASE_ROUTE + "/item/search";
    private static final String ROUTE_GROUP_CREATE = BASE_ROUTE + "/group/create";
    private static final String ROUTE_GROUP_SAVE = BASE_ROUTE + "/group/save";
    private static final String ROUTE_ITEM_SAVE = BASE_ROUTE + "/item/save";
    private static final String ROUTE_ITEM_DELETE = BASE_ROUTE + "/item/delete/{id}";
    private static final String ROUTE_ITEM_EDIT = BASE_ROUTE + "/item/edit/{id}";
    public static final String  ROUTE_UPDATE = BASE_ROUTE + "/update/{id}";
    private static final String ROUTE_GROUP_LIST_PAGE = BASE_ROUTE + "/group-list-page";

    private static final int PAGE_SIZE = 1000;
    private static final String REDIRECT = "redirect:";

    @GetMapping(ROUTE_GROUP_SEARCH)
    public String searchMenuGroups(Model model) {
         populateSearchResult(model,menuGroupService.findAllMenuGroups(PageRequest.of(0, PAGE_SIZE)));
        return "/web/pages/menu/search";
    }

    private void populateSearchResult(Model model, Page<MenuGroup> groupPage) {
        model.addAttribute("groupPage", groupPage);
        int totalPages = groupPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

    @GetMapping(ROUTE_GROUP_CREATE)
    public String createMenuGroup(Model model) {
        populateMenuForm(model,new MenuForm());
        return "/web/pages/menu/create";
    }

    private void populateMenuForm(Model model, MenuForm menuForm){
        model.addAttribute("menuForm",menuForm);
        if(menuForm.isPersisted()){
            model.addAttribute("menuItems",menuItemService.getMenuItemList(menuForm.getId()));
        }
    }

    @PostMapping(ROUTE_GROUP_SAVE)
    public String saveMenuGroup(Model model, @ModelAttribute MenuForm menuForm, BindingResult result, RedirectAttributes redirectAttributes) {

        menuFormValidator.validate(menuForm,result);
        if (result.hasErrors()) {
            populateMenuForm(model,menuForm);
            return "/web/pages/menu/create";
        }

        MenuGroup menuGroup=prepareMenuGroup(menuForm);
        menuGroupService.saveMenuGroup(menuGroup);
        redirectAttributes.addFlashAttribute("message", "admin.menu.group.saved");
        return REDIRECT + BASE_ROUTE + "/update/" + menuGroup.getId();
    }

    private MenuGroup prepareMenuGroup(MenuForm menuForm){
        MenuGroup menuGroup;
        if(menuForm.isPersisted()){
             menuGroup=menuGroupService.getMenuGroup(menuForm.getId());
        }

        else {
            menuGroup = MenuGroup.builder()
                    .activeStatus(Constants.ACTIVE_STATUS)
                    .createdBy(sessionManagementService.getAuthenticatedUser().getId())
                    .build();
        }

        menuGroup.setUpdatedBy(sessionManagementService.getAuthenticatedUser().getId());
        menuGroup.setName(menuForm.getGroupName());
        if(menuForm.isPersisted()){
            menuGroup.setSequenceNo(menuForm.getSequenceNo());
            menuGroup.setActiveStatus(menuForm.isActiveStatus()==true?1:0);
        }
        else menuGroup.setSequenceNo(generateAutoSequenceNo(menuGroupService.getAllSequences()));


        return menuGroup;
    }

    @GetMapping(ROUTE_UPDATE)
    public String saveMenuUpdate(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        MenuGroup menuGroup=menuGroupService.getMenuGroup(id);
        populateMenuForm(model,new MenuForm(menuGroup));
        return "/web/pages/menu/create";
    }

    @PostMapping(ROUTE_ITEM_SAVE)
    public String saveMenuItem(Model model, @ModelAttribute MenuForm menuForm, BindingResult result, RedirectAttributes redirectAttributes) {
        MenuItem menuItem=prepareMenuItem(menuForm);
        menuItemService.saveMenuItem(menuItem);
        redirectAttributes.addFlashAttribute("message", "admin.menu.item.saved");
        return REDIRECT + BASE_ROUTE + "/update/" + menuForm.getId();
    }


    private MenuItem prepareMenuItem(MenuForm menuForm){
        MenuItem menuItem;

        if(menuForm.isItemPersisted()){
            menuItem=menuItemService.getMenuItem(menuForm.getMenuItemId());
        }
        else{
            menuItem=MenuItem.builder()
                    .activeStatus(Constants.ACTIVE_STATUS)
                    .createdBy(sessionManagementService.getAuthenticatedUser().getId())
                    .menuGroupId(menuForm.getId())
                    .sequenceNo(generateAutoSequenceNo(menuItemService.getAllSequences()))
                    .build();
        }

        menuItem.setName(menuForm.getMenuItemName());
        menuItem.setUrl(menuForm.getUrl());
        menuItem.setUpdatedBy(sessionManagementService.getAuthenticatedUser().getId());
        return menuItem;
    }


    private Long generateAutoSequenceNo(List<Long>sequenceList){
        Long sequenceNo=sequenceList.size()<1 ? 0L : Collections.max(sequenceList);
        return sequenceNo+1;
    }

    @GetMapping(ROUTE_ITEM_DELETE)
    public String deleteMenuItem(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        MenuItem menuItem = menuItemService.getMenuItem(id);
        menuItemService.deleteMenuItem(id);
        redirectAttributes.addFlashAttribute("message", "admin.menu.item.delete");
        return REDIRECT + BASE_ROUTE + "/update/" + menuItem.getMenuGroupId();
    }

    @GetMapping(ROUTE_ITEM_EDIT)
    public String editMenuItem(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        MenuItem menuItem = menuItemService.getMenuItem(id);
        MenuGroup menuGroup=menuGroupService.getMenuGroup(menuItem.getMenuGroupId());
        populateMenuForm(model,new MenuForm(menuGroup,menuItem));
        return "/web/pages/menu/create";
    }



    }
