package com.awu.raft.services.regression.strategy.handler.impl;

import com.awu.raft.services.regression.strategy.domain.Order;
import com.awu.raft.services.regression.strategy.handler.OrderHandler;
import com.awu.raft.services.regression.strategy.handler.OrderHandlerType;
import lombok.extern.log4j.Log4j2;

@OrderHandlerType(source = "pc", payMethod = "ali")
@Log4j2
public class PCOrderHandler implements OrderHandler {

    @Override
    public void handle(Order order) {
          log.info("处理PC端订单-{}", order);
    }
}
