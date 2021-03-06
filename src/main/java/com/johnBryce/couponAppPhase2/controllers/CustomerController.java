package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.entities.Category;
import com.johnBryce.couponAppPhase2.entities.Coupon;
import com.johnBryce.couponAppPhase2.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController extends ClientController  {

    public CustomerController()  {
    }

    @PostMapping("couponPurchase")
    public ResponseEntity<?> purchaseCoupon( String title, @RequestHeader String token){
        System.out.println("customer purchase initiated");
        if (tokenManager.isTokenExist(token)){
            Coupon temp = companyService.findByTitle(title);
            customerService.couponPurchaseByCustomer(temp);
            return new ResponseEntity<String>("found coupon:"+temp,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("coupon purchase failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping ("getCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader String token){
        System.out.println("printing customer coupons initiated");
        if (tokenManager.isTokenExist(token)){
            List<Coupon> coupons = customerService.getAllCoupons();
           return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("printing failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCouponsByCategory")
    public ResponseEntity<?> getCustomerCouponsByCategory( Category category, @RequestHeader String token){
        System.out.println("print coupons by price initiated");
        if (tokenManager.isTokenExist(token)){
            List<Coupon> coupons = customerService.getAllCouponsByCategory(category);
           return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCouponsByPrice")
    public ResponseEntity<?> getCustomerCouponsByPrice( double maxPrice, @RequestHeader String token){
        System.out.println("print coupons by price initiated");
        if (tokenManager.isTokenExist(token)){
            List<Coupon> coupons = customerService.getAllCouponsByMaxPrice(maxPrice);
           return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("customer fetching coupons failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("CustomerDetails")

    public ResponseEntity<?> getCustomerDetails( @RequestHeader String token){
        System.out.println("customer details printing initiated");
        if (tokenManager.isTokenExist(token)){
            Customer customer = customerService.getCustomerDetails();
            return new ResponseEntity<Customer>(customer,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("customer detail failed",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("deleteCouponPurchase")
    public ResponseEntity<?> delteCuponPurchase(long id,@RequestHeader String token){
        System.out.println("delete coupon purchase initiated");
        if (tokenManager.isTokenExist(token)){
            customerService.deleteCouponPurchase(id);
            return new ResponseEntity<String>("coupon purchase deleted",HttpStatus.OK);
        }
        else return new ResponseEntity<String>("coupon purchase failed",HttpStatus.BAD_REQUEST);
    }
}
