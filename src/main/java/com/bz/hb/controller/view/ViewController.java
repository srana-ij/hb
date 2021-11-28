package com.bz.hb.controller.view;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class ViewController {
    private static final String ROUTE_COMPANY_INFORMATION_SEARCH = "/companyInformation/search";
    private static final String ROUTE_COMPANY_INFORMATION_CREATE = "/companyInformation/create";
    private static final String ROUTE_M_BOOTH_OFFICE_SEARCH = "/mBoothOffice/search";
    private static final String ROUTE_M_BOOTH_OFFICE_CREATE = "/mBoothOffice/create";
    private static final String ROUTE_M_BOOTH_MEDIA_SEARCH = "/mBoothMedia/search";
    private static final String ROUTE_M_BOOTH_MEDIA_CREATE = "/mBoothMedia/create";
    private static final String ROUTE_MEDIA2_MEMBER_INFORMATION_SEARCH = "/media2MemberInformation/search";
    private static final String ROUTE_MEDIA2_MEMBER_INFORMATION_CREATE = "/media2MemberInformation/create";
    private static final String ROUTE_BOOTH_MEMBER_INFORMATION_SEARCH = "/boothMemberInformation/search";
    private static final String ROUTE_BOOTH_MEMBER_INFORMATION_CREATE = "/boothMemberInformation/create";
    private static final String ROUTE_PROJECT_INFORMATION_SEARCH = "/projectInformation/search";
    private static final String ROUTE_PROJECT_INFORMATION_CREATE = "/projectInformation/create";
    private static final String ROUTE_CUSTOMER_INFORMATION_SEARCH = "/customerInformation/search";
    private static final String ROUTE_CUSTOMER_INFORMATION_CREATE = "/customerInformation/create";
    private static final String ROUTE_PROJECT_LAND_SEARCH = "/projectLand/search";
    private static final String ROUTE_PROJECT_LAND_CREATE = "/projectLand/create";
    private static final String ROUTE_LAND_PURCHASE_SEARCH = "/landPurchase/search";
    private static final String ROUTE_LAND_PURCHASE_CREATE = "/landPurchase/create";
    private static final String ROUTE_REGISTRY_INFORMATION_SEARCH = "/registryInformation/search";
    private static final String ROUTE_REGISTRY_INFORMATION_CREATE = "/registryInformation/create";
    private static final String ROUTE_REGISTRY_REQUEST_SEARCH = "/registryRequest/search";
    private static final String ROUTE_REGISTRY_REQUEST_CREATE = "/registryRequest/create";
    private static final String ROUTE_M_BOOTH_MEMBER_INFORMATION_SEARCH = "/mBoothMemberInformation/search";
    private static final String ROUTE_M_BOOTH_MEMBER_INFORMATION_CREATE = "/mBoothMemberInformation/create";
    private static final String ROUTE_MEDIA1_MEMBER_INFORMATION_SEARCH = "/media1MemberInformation/search";
    private static final String ROUTE_MEDIA1_MEMBER_INFORMATION_CREATE = "/media1MemberInformation/create";
    private static final String ROUTE_COMMISSION_RATE_SEARCH = "/commissionRate/search";
    private static final String ROUTE_COMMISSION_RATE_CREATE = "/commissionRate/create";
    private static final String ROUTE_LAND_INFORMATION_SEARCH = "/landInformation/search";
    private static final String ROUTE_LAND_INFORMATION_CREATE = "/landInformation/create";
    private static final String ROUTE_PROJECT_EXPENSE_SEARCH = "/projectExpense/search";
    private static final String ROUTE_PROJECT_EXPENSE_CREATE = "/projectExpense/create";
    private static final String ROUTE_BOOKING_INFORMATION_SEARCH = "/bookingInformation/search";
    private static final String ROUTE_BOOKING_INFORMATION_CREATE = "/bookingInformation/create";
    private static final String ROUTE_PROJECT_CLOSING_SEARCH = "/projectClosing/search";
    private static final String ROUTE_PROJECT_CLOSING_CREATE = "/projectClosing/create";
    private static final String ROUTE_COLLECTION_SEARCH = "/collection/search";
    private static final String ROUTE_COLLECTION_CREATE = "/collection/create";





    @GetMapping(ROUTE_COMPANY_INFORMATION_SEARCH)
    public String companyInformationSearch(Model model) { return "/web/pages/companyInformation/search"; }
    @GetMapping(ROUTE_COMPANY_INFORMATION_CREATE)
    public String companyInformationCreate(Model model) { return "/web/pages/companyInformation/create"; }
    @GetMapping(ROUTE_M_BOOTH_OFFICE_SEARCH)
    public String mBoothOfficeSearch(Model model) { return "/web/pages/mBoothOffice/search"; }
    @GetMapping(ROUTE_M_BOOTH_OFFICE_CREATE)
    public String mBoothOfficeCreate(Model model) { return "/web/pages/mBoothOffice/create"; }
    @GetMapping(ROUTE_M_BOOTH_MEDIA_SEARCH)
    public String mBoothMediaSearch(Model model) { return "/web/pages/mBoothMedia/search"; }
    @GetMapping(ROUTE_M_BOOTH_MEDIA_CREATE)
    public String mBoothMediaCreate(Model model) { return "/web/pages/mBoothMedia/create"; }
    @GetMapping(ROUTE_MEDIA2_MEMBER_INFORMATION_SEARCH)
    public String media2MemberInformationSearch(Model model) { return "/web/pages/media2MemberInformation/search"; }
    @GetMapping(ROUTE_MEDIA2_MEMBER_INFORMATION_CREATE)
    public String media2MemberInformationCreate(Model model) { return "/web/pages/media2MemberInformation/create"; }
    @GetMapping(ROUTE_BOOTH_MEMBER_INFORMATION_SEARCH)
    public String boothMemberInformationSearch(Model model) { return "/web/pages/boothMemberInformation/search"; }
    @GetMapping(ROUTE_BOOTH_MEMBER_INFORMATION_CREATE)
    public String boothMemberInformationCreate(Model model) { return "/web/pages/boothMemberInformation/create"; }
    @GetMapping(ROUTE_PROJECT_INFORMATION_SEARCH)
    public String projectInformationSearch(Model model) { return "/web/pages/projectInformation/search"; }
    @GetMapping(ROUTE_PROJECT_INFORMATION_CREATE)
    public String projectInformationCreate(Model model) { return "/web/pages/projectInformation/create"; }
    @GetMapping(ROUTE_CUSTOMER_INFORMATION_SEARCH)
    public String customerInformationSearch(Model model) { return "/web/pages/customerInformation/search"; }
    @GetMapping(ROUTE_CUSTOMER_INFORMATION_CREATE)
    public String customerInformationCreate(Model model) { return "/web/pages/customerInformation/create"; }
    @GetMapping(ROUTE_PROJECT_LAND_SEARCH)
    public String projectLandSearch(Model model) { return "/web/pages/projectLand/search"; }
    @GetMapping(ROUTE_PROJECT_LAND_CREATE)
    public String projectLandCreate(Model model) { return "/web/pages/projectLand/create"; }
    @GetMapping(ROUTE_LAND_PURCHASE_SEARCH)
    public String landPurchaseSearch(Model model) { return "/web/pages/landPurchase/search"; }
    @GetMapping(ROUTE_LAND_PURCHASE_CREATE)
    public String landPurchaseCreate(Model model) { return "/web/pages/landPurchase/create"; }
    @GetMapping(ROUTE_REGISTRY_INFORMATION_SEARCH)
    public String registryInformationSearch(Model model) { return "/web/pages/registryInformation/search"; }
    @GetMapping(ROUTE_REGISTRY_INFORMATION_CREATE)
    public String registryInformationCreate(Model model) { return "/web/pages/registryInformation/create"; }
    @GetMapping(ROUTE_REGISTRY_REQUEST_SEARCH)
    public String registryRequestSearch(Model model) { return "/web/pages/registryRequest/search"; }
    @GetMapping(ROUTE_REGISTRY_REQUEST_CREATE)
    public String registryRequestCreate(Model model) { return "/web/pages/registryRequest/create"; }
    @GetMapping(ROUTE_M_BOOTH_MEMBER_INFORMATION_SEARCH)
    public String mBoothMemberInformationSearch(Model model) { return "/web/pages/mBoothMemberInformation/search"; }
    @GetMapping(ROUTE_M_BOOTH_MEMBER_INFORMATION_CREATE)
    public String mBoothMemberInformationCreate(Model model) { return "/web/pages/mBoothMemberInformation/create"; }
    @GetMapping(ROUTE_MEDIA1_MEMBER_INFORMATION_SEARCH)
    public String media1MemberInformationSearch(Model model) { return "/web/pages/media1MemberInformation/search"; }
    @GetMapping(ROUTE_MEDIA1_MEMBER_INFORMATION_CREATE)
    public String media1MemberInformationCreate(Model model) { return "/web/pages/media1MemberInformation/create"; }
    @GetMapping(ROUTE_COMMISSION_RATE_SEARCH)
    public String commissionRateSearch(Model model) { return "/web/pages/commissionRate/search"; }
    @GetMapping(ROUTE_COMMISSION_RATE_CREATE)
    public String commissionRateCreate(Model model) { return "/web/pages/commissionRate/create"; }
    @GetMapping(ROUTE_LAND_INFORMATION_SEARCH)
    public String landInformationSearch(Model model) { return "/web/pages/landInformation/search"; }
    @GetMapping(ROUTE_LAND_INFORMATION_CREATE)
    public String landInformationCreate(Model model) { return "/web/pages/landInformation/create"; }
    @GetMapping(ROUTE_PROJECT_EXPENSE_SEARCH)
    public String projectExpenseSearch(Model model) { return "/web/pages/projectExpense/search"; }
    @GetMapping(ROUTE_PROJECT_EXPENSE_CREATE)
    public String projectExpenseCreate(Model model) { return "/web/pages/projectExpense/create"; }
    @GetMapping(ROUTE_BOOKING_INFORMATION_SEARCH)
    public String bookingInformationSearch(Model model) { return "/web/pages/bookingInformation/search"; }
    @GetMapping(ROUTE_BOOKING_INFORMATION_CREATE)
    public String bookingInformationCreate(Model model) { return "/web/pages/bookingInformation/create"; }
    @GetMapping(ROUTE_PROJECT_CLOSING_SEARCH)
    public String projectClosingSearch(Model model) { return "/web/pages/projectClosing/search"; }
    @GetMapping(ROUTE_PROJECT_CLOSING_CREATE)
    public String projectClosingCreate(Model model) { return "/web/pages/projectClosing/create"; }
    @GetMapping(ROUTE_COLLECTION_SEARCH)
    public String collectionSearch(Model model) { return "/web/pages/collection/search"; }
    @GetMapping(ROUTE_COLLECTION_CREATE)
    public String collectionCreate(Model model) { return "/web/pages/collection/create"; }




}
