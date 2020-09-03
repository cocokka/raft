package com.awu.raft.services.regression.strategy.handler;

import com.awu.raft.services.regression.strategy.domain.Order;

public interface OrderHandler {

    void handle(Order order);

}
