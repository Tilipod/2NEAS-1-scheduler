package ru.tilipod.jpa.entity.security.enums;

import lombok.Getter;

@Getter
public enum VipLevel {
    NONE(10L),
    BASIC(100L),
    GOLD(500L),
    PLATINUM(1000L);

    private Long downloadBatchSize;

    VipLevel(Long downloadBatchSize) {
        this.downloadBatchSize = downloadBatchSize;
    }
}
