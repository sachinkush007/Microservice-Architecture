package com.microservice.cloud_gateway.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallback")
    public String orderServiceFallback() {
        return "Order Service is currently unavailable. Please try again later.";
    }

    @RequestMapping("/paymentFallback")
    public String paymentServiceFallback() {
        return "Payment Service is currently unavailable. Please try again later.";
    }
}
