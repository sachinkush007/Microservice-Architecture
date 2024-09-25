package com.microservices.p.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.p.api.entity.PaymentEntity;
import com.microservices.p.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
   @Autowired
   PaymentService paymentService;

   @PostMapping("/doPayment")
   public PaymentEntity makePayment(@RequestBody PaymentEntity payment) throws JsonProcessingException {
      return paymentService.makePayment(payment);
   }

   @GetMapping("/{orderId}")
   public Optional<PaymentEntity> getPaymentHistoryById(@PathVariable int orderId){
      return paymentService.findPaymentHistoryById(orderId);
   }
}
