package com.johnBryce.couponAppPhase2.repositories;

import com.johnBryce.couponAppPhase2.entities.Category;
import com.johnBryce.couponAppPhase2.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface CouponRepository extends JpaRepository<Coupon, Long> {




    Coupon findByTitle (String title);
    List<Coupon> deleteByEndDateBefore(Date date);

    ArrayList<Coupon> findAllByCategoryAndCompany_id(Category category, long companyID);




    ArrayList<Coupon> findAllByPriceLessThanEqualAndCompany_id(double maxPrice, long companyID);

    Coupon findByCompany_idAndDescription(long id , String description);

 ArrayList<Coupon> findAllByCompany_id(long id);
    Coupon findById(long id) ;
    void deleteById(long id);




    //////////////////////////////////////  restore category in query, just take the (//) away and its good


//    @Query(value = "SELECT u FROM  sql11437103.coupons  join sql11437103.customers_coupons  " +
//            "on  ( u.sql11437103.coupons.id = u.sql11437103.customers_coupons.coupon_id) " +
//            "where  u.sql11437103.customers_coupons.customer_id=  ? ", nativeQuery = true)
//    List<Coupon> findCouponsOfCustomerNative(long customerID);

   // Query(value = "select sql11437103.coupons,  from Account account, Client client where account.fkClient = client.pkClient



//    @Modifying
//    @Query(value = "update sql11454997.coupons u set " +
//               "u.category = ? ," +
//            "   u.description = ? ," +
//            "  u.end_date = ?, u.price = ?, " +
//            "u.image = ?  where u.id = ?", nativeQuery = true)
//    void updateCouponNative(
//            Category category,
//
//            String description, Date endDate,
//            double price, String image, long id);

}


