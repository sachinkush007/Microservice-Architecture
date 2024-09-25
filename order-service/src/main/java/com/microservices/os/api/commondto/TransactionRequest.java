package com.microservices.os.api.commondto;

import com.microservices.os.api.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private OrderEntity order;
    private Payment payment;
}
