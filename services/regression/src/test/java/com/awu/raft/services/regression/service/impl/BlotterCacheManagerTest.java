package com.awu.raft.services.regression.service.impl;

import com.awu.raft.services.regression.domain.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlotterCacheManagerTest {

    @Autowired
    BlotterCacheManager blotterCacheManager;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void put() {

        List<Item> list = Arrays.asList(build("1"), build("2"), build("3"), build("2"), build("1"));

        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    blotterCacheManager.put(list.get(finalI));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
        }
    }

    private Item build(String id) {
        Item item = new Item();
        item.setId(id);
        return item;
    }
}