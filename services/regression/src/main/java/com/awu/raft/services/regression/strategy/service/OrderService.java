package com.awu.raft.services.regression.strategy.service;

import com.awu.raft.services.regression.strategy.domain.Order;

public interface OrderService {

    void createOrder(Order order);
}
