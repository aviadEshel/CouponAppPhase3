package com.johnBryce.couponAppPhase2.repositories.services;

import com.johnBryce.couponAppPhase2.repositories.CompanyRepository;
import com.johnBryce.couponAppPhase2.repositories.CouponRepository;
import com.johnBryce.couponAppPhase2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;



    public ClientService() {
        super();
    }


    public abstract boolean login(String email, String password) ;

}
