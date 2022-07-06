package com.johnBryce.couponAppPhase2.repositories.services;

import com.johnBryce.couponAppPhase2.entities.Category;
import com.johnBryce.couponAppPhase2.entities.Company;
import com.johnBryce.couponAppPhase2.entities.Coupon;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class CompanyService extends ClientService {
    private static long companyID = -1;
    private static Company thisCompany;

    public long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(long companyID) {
        this.companyID = companyID;
    }

    public CompanyService() {
        super();
    }
    //TODO change getcouponbyprice to sql query
    @Override
    public boolean login(String email, String password)  {
        boolean flag = false;

        try {
            thisCompany = companyRepository.findByEmailAndPassword(email,password);
            setCompanyID(thisCompany.getId());
            if (!(companyID == -1)) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "company: com.johnBryce.couponAppPhase2.program.dailyJob.login failed");

        }
        return flag;
    }

    public Coupon addCouponToCompany(Coupon coupon) {

        coupon.setCompany(thisCompany);
        coupon.setStartDate(new Date(System.currentTimeMillis()));
        coupon.setEndDate( new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(90)));
        try {
           coupon= couponRepository.save(coupon);
            return coupon;
        } catch ( Exception  e) {
            System.out.println(e.toString()+"\n" +
                    "company: adding coupon failed");
        }
        return null;
//        return true;
    }

    public Coupon updateCouponInCompany(Coupon coupon)  {
        Coupon temp = new Coupon();
        temp.setCategory(coupon.getCategory());
        temp.setDescription(coupon.getDescription());
        temp.setAmount(coupon.getAmount());
        temp.setEndDate(coupon.getEndDate());
        temp.setPrice(coupon.getPrice());
        temp.setImage(coupon.getImage());
        temp.setStartDate(coupon.getStartDate());
        temp.setId(coupon.getId());
        try {
            couponRepository.save(temp);


        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "company: updating coupon failed");
        }
        return temp;
    }

    public boolean deleteCopounFromCompany(long couponID)  {
        boolean flag = false;
        try {
            Coupon c = couponRepository.findById(couponID);
//            if (c.getCompany().getId()==companyID) {
            c.deleteAllCustomers();
//            thisCompany.getCoupons().remove(c);
            c.setCompany(null);
            couponRepository.save(c);
            couponRepository.deleteById(couponID);



                flag = true;
//            }
//            else if (c.getCompany().getId()==null){
//                couponRepository.deleteById(couponID);
//            flag=true;}
// to add a sout that coupon id not in this company?
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "company: deleting coupon failed");
        }
        return flag;
    }
    public Coupon findByTitle (String title){
        Coupon temp= new Coupon();
        try {


            temp = couponRepository.findByTitle(title);
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "customer: finding coupon by title failed");
        }
        return temp;
    }

    public ArrayList<Coupon> getAllCouponsInCompany()  {

        try {
            return couponRepository.findAllByCompany_id(companyID);
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "company: get All Coupons failed");
        }
        return null;
    }

    public ArrayList<Coupon> getAllCompnyCouponsOfOneCategory(Category category)  {
        try {
            return couponRepository.findAllByCategoryAndCompany_id(category, companyID);
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "company: get All Coupons Of One Kind failed");
        }
        return null;
    }


    public ArrayList<Coupon> getAllCouponsByPrice(double maxPrice)  {
        try {
            return  couponRepository.findAllByPriceLessThanEqualAndCompany_id(maxPrice,companyID);

        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "company: get All Coupons By Price failed");
        }
        return null;
    }
    public Company getCompanyDetails(){
        return thisCompany;
    }



}
