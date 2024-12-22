package org.example.ecommerce.contract.abstractions.shared.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseAuditEntity<T extends Serializable> extends BaseEntity<T> implements Serializable {
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Column(name = "is_deleted")
    @JsonIgnore
    @Builder.Default
    private Boolean isDeleted = false;
}