package com.johnBryce.couponAppPhase2.repositories;

import com.johnBryce.couponAppPhase2.entities.Company;
import com.johnBryce.couponAppPhase2.exceptions.DBExceptions;
import com.johnBryce.couponAppPhase2.exceptions.DaoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long>  {


    Company findByEmailAndPassword(String email, String password)throws SQLException, DaoException, DBExceptions;
    Company findById(long id)throws SQLException, DaoException, DBExceptions;
void deleteById(long id);


//    @Modifying
//    @Query(value = "update sql11454997.companies u  set " +
//            " u.email = :email ," +
//            " u.password = :password where u.id = :id", nativeQuery = true)
//    void updateCompanyNative( @Param ("email") String email,
//                       @Param("password")String password,@Param("id") long id);



}
