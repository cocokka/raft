package com.awu.raft.services.regression.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Item {

    private String id;
    private String title;
    private String description;
    private ItemPriority priority;
    private ItemStatusEnum status;
    private ItemType type;
    private List<ItemComment> comments;

    private LocalDateTime createdTime;
    private LocalDateTime lastUpdatedTime;
    private String lastUpdatedBy;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", type=" + type +
                ", comments=" + comments +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
