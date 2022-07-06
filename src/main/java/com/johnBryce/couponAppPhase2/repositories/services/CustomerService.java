package com.johnBryce.couponAppPhase2.repositories.services;


import com.johnBryce.couponAppPhase2.entities.Category;
import com.johnBryce.couponAppPhase2.entities.Coupon;
import com.johnBryce.couponAppPhase2.entities.Customer;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService extends ClientService {
    private long customerID = 0;
    private Customer thisCustomer;


    public CustomerService() {
        super();
    }


    public long getCustomerID() {
        return customerID;
    }


    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }


    @Override
    public boolean login(String email, String password)  {

        boolean flag = false;



        try {
           thisCustomer = customerRepository.findByEmailAndPassword(email, password);
            customerID = thisCustomer.getId();
            if (!(getCustomerID() == -1)) {
                flag= true;
            } else {
                System.out.println("customer com.johnBryce.couponAppPhase2.program.dailyJob.login failed-details d'ont match");

            }
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "customer: Customer com.johnBryce.couponAppPhase2.program.dailyJob.login failed");
        }

        return flag;
    }


    public boolean couponPurchaseByCustomer(Coupon coupon) {
        Date testDate = new Date(System.currentTimeMillis());
        try {
            if (thisCustomer.getPurchasedCoupons()!=null){
                for (Coupon loopCoupon : thisCustomer.getPurchasedCoupons()) {
                    if (loopCoupon.getId() == coupon.getId()) {
                        System.out.println("coupon purchase for coupon " + coupon.getId() + " stopped, coupon exists");
                        return false;
                    }
                }
            }
                if ((coupon.getAmount() <= 0) || (coupon.getEndDate().before(testDate))) {
                    System.out.println("coupon " + coupon.getTitle() + " out of inventory or out of date");
                    return false;
                } else
                    {
                 thisCustomer.addCupon(coupon);
                 coupon.addCustomer(thisCustomer);
                 coupon.setAmount(coupon.getAmount() - 1);
                 couponRepository.save(coupon);
                    return true;
                }
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "customer: coupon Purchase By Customer failed");
        }
        return false;
    }

    public List<Coupon> getAllCoupons() {
        try {
            return customerRepository.findById(customerID).getPurchasedCoupons();
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
            "customer: get All Coupons By Category failed");
        }
       return null;
    }

    public ArrayList<Coupon> getAllCouponsByCategory(Category category)  {
        try {
        ArrayList<Coupon> categoryList = new ArrayList<>() ;
            for (Coupon coupon:customerRepository.findById(customerID).getPurchasedCoupons()){
                if (coupon.getCategory()==category){
                    categoryList.add(coupon);
                }
            }
            return categoryList;
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
            "customer: get All Coupons By Category failed");
        }
        return null;
    }

    public ArrayList<Coupon> getAllCouponsByMaxPrice(Double maxPrice)  {
       try {
        ArrayList<Coupon> byPriceList = new ArrayList<>() ;
            for (Coupon coupon: customerRepository.findById(customerID).getPurchasedCoupons()){
                if (coupon.getPrice()<= maxPrice){
                    byPriceList.add(coupon);
                }
            }
            return byPriceList;
        }
       catch (Exception e) {
            System.out.println(e.toString()+"\n" +
            "customer: get All Coupons By max price failed");
        }
        return null;
    }

    public boolean deleteCouponPurchase(long id){
        boolean flag =false;
        try {
           Coupon coupon = couponRepository.findById(id);

               if (thisCustomer.getPurchasedCoupons().contains(coupon)){
                   thisCustomer.getPurchasedCoupons().remove(coupon);
                   coupon.getCustomerPurchase().remove(thisCustomer);
                   coupon.setAmount(coupon.getAmount()+1);
                   couponRepository.save(coupon);
                   customerRepository.save(thisCustomer);
                   flag= true;
           }
        }catch (Exception  e) {
            System.out.println(e.toString()+"\n" +
                    "customer: get Customer Details failed");
        }
        return flag;
    }

    public Customer getCustomerDetails(){
        return thisCustomer;
    }

}
