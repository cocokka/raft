package com.awu.raft.services.regression.strategy.service.impl;

import com.awu.raft.services.regression.strategy.domain.Order;
import com.awu.raft.services.regression.strategy.handler.OrderHandler;
import com.awu.raft.services.regression.strategy.handler.OrderHandlerType;
import com.awu.raft.services.regression.strategy.handler.impl.OrderHandlerTypeImpl;
import com.awu.raft.services.regression.strategy.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    /*private Map<String, OrderHandler> orderHandlerMap;

    @Autowired
    public void setOrderHandlerMap(List<OrderHandler> orderHandlers) {
        this.orderHandlerMap = orderHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderHandlerType.class).source(), v -> v, (v1, v2) -> v1)
        );
    }


    @Override
    public void createOrder(Order order) {
        OrderHandler orderHandler = orderHandlerMap.get(order.getSource());
        orderHandler.handle(order);
    }*/


    private Map<OrderHandlerType, OrderHandler> orderHandlerMap;

    @Autowired
    public void setOrderHandlerMap(List<OrderHandler> orderHandlers) {
        this.orderHandlerMap = orderHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderHandlerType.class), v -> v, (v1, v2) -> v1)
        );
    }

    @Override
    public void createOrder(Order order) {
        OrderHandlerType orderHandlerType = new OrderHandlerTypeImpl(order.getSource(), order.getPayMethod());
        OrderHandler orderHandler = orderHandlerMap.get(orderHandlerType);
        log.info("OrderHandler-{} is processing order-{}", orderHandler, order);
        orderHandler.handle(order);
    }
}
