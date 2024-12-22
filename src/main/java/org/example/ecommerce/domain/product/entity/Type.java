package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ecommerce.contract.abstractions.shared.model.BaseEntity;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_types")
public class Type extends BaseEntity<Integer> implements Serializable {
    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category categoryByCategory;

    @OneToMany(mappedBy = "")
    private Set<Product> products;
}