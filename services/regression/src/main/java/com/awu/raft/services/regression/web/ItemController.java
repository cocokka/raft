package com.awu.raft.services.regression.web;

import com.awu.raft.services.regression.domain.Item;
import com.awu.raft.services.regression.service.impl.BlotterCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private BlotterCacheManager blotterCacheManager;

    private CountDownLatch countDownLatch = new CountDownLatch(5);

    @GetMapping("/test")
    public ResponseEntity<String> put() throws InterruptedException {

        List<Item> list = Arrays.asList(build("1"), build("2"), build("3"), build("2"), build("1"));

        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    blotterCacheManager.put(list.get(finalI));
//                    blotterCacheManager.putWithLock(list.get(finalI));
//                    blotterCacheManager.putRLock(list.get(finalI));
//                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
        }
//        countDownLatch.await();
        return ResponseEntity.ok("ok");
    }

    private Item build(String id) {
        Item item = new Item();
        item.setId(id);
        return item;
    }


}
