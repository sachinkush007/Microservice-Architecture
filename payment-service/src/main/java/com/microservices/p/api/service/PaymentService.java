package com.microservices.p.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.p.api.entity.PaymentEntity;
import com.microservices.p.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    PaymentRepository paymentRepository;

    public PaymentEntity makePayment(PaymentEntity payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("Payment Service Request",new ObjectMapper().writeValueAsString(payment));

        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        //API should be third party payment(like Paytm, Phonepay, Paypal.... etc)
        return new Random().nextBoolean()?"Success":"Failed";
    }

    public Optional<PaymentEntity> findPaymentHistoryById(int id){
        PaymentEntity payment=paymentRepository.findByOrderId(id);
        return Optional.ofNullable(payment);
    }
}
