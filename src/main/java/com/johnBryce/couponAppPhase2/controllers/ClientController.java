package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.controllerVerifacationTools.TokenManager;
import com.johnBryce.couponAppPhase2.repositories.services.AdminService;
import com.johnBryce.couponAppPhase2.repositories.services.CompanyService;
import com.johnBryce.couponAppPhase2.repositories.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientController {
    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CompanyService companyService;
@Autowired
    TokenManager tokenManager;
   //  abstract boolean login(String email, String password);

}
