package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.controllerVerifacationTools.Credentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
// works great

@RestController
@RequestMapping( "/login")
public class LoginController extends ClientController {

    public LoginController() {
        super();
    }



    @PostMapping( "/company-redirect")
    public ResponseEntity<?> CompanyLogin( Credentials cred) {
        System.out.println(new Date()+": Got a new login: "+cred);
        if (companyService.login(cred.getEmail(),cred.getPassword())) {
            String token = tokenManager.getNewToken();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }



    @PostMapping( "/customer-redirect")
        public ResponseEntity<?> CustomerLogin( Credentials cred)
        {
            System.out.println(new Date()+": Got a new login: "+cred);
            if (customerService.login(cred.getEmail(),cred.getPassword())) {
                String token = tokenManager.getNewToken();
                return new ResponseEntity<String>(token, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
        }

    @PostMapping( "/admin-redirect")
    public ResponseEntity<?> AdminLogin( Credentials cred)
    {
        System.out.println(new Date()+": Got a new login: "+cred);
        if (adminService.login(cred.getEmail(),cred.getPassword())) {
            String token = tokenManager.getNewToken();
            return new ResponseEntity<String>( token, HttpStatus.OK);
        }
        else return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }
}


