package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.entities.Company;
import com.johnBryce.couponAppPhase2.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//works great

@RestController
@RequestMapping("/admin")
public class AdminController extends ClientController {
    // maybe better to seperate?
    //@Autowired
    //AdminService adminService


    public AdminController() {


    }

    /**
     * A successful login will return a valid token to the client for future requests.
     * this token will be valid for SimpleTokenManager.EXPIRATION_TIME_PERIOD_IN_MILLIS, after
     * this period passes SimpleTokenManager.removeExpiredSessions() will remove this token and
     * it'll be no longer a valid token.
     */



    @PostMapping("addCompany")
    public ResponseEntity<?> addCompany( Company company, @RequestHeader("token") String token){
        System.out.println("Got a new company: "+company+", token="+token);
        if (tokenManager.isTokenExist(token)) {
            company = adminService.addNewCompany(company);
            if (company.getId() != null) {

                return new ResponseEntity<String>( company.toString() + " was added successfully", HttpStatus.OK);
            }
            else return new ResponseEntity<String>("this company was not added to the DB",HttpStatus.BAD_REQUEST);
        }
         else return new ResponseEntity<String>("this company was not added to the DB",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("updateCompany")
    public ResponseEntity<?> updateCompany( Company company, @RequestHeader("token") String token){
        System.out.println("Got a new company: "+company+", token="+token);
        if (tokenManager.isTokenExist(token)){
            company = adminService.updateCompany(company,company);

            return new ResponseEntity<String>("company: "+company.toString()+ " updated successfully",HttpStatus.OK);
        }
        else return new ResponseEntity<String>("this company was not updated",HttpStatus.BAD_REQUEST);
    }
    //public boolean deleteCompany (Company company){
    @PostMapping("deleteCompany")
    public ResponseEntity<?> deleteCompany (int companyID, @RequestHeader("token") String token){
        System.out.println("admin: request to delete company");
        if (tokenManager.isTokenExist(token)){
            adminService.deleteCompany((long)companyID);
            return new  ResponseEntity<String>("company : "+companyID+" was deleted, ", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("this company is not listed in the DB",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getAllCompanies/{token}")
    @ResponseBody
    public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
        System.out.println("Got a request (all) from client!");
        if (tokenManager.isTokenExist(token)) {
            List<Company> companies = adminService.getAllCompanys();
            List<String> stringedCompanies = new ArrayList<>();
            for (Company c:companies){
                stringedCompanies.add(c.toString());
            }
            return new ResponseEntity<List<String>>(stringedCompanies, HttpStatus.OK);
        }
       else return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getOneCOmpany")
    @ResponseBody
    public ResponseEntity<?> getOneCompany( int companyID,@RequestHeader String token){
        System.out.println("admin: print one customer initiated");
        if (tokenManager.isTokenExist(token)){
            Company temp = adminService.getOneCompany((long) companyID);
            return new ResponseEntity<String>(temp.toString(),HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("get one Customer failed",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addCustomer")
    public ResponseEntity<?> addCustomer ( Customer customer, @RequestHeader String token){
        System.out.println("admin : add customer initiated");
        if (tokenManager.isTokenExist(token)) {
            customer = adminService.addCustomer(customer);
            if (customer.getId() != null) {
                return new ResponseEntity<String>("customer: " + customer + " added successfully", HttpStatus.OK);
            }
            else return new ResponseEntity<String>("add customer failed", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<String>("add customer failed", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer ( Customer customer, @RequestHeader String token){
        System.out.println("admin : add customer initiated");
        if (tokenManager.isTokenExist(token)){
            customer = adminService.updateCustomer(customer,customer);
            return new ResponseEntity<String>("customer: "+customer+" updated successfully", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("update customer failed", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("deleteCustomer")
    public ResponseEntity<?> deleteCustomer ( long customerID, @RequestHeader String token){
        System.out.println("admin: delete customer id "+customerID+" initiated");
        if (tokenManager.isTokenExist(token)){
            adminService.deleteCustomer(customerID);
            return new  ResponseEntity<String>("customer : "+customerID+" was deleted, ", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("this customer is not listed in the DB",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getAllCustomers/{token}")

    public ResponseEntity<?> getAllCustomers(@PathVariable String token){
        System.out.println("admin: print all customers initiated");
        if (tokenManager.isTokenExist(token)){
            List<Customer> customers = adminService.getAllCustomers();
            List<String> stringedCustomers = new ArrayList<>();
            for (Customer c:customers){
                stringedCustomers.add(c.toString());
            }
            return new ResponseEntity<List<String>>(stringedCustomers,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("printing customerts failed",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("getOneCustomer")

    public ResponseEntity<?> getOneCustomer( long customerID,@RequestHeader String token){
        System.out.println("admin: print one customer initiated");
        if (tokenManager.isTokenExist(token)){
            Customer temp = adminService.getOneCustomer( customerID);
            return new ResponseEntity<String>(temp.toString(),HttpStatus.OK);
        }
        else

        return new ResponseEntity<String>("get one Customer failed",HttpStatus.BAD_REQUEST);
    }
}
