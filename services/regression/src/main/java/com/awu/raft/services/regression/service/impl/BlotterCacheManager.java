package com.awu.raft.services.regression.service.impl;

import com.awu.raft.services.regression.domain.Item;
import com.awu.raft.services.regression.domain.ItemKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Log4j2
public class BlotterCacheManager {

    private final Map<String, Item> blotterCache;

    private final Map<String, ItemKey> keyCache = new ConcurrentHashMap<>();
    private final Map<String, Lock> reentrantLockCache = new ConcurrentHashMap<>();

    private final Lock lock = new ReentrantLock();

    @Autowired
    public BlotterCacheManager(Map<String, Item> blotterCache) {
        this.blotterCache = blotterCache;
    }


    private ItemKey getLock(String key) {
        if (Objects.isNull(keyCache.get(key))) {
            synchronized (keyCache) {
                if (Objects.isNull(keyCache.get(key))) {
                    ItemKey value = new ItemKey(key);
                    log.info("generate new key {}", value);
                    keyCache.put(key, value);
                }
            }
        } else {
            log.info("Got existing key {}", keyCache.get(key));
        }
        return keyCache.get(key);
    }

    private Lock getReentrantLock(String key) {
        synchronized (reentrantLockCache) {
            Lock reentrantLock = reentrantLockCache.get(key);
            if (Objects.isNull(reentrantLock)) {
                reentrantLock = new ReentrantLock();
                log.info("generate new lock {}", lock);
                Assert.notNull(reentrantLockCache);
                Assert.notNull(key);
                Assert.notNull(reentrantLock);
                reentrantLockCache.put(key, reentrantLock);
                reentrantLock = reentrantLockCache.get(key);
            } else {
                log.info("Got existing lock {}", reentrantLock);
            }
            return reentrantLock;
        }
    }


    public void put(Item incoming) throws InterruptedException {
        String id = incoming.getId();
        ItemKey lock = this.getLock(id);
        synchronized (incoming.getId().intern()) {
            TimeUnit.SECONDS.sleep(10);
            log.info("processing key {}", id);
            Item existing = blotterCache.get(id);
            if (Objects.nonNull(existing)) {
                log.info("removing existing one {}", existing);
                blotterCache.remove(existing.getId());
            }
            log.info("putting incoming one {}", incoming);
            blotterCache.put(id, incoming);
        }
    }

    public void putWithLock(Item incoming) throws InterruptedException {
        String id = incoming.getId();
        lock.lock();

        try {
            TimeUnit.SECONDS.sleep(10);
            log.info("processing key {}", id);
            Item existing = blotterCache.get(id);
            if (Objects.nonNull(existing)) {
                log.info("removing existing one {}", existing);
                blotterCache.remove(existing.getId());
            }
            log.info("putting incoming one {}", incoming);
            blotterCache.put(id, incoming);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void putRLock(Item incoming) throws InterruptedException {
        String id = incoming.getId();
        Lock reentrantLock = this.getReentrantLock(id);

        if (reentrantLock.tryLock()) {
            try {
                TimeUnit.SECONDS.sleep(10);
                log.info("processing key {}", id);
                Item existing = blotterCache.get(id);
                if (Objects.nonNull(existing)) {
                    log.info("removing existing one {}", existing);
                    blotterCache.remove(existing.getId());
                }
                log.info("putting incoming one {}", incoming);
                blotterCache.put(id, incoming);
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }
}
