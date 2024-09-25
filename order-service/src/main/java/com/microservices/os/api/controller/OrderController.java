package com.microservices.os.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.os.api.commondto.TransactionRequest;
import com.microservices.os.api.commondto.TransactionResponse;
import com.microservices.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {

        return orderService.saveOrder(transactionRequest);
    }
}
