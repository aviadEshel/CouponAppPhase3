//package com.johnBryce.couponAppPhase2.program.dailyJob.login;
//
//import com.johnBryce.couponAppPhase2.exceptions.DBExceptions;
//import com.johnBryce.couponAppPhase2.exceptions.FacadeException;
//import com.johnBryce.couponAppPhase2.repositories.services.AdminService;
//import com.johnBryce.couponAppPhase2.repositories.services.ClientService;
//import com.johnBryce.couponAppPhase2.repositories.services.CompanyService;
//import com.johnBryce.couponAppPhase2.repositories.services.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.sql.SQLException;
//@Component
//@Scope("singleton")
//public class LoginManager {
//@Autowired
//   private AdminService a;
//@Autowired
//private CustomerService cf;
//@Autowired
//private  CompanyService c;
//
//        public enum ClientType {administrator, company, customer}
//
//        public ClientService login(String email, String password, ClientType ct) throws SQLException, FacadeException, DBExceptions {
//
//            switch (ct) {
//                case administrator:
//
//                    if (a.login(email, password))
//                        return a;
//                    else
//                        return null;
//                case company:
//
//                    if(c.login(email, password)) {
//
//                        return c;
//                    }
//                    else
//                        return null;
//
//
//                case customer:
//
//                    if(cf.login(email, password)) {
//                        return cf;
//                    }
//                    else
//                        return null;
//                default:
//                    return null;
//
//            }
//        }
//    }
//
//
