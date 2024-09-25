package com.microservices.os.api.commondto;

import com.microservices.os.api.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private OrderEntity order;
    private String tansactionId;
    private double amount;
    private String message;

}
