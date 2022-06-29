package com.johnBryce.couponAppPhase2.repositories;

import com.johnBryce.couponAppPhase2.entities.Customer;
import com.johnBryce.couponAppPhase2.exceptions.DBExceptions;
import com.johnBryce.couponAppPhase2.exceptions.DaoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>{

        Customer findByEmailAndPassword(String email, String password)throws SQLException, DaoException, DBExceptions; ;
        Customer findById(long id) throws SQLException, DaoException, DBExceptions;



//
//        @Modifying
//        @Query(value = "update sql11454997.customers u set u.first_name = ?" +
//                " , u.last_name = ?, u.email =  ? ," +
//                " u.password = ?  where u.id = ?", nativeQuery = true)
//        void updateCustomerNative(String firstName,String lastName,String email, String password, long id);

        }
