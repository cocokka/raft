package com.awu.raft.services.regression.service.impl;

import com.awu.raft.services.regression.domain.Item;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class BlotterCache extends ConcurrentHashMap<String, Item> {

}
