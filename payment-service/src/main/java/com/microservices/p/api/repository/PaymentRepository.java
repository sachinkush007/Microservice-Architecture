package com.microservices.p.api.repository;

import com.microservices.p.api.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {
     PaymentEntity findByOrderId(int orderId);
}
