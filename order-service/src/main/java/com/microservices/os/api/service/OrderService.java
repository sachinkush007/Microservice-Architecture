package com.microservices.os.api.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.os.api.commondto.Payment;
import com.microservices.os.api.commondto.TransactionRequest;
import com.microservices.os.api.commondto.TransactionResponse;
import com.microservices.os.api.entity.OrderEntity;
import com.microservices.os.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    @Value("${microservices.payment-service.endpoints.uri}")
    String PAYMENT_ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        String message = "";
        OrderEntity order = request.getOrder();
        Payment payment = request.getPayment();

        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        log.info("Order Service Request: {}", new ObjectMapper().writeValueAsString(request));

        //Rest call
        Payment paymentResponse = restTemplate.postForObject(PAYMENT_ENDPOINT_URL, payment, Payment.class);
        log.info("Order Service Response: {}", new ObjectMapper().writeValueAsString(paymentResponse));

        message = paymentResponse.getPaymentStatus().equals("Success") ? "Payment Successfull" : "There is problem with your payment! please try again";
        orderRepository.save(order);
        long endTime = System.currentTimeMillis();
        log.info("Execution time:", (endTime - startTime));
        return new TransactionResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(), message);
    }
}
