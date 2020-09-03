package com.awu.raft.services.regression.domain;

import lombok.Getter;

@Getter
public class ItemKey {

    private String key;

    public ItemKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ItemKey{" +
                "key='" + key + '\'' +
                '}';
    }
}
