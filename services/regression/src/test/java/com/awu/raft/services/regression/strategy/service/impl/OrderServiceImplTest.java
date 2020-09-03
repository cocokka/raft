package com.awu.raft.services.regression.strategy.service.impl;

import com.awu.raft.services.regression.strategy.domain.Order;
import com.awu.raft.services.regression.strategy.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createOrder() {
        Order order = new Order();
        order.setSource("pc");
        order.setPayMethod("ali");

        orderService.createOrder(order);
    }
}