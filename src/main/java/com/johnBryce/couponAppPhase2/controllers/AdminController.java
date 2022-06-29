package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.entities.Company;
import com.johnBryce.couponAppPhase2.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
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
     * @param
     * @return
     */



    @PostMapping("addCompany")
    public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader("token") String token){
        System.out.println("Got a new company: "+company+", token="+token);
        if (tokenManager.isTokenExist(token)){
       company = adminService.addNewCompany(company);

           return new ResponseEntity<String>("company: "+company+ " was added successfully",HttpStatus.OK);
       }
         else return new ResponseEntity<String>("this company was not added to the DB",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("updateCompany")
    public ResponseEntity<?> updateCompany(@RequestBody Company company, @RequestHeader("token") String token){
        System.out.println("Got a new company: "+company+", token="+token);
        if (tokenManager.isTokenExist(token)){
            company = adminService.updateCompany(company,company);

            return new ResponseEntity<String>("company: "+company+ " updated successfully",HttpStatus.OK);
        }
        else return new ResponseEntity<String>("this company was not updated",HttpStatus.BAD_REQUEST);
    }
    //public boolean deleteCompany (Company company){
    @PostMapping("deleteCompany")
    public ResponseEntity<?> deleteCompany (@RequestBody int companyID, @RequestHeader("token") String token){
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
            List<Company> res = adminService.getAllCompanys();
            return new ResponseEntity<List<Company>>(res, HttpStatus.OK);
        }
       else return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getOneCOmpany")
    @ResponseBody
    public ResponseEntity<?> getOneCompany(@RequestBody int companyID,@RequestHeader String token){
        System.out.println("admin: print one customer initiated");
        if (tokenManager.isTokenExist(token)){
            Company temp = adminService.getOneCompany((long) companyID);
            return new ResponseEntity<String>("customer "+temp.getName()+"; "+temp+",",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("get one Customer failed",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addCustomer")
    public ResponseEntity<?> addCustomer (@RequestBody Customer customer, @RequestHeader String token){
        System.out.println("admin : add customer initiated");
        if (tokenManager.isTokenExist(token)){
         customer =  adminService.addCustomer(customer);
            return new ResponseEntity<String>("customer: "+customer+" added successfully", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("add customer failed", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer (@RequestBody Customer customer, @RequestHeader String token){
        System.out.println("admin : add customer initiated");
        if (tokenManager.isTokenExist(token)){
            customer = adminService.updateCustomer(customer,customer);
            return new ResponseEntity<String>("customer: "+customer+" updated successfully", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("update customer failed", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("deleteCustomer")
    public ResponseEntity<?> deleteCustomer (@RequestBody int customerID, @RequestHeader String token){
        System.out.println("admin: delete customer id "+customerID+" initiated");
        if (tokenManager.isTokenExist(token)){
            adminService.deleteCustomer((long)customerID);
            return new  ResponseEntity<String>("customer : "+customerID+" was deleted, ", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("this customer is not listed in the DB",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getAllCustomers/{token}")
    @ResponseBody
    public ResponseEntity<?> getAllCustomers(@PathVariable String token){
        System.out.println("admin: print all customers initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<List<Customer>>(adminService.getAllCustomers(),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("printing customerts failed",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("getOneCustomer")
    @ResponseBody
    public ResponseEntity<?> getOneCustomer(@RequestBody int customerID,@RequestHeader String token){
        System.out.println("admin: print one customer initiated");
        if (tokenManager.isTokenExist(token)){
            Customer temp = adminService.getOneCustomer((long) customerID);
            return new ResponseEntity<String>("customer "+temp.getFirstName()+"; "+temp+",",HttpStatus.OK);
        }
        else

        return new ResponseEntity<String>("get one Customer failed",HttpStatus.BAD_REQUEST);
    }
}
