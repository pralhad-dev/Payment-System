package com.paymentflow.payment.repository;


import com.paymentflow.payment.entity.Transaction;
import com.paymentflow.payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderOrReceiverOrderByCreatedAtDesc(User sender, User receiver);


}