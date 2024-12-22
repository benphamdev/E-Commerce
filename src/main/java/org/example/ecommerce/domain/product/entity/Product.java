package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ecommerce.contract.abstractions.shared.model.BaseEntity;
import org.example.ecommerce.domain.branch.entity.Branch;
import org.example.ecommerce.domain.cart.entity.Cart;
import org.example.ecommerce.domain.discount.entity.Voucher;
import org.example.ecommerce.domain.order.entity.OrderDetail;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_products")
@EntityListeners(ProductListener.class)
public class Product extends BaseEntity<Integer> implements Serializable {
    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "description", columnDefinition = "varchar(1000)")
    private String description;

    @Column(name = "image", columnDefinition = "varchar(1000)")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type typeByType;

    @OneToMany(mappedBy = "milkTeaByCartDetail")
    private Set<Cart> cartDetails;

    @OneToMany(mappedBy = "milkTeaByOrderDetail")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "milkTeaByBranchMilkTea")
    private Set<Branch> branchMilkTeas;

    @ManyToMany(mappedBy = "specificProducts", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private Set<Voucher> vouchers;

}
