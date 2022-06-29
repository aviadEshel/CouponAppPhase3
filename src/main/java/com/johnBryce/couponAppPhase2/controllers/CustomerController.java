package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.entities.Category;
import com.johnBryce.couponAppPhase2.entities.Coupon;
import com.johnBryce.couponAppPhase2.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController extends ClientController  {

    public CustomerController()  {
    }




    @PostMapping("couponPurchase")
    public ResponseEntity<?> purchaseCoupon(@RequestBody String title, @RequestHeader String token){
        System.out.println("customer purchase initiated");
        if (tokenManager.isTokenExist(token)){
            Coupon temp = companyService.findByTitle(title);
            customerService.couponPurchaseByCustomer(temp);
            return new ResponseEntity<String>("found coupon:"+temp,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("coupon purchase failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping ("getCoupons")
    @ResponseBody
    public ResponseEntity<?> getCustomerCoupons(@PathVariable String token){
        System.out.println("printing customer coupons initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<List<Coupon>>(customerService.getAllCoupons(),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("printing failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCouponsByCategory")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestBody Category category, @RequestHeader String token){
        System.out.println("print coupons by price initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<List<Coupon>>(customerService.getAllCouponsByCategory(category),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCouponsByPrice")
    public ResponseEntity<?> getCustomerCouponsByPrice(@RequestBody double maxPrice, @RequestHeader String token){
        System.out.println("print coupons by price initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<List<Coupon>>(customerService.getAllCouponsByMaxPrice(maxPrice),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("CustomerDetails")
    @ResponseBody
    public ResponseEntity<?> getCustomerDetails( @PathVariable String token){
        System.out.println("company details printing initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<Customer>(customerService.getCustomerDetails(),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("company detail failed",HttpStatus.BAD_REQUEST);
    }
}
