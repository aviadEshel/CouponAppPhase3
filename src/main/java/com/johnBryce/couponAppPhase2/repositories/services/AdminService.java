package com.johnBryce.couponAppPhase2.repositories.services;

import com.johnBryce.couponAppPhase2.entities.Company;
import com.johnBryce.couponAppPhase2.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService extends ClientService {
    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";
    private static final String ROLE = "admin";

    public AdminService() {
        super();

    }


    @Override
    public boolean login(String email, String password,String role) {
        if ((role.equals(ROLE))&&(email.equals(EMAIL)) && (password.equals(PASSWORD))) {
            return true;
        }

            return false;
    }
    // checked and is good
    public Company addNewCompany(Company company)  {

        try {
            if (companyRepository.findByEmailAndPassword(company.getEmail(),company.getPassword())==null) {
                company = companyRepository.save(company);
                return company;
            } else return null;

        }catch (Exception e){
            System.out.println(e.toString()+"\n" +
                    "admin: adding company failed");
        }
        return null;
    }

    // chekced and vrified
    public Company updateCompany(Company  oldCompany, Company newCompany) {
        Company temp= new Company();
        oldCompany.setEmail(newCompany.getEmail());
        oldCompany.setPassword(newCompany.getPassword());
        try {
             temp=companyRepository.save(oldCompany);

        }catch(Exception e){
            System.out.println(e.toString()+"\n" +
                    "admin:update company failed");
        }
        return temp ;

    }

    // checked and is good
    public boolean deleteCompany(long companyID)  {
        boolean flag = false;
try{
//    if (company.getCoupons()!=null) {
//            for(Coupon coupon: company.getCoupons()){
//                company.getCoupons().remove(coupon);
//                couponRepository.save(coupon);
//                couponRepository.deleteById(coupon.getId());
//                for(Customer customer: coupon.getCustomerPurchase()){
//                    customer.getPurchasedCoupons().remove(coupon);
//                    customerRepository.save(customer);
////
//                }
//
//            }
////         companyRepository.save(company);
//    }

        companyRepository.deleteById(companyID);
        flag =true;

        }catch (Exception e){
            System.out.println(e.toString()+"\n" +
                    "delete company service error");
        }
        return flag;
    }


    public List<Company> getAllCompanys()  {
        try {
            return companyRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "admin: get All Companies failed");
        }
        return null;
    }


    public Company getOneCompany(long companyID)  {

        try {
            return companyRepository.findById(companyID);
        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "admin:get One Company failed");
        }
        return null;
    }



    public Customer addCustomer(Customer customer)  {


            try {
                if (customerRepository.findByEmailAndPassword(customer.getEmail(),customer.getPassword())==null) {
                    customer = customerRepository.save(customer);
                    return customer;
                } else return null;

            } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "admin: adding Customer failed");
        }
        return null;
    }

    // checked and is good
    public Customer updateCustomer(Customer oldCustomer,Customer newCustomer)  {
        Customer temp = new Customer();
        oldCustomer.setFirstName(newCustomer.getFirstName());
        oldCustomer.setLastName(newCustomer.getLastName());
        oldCustomer.setEmail(newCustomer.getEmail());
        oldCustomer.setPassword(newCustomer.getPassword());
        try {
             temp =customerRepository.save(oldCustomer);



        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "admin: updatting Customer failed");
        }

        return temp;
    }


    public boolean deleteCustomer(long customerID) {
        boolean flag = false;
        try {
//if (!customer.getPurchasedCoupons().isEmpty()) {
//
//    for (Coupon coupon : customer.getPurchasedCoupons()) {
//        coupon.getCustomerPurchase().remove(customer);
//        customer.getPurchasedCoupons().remove(coupon);
//        couponRepository.save(coupon);
//    }
//    customerRepository.save(customer);
//    customerRepository.deleteById(customer.getId());
//}else {
    customerRepository.deleteById(customerID);
//}

            flag = true;

        } catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "admin: delete Customer failed");
        }
        return flag;
    }

    // checked and verified
    public List<Customer> getAllCustomers()  {

        try {
            return customerRepository.findAll();
        }
        catch (Exception e) {
            System.out.println(e.toString()+"\n" +
                    "admin: get all Customers failed");
        }
        return null;
    }

    // checked and is good
    public Customer getOneCustomer(long customerID)  {

        try {
            return customerRepository.findById(customerID);
        }catch(Exception e) {
            System.out.println(e.toString()+"\n" +
                    "admin: get one customer failed");
        }
        return null;
    }
}
