package com.paymentflow.payment.repository;


import com.paymentflow.payment.entity.Transaction;
import com.paymentflow.payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderOrReceiverOrderByCreatedAtDesc(User sender, User receiver);

    @Query("SELECT t FROM Transaction t WHERE t.sender.id = :userId OR t.receiver.id = :userId")
    List<Transaction> findTransactionsByUserId(@Param("userId") Long userId);



}