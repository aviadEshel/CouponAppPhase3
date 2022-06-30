package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.entities.Category;
import com.johnBryce.couponAppPhase2.entities.Company;
import com.johnBryce.couponAppPhase2.entities.Coupon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController extends ClientController {



    public CompanyController() {

    }




    @PostMapping("/addCoupon")
    ResponseEntity<?> addCoupon( Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("company: Got a new coupon: "+coupon+", token="+token);
        if (tokenManager.isTokenExist(token)) {
            Coupon temp = companyService.addCouponToCompany(coupon);
            return new ResponseEntity<String>(temp.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/updateCoupon")
    ResponseEntity<?> updateCoupon( Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("company: Got a new coupon: "+coupon+", token="+token);
        if (tokenManager.isTokenExist(token)) {
            Coupon temp = companyService.updateCouponInCompany(coupon);
            return new ResponseEntity<String>(temp.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("deleteCoupon")
    public ResponseEntity<?> deleteCoupon(long couponID, @RequestHeader String token){
        System.out.println("company: delete coupon initiated");
        if (tokenManager.isTokenExist(token)){
            companyService.deleteCopounFromCompany(couponID);
            return new ResponseEntity<String>("coupon deleted", HttpStatus.OK);
        }
        else return new ResponseEntity<String>("delete coupon failed",HttpStatus.BAD_REQUEST);
    }


    @GetMapping("getCompanyCoupons")

    public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token){
        System.out.println("print all coupons initiated");
        if (tokenManager.isTokenExist(token)){
            List<Coupon> coupons = companyService.getAllCouponsInCompany();
            List<String> stringedCoupons = new ArrayList<>();
            for (Coupon c:coupons){
                stringedCoupons.add(c.toString());
            }
            return new ResponseEntity<List<String>>(stringedCoupons,HttpStatus.OK);
            //  return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("getCouponsByCategory")

    public ResponseEntity<?> getCouponsByCategory( Category category, @RequestHeader String token){
        System.out.println("print coupons category initiated");
        if (tokenManager.isTokenExist(token)){
            List<Coupon> coupons = companyService.getAllCompnyCouponsOfOneCategory(category);
            List<String> stringedCoupons = new ArrayList<>();
            for (Coupon c:coupons){
                stringedCoupons.add(c.toString());
            }
            return new ResponseEntity<List<String>>(stringedCoupons,HttpStatus.OK);
            //  return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);

    }


    @PostMapping("getCouponsByMaxPrice")

    public ResponseEntity<?> getCouponsByCategory( double maxPrice, @RequestHeader String token){
        System.out.println("print coupons by price initiated");
        if (tokenManager.isTokenExist(token)){
            List<Coupon> coupons = companyService.getAllCouponsByPrice(maxPrice);
            List<String> stringedCoupons = new ArrayList<>();
            for (Coupon c:coupons){
                stringedCoupons.add(c.toString());
            }
            return new ResponseEntity<List<String>>(stringedCoupons,HttpStatus.OK);
          //  return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
        }
        else return new ResponseEntity<String>("fetching coupons failed",HttpStatus.BAD_REQUEST);

    }

    @GetMapping("companyDetails")

    public ResponseEntity<?> getCompanyDetails( @RequestHeader String token){
        System.out.println("company details printing initiated");
        if (tokenManager.isTokenExist(token)){
            Company company = companyService.getCompanyDetails();
            return new ResponseEntity<String>(company.toString(),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("company detail failed",HttpStatus.BAD_REQUEST);
    }
}
