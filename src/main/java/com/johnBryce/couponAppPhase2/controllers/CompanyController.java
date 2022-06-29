package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.entities.Category;
import com.johnBryce.couponAppPhase2.entities.Company;
import com.johnBryce.couponAppPhase2.entities.Coupon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyController extends ClientController {



    public CompanyController() {

    }




    @PostMapping("/addCoupon")
    ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("company: Got a new coupon: "+coupon+", token="+token);
        if (tokenManager.isTokenExist(token)) {
            Coupon temp = companyService.addCouponToCompany(coupon);
            return new ResponseEntity<Coupon>(temp, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/updateCoupon")
    ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("company: Got a new coupon: "+coupon+", token="+token);
        if (tokenManager.isTokenExist(token)) {
            Coupon temp = companyService.updateCouponInCompany(coupon);
            return new ResponseEntity<Coupon>(temp, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("deleteCoupon")
    public ResponseEntity<?> deleteCoupon(@RequestBody int couponID, @RequestHeader String token){
        System.out.println("company: delete coupon initiated");
        if (tokenManager.isTokenExist(token)){
            companyService.deleteCopounFromCompany((long)couponID);
            return new ResponseEntity<String>("coupon deleted", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("delete coupon failed",HttpStatus.BAD_REQUEST);
    }


    @GetMapping("getCompanyCoupons")
    @ResponseBody
    public ResponseEntity<?> getCompanyCoupons(@PathVariable String token){
        System.out.println("print all coupons initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<List<Coupon>>(companyService.getAllCouponsInCompany(),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("getCouponsByCategory")

    public ResponseEntity<?> getCouponsByCategory(@RequestBody Category category, @RequestHeader String token){
        System.out.println("print coupons category initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<List<Coupon>>(companyService.getAllCompnyCouponsOfOneCategory(category),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);

    }


    @PostMapping("getCouponsByMaxPrice")

    public ResponseEntity<?> getCouponsByCategory(@RequestBody double maxPrice, @RequestHeader String token){
        System.out.println("print coupons by price initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<List<Coupon>>(companyService.getAllCouponsByPrice(maxPrice),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);

    }

    @GetMapping("companyDetails")
    @ResponseBody
    public ResponseEntity<?> getCompanyDetails( @PathVariable String token){
        System.out.println("company details printing initiated");
        if (tokenManager.isTokenExist(token)){
            return new ResponseEntity<Company>(companyService.getCompanyDetails(),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("company detail failed",HttpStatus.BAD_REQUEST);
    }
}
