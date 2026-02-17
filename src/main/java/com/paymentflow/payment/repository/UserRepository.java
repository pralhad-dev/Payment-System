package com.paymentflow.payment.repository;


import com.paymentflow.payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.email FROM User u WHERE u.email = :email")
    User findByEmailId(@Param("email") String email);


    boolean existsByEmail(String email);


    boolean existsByPhone(String phone);
}