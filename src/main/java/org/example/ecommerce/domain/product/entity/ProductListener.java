package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProductListener {
    private final IProductRedisService productRedisService;

    @PrePersist
    public void prePersist(Product productEntity) {
        log.info("PrePersist: {}", productEntity);
    }

    @PostPersist
    public void postPersist(Product productEntity) {
        log.info("PostPersist: {}", productEntity);
        productRedisService.deleteAllProduct();
    }

    @PreUpdate
    public void preUpdate(Product productEntity) {
        log.info("PreUpdate: {}", productEntity);
    }

    @PostUpdate
    public void postUpdate(Product productEntity) {
        log.info("PostUpdate: {}", productEntity);
        productRedisService.deleteAllProduct();
    }

    @PreRemove
    public void preRemove(Product productEntity) {
        log.info("PreRemove: {}", productEntity);
    }

    @PostRemove
    public void postRemove(Product productEntity) {
        log.info("PostRemove: {}", productEntity);
        productRedisService.deleteAllProduct();
    }

}
