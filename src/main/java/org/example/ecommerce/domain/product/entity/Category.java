package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ecommerce.contract.abstractions.shared.model.BaseEntity;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_categories")
public class Category extends BaseEntity<Integer> implements Serializable {
    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @OneToMany(mappedBy = "milkTeaCategoryByMilkTeaType")
    private Set<MilkTeaTypeEntity> milkTeaTypes;
}
