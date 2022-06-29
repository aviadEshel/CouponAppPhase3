//package com.johnBryce.couponAppPhase2.test;
//
//import com.johnBryce.couponAppPhase2.dailyJob.CouponExpirationDailyJob;
//import com.johnBryce.couponAppPhase2.entities.Category;
//import com.johnBryce.couponAppPhase2.entities.Company;
//import com.johnBryce.couponAppPhase2.entities.Coupon;
//import com.johnBryce.couponAppPhase2.entities.Customer;
//import com.johnBryce.couponAppPhase2.repositories.services.AdminService;
//import com.johnBryce.couponAppPhase2.repositories.services.CompanyService;
//import com.johnBryce.couponAppPhase2.repositories.services.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.util.concurrent.TimeUnit;
//
//@Component
//@Scope("singleton")
//public class Testing {
//
//
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private CouponExpirationDailyJob couponExpirationDailyJob;
//
//    public Testing() {
//
//
//
//
//    }
//
//
//
//
//
//    public  void testAll()  {
//
////        couponExpirationDailyJob.start();
//
//
//
////
//        Company a = new Company("aaa", "a@a.com", "aaaaa");
//        Company b = new Company("bbb", "b@b.com", "bbbbb");
//
//        Company c = new Company("ccc", "c@c.com", "ccccc");
//        Company d = new Company("ddd", "d@d.com", "ddddd");
//        Company e = new Company("eee", "e@e.com", "eeeee");
//
//        Coupon c1 = new Coupon(c, Category.RESTARUNT, "pizza hut", "50% off muzarella", 200, 89.99, "stringImage");
//        Coupon c2 = new Coupon(c, Category.FOOD, "pizza hut", "20% off salad", 99, 52d, "stringImage");
//        Coupon c3 = new Coupon(c, Category.RESTARUNT, "pizza hut", "buy one get one free ", 50, 40.35d, "stringImage");
//        Coupon a1 = new Coupon(a, Category.VACATION, "Rome", "one week-3 stars hotel", 50, 1499.99d, "stringImage");
//        Coupon a2 = new Coupon(a, Category.VACATION, "Rio de_Janeiro", "one week-5 stars hotel", 15, 3299.99d, "stringImage");
//        Coupon outdated = new Coupon(Category.ELECTRICITY,"to delete","its outdated", new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(-90)),new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(-30)),55L,45.55,"yo mama",a);
//
//        Customer customer_1 = new Customer("Aviad", "Eshel", "aviad@eshel.com", "aviad1234");
//        Customer customer_2 = new Customer("isac", "Nachui", "Isac@gmail.com", "isac1234");
//        Customer customer_3 = new Customer("Rami", "Younes", "rami@gamil.com", "rami1234");
//        Customer customer_4 = new Customer("moshe", "Eshel", "moshe@eshel.com", "moshe1234");
//
//
//        adminService.login("adminService@adminService.com", "adminService");
//
////
////
//////        try {
////
////
////
//
//            System.out.println("----------adminService functions----------------");
//            System.out.println("--------adding companies-------");
//             a=  adminService.addNewCompany(a);
//            c = adminService.addNewCompany(c);
//            d = adminService.addNewCompany(d);
//            System.out.println("------------------------------------------");
//            System.out.println("---------print all companies ---------");
//            System.out.println(adminService.getAllCompanys());
//            System.out.println("------------------------------------------");
//            System.out.println("----------updating company----------");
//            System.out.println(adminService.updateCompany(d, e));
//            System.out.println("------------------------------------------");
//            System.out.println("-------deleting companies----------");
//            System.out.println(adminService.deleteCompany(a));
//             System.out.println("-----------delete non exist company---------------");
//            System.out.println(adminService.deleteCompany(b));
//            System.out.println("------------------------------------------");
//            System.out.println("---------print all companies after CRUD-----------");
//            System.out.println(adminService.getAllCompanys());
//            System.out.println("------------------------------------------");
//            System.out.println("----------print one company------------");
//            System.out.println(adminService.getOneCompany(d.getId()));
//            System.out.println("------------------------------------------");
//            System.out.println("----------print non existent company----------");
//            System.out.println(adminService.getOneCompany(a.getId()));
//            System.out.println("------------------------------------------");
//            System.out.println("-------adding customers-----------");
//            customer_1 = adminService.addCustomer(customer_1);
//            customer_2 = adminService.addCustomer(customer_2);
//            customer_4 = adminService.addCustomer(customer_4);
//            System.out.println("------------------------------------------");
//            System.out.println("-------------print all customers-----------");
//            System.out.println(adminService.getAllCustomers());
//            System.out.println("------------------------------------------");
//            System.out.println("------------update customer------");
//            System.out.println( adminService.updateCustomer(customer_1, customer_3));
//            System.out.println("------------------------------------------");
//            System.out.println("------------delete customer");
//            System.out.println(adminService.deleteCustomer(customer_2));
//            System.out.println("------------------------------------------");
//            System.out.println("-------------print all customers after CRUD-----------");
//            System.out.println(adminService.getAllCustomers());
//            System.out.println("------------------------------------------");
//            System.out.println("------------print one customer---------");
//            System.out.println(adminService.getOneCustomer(customer_1.getId()));
//            System.out.println("------------------------------------------");
//            System.out.println("------------print empty customer---------");
//            System.out.println(adminService.getOneCustomer(customer_2.getId()));
//            System.out.println("------------------------------------------");
//
//            System.out.println("-----------company service functions------");
//               companyService.login("c@c.com", "ccccc");
//
//            System.out.println("------------------------------------------");
//            System.out.println("-----------adding coupons------------");
//            c1=   companyService.addCouponToCompany(c1);
//            c2 = companyService.addCouponToCompany(c2);
//            c3 = companyService.addCouponToCompany(c3);
//            outdated= companyService.addCouponToCompany(outdated);
//            System.out.println("------------------------------------------");
//            System.out.println("--------get all coupons in company--------------");
//            System.out.println(companyService.getAllCouponsInCompany());
//            System.out.println("------------------------------------------");
//            System.out.println("---------update coupon----------------");
//            System.out.println(companyService.updateCouponInCompany(c3, a1));
//            System.out.println("------------------------------------------");
//            System.out.println("----------delete coupon--------------");
//            System.out.println(companyService.deleteCopounFromCompany(c2.getId()));
//            System.out.println("------------------------------------------");
//            System.out.println("---------get all coupons in company after CRUD---------");
//            System.out.println(companyService.getAllCouponsInCompany());
//            System.out.println("------------------------------------------");
//            System.out.println("-------------get all coupons of category-------------");
//            System.out.println(companyService.getAllCompnyCouponsOfOneCategory(Category.VACATION));
//            System.out.println("------------------------------------------");
//            System.out.println("-------------get all coupons by max price-----------");
//            System.out.println(companyService.getAllCouponsByPrice(150d));
//            System.out.println("------------------------------------------");
//            System.out.println("---------company details------------");
//            System.out.println(companyService.getCompanyDetails());
//            System.out.println("------------------------------------------");
//            System.out.println("---------second company------------");
//        companyService.login(d.getEmail(),d.getPassword());
//        System.out.println("--------------------------------");
//        System.out.println("-------------add coupon to company---------");
//        a2= companyService.addCouponToCompany(a2);
//        System.out.println("---------------------------");
//
//
//            System.out.println("----------customer service functions-------------");
//            System.out.println("----------login-----------");
//            customerService.login("rami@gamil.com", "rami1234");
//
//        customerService.getAllCoupons();
//            System.out.println("------------------------------------------");
//            System.out.println("------------------coupon purchases-------------");
//            System.out.println(customerService.couponPurchaseByCustomer(c1));
//            System.out.println(customerService.couponPurchaseByCustomer(c3));
//            System.out.println(customerService.couponPurchaseByCustomer(a2));
//            System.out.println(customerService.couponPurchaseByCustomer(outdated));
//            System.out.println("----------print all coupons----------");
//            System.out.println(customerService.getAllCoupons());
//            System.out.println("------------------------------------------");
//            System.out.println("---------------coupons by category------------");
//            System.out.println(customerService.getAllCouponsByCategory(Category.RESTARUNT));
//            System.out.println("------------------------------------------");
//            System.out.println("-----------max price----------");
//            System.out.println(customerService.getAllCouponsByMaxPrice(1600d));
//            System.out.println("------------------------------------------");
//            System.out.println("--------customer details----------");
//            System.out.println(customerService.getCustomerDetails());
//            System.out.println("------------------------------------------");
//            System.out.println("-------------second customer-------------");
//             customerService.login(customer_4.getEmail(),customer_4.getPassword());
//        System.out.println("---------------coupon purchase-----------");
//             System.out.println(customerService.couponPurchaseByCustomer(c1));
//             System.out.println(customerService.couponPurchaseByCustomer(c3));
//            System.out.println(customerService.couponPurchaseByCustomer(a2));
//        System.out.println("----------------------------------");
//        System.out.println("-------------print coupons-----------");
//           System.out.println(customerService.getAllCoupons());
//        System.out.println("----------------------------");
//        System.out.println("-------------delete coupon purchase-----------");
//        System.out.println(customerService.deleteCouponPurchase(a2.getId()));
//        System.out.println("-------------------------");
//
//        System.out.println("---------------print after CRUD-----------------");
//        System.out.println(customerService.getAllCoupons());
//        System.out.println("-----------------------------");
//        System.out.println("-------daily job test-----------");
//        couponExpirationDailyJob.start();
//        System.out.println("----------------------------------");
//        companyService.login("c@c.com", "ccccc");
//        System.out.println(companyService.getAllCouponsInCompany());
//        System.out.println("------------------------");
//       // System.out.println(   adminService.deleteCompany(c));
////        System.out.println(adminService.deleteCompany(d));
////        customerService.login(customer_1.getEmail(),customer_1.getPassword());
////        customerService.deleteCouponPurchase(c3.getId());
////        System.out.println(adminService.deleteCustomer(customer_1));
////       customerService.login(customer_4.getEmail(),customer_4.getPassword());
////        customerService.login(customer_1.getEmail(),customer_1.getPassword());
////        System.out.println("---------if deleted customer is empty coupons----------");
////        System.out.println(customerService.getAllCoupons());
////        System.out.println("---------------------------");
////        System.out.println("-------all coupons shuld be empty-------");
////       System.out.println(companyService.getAllCouponsInCompany());
////       System.out.println("-----------------------");
////        System.out.println("------all customers should be full");
////        System.out.println(adminService.getAllCustomers());
////       System.out.println("-----------------------");
//
//
//
////        }catch (Exception excep){
////            System.out.println(excep.toString()+"\n" +
////                    "test failed");
////        }finally {
//            couponExpirationDailyJob.stop();
////        }
//    }
//}
