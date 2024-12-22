package org.example.ecommerce.domain.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ecommerce.contract.abstractions.shared.model.BaseAuditEntity;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_orders")
public class Order extends BaseAuditEntity<Integer> implements Serializable {
}