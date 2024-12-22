package org.example.ecommerce.domain.discount.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ecommerce.contract.abstractions.shared.model.BaseAuditEntity;
import org.example.ecommerce.domain.discount.enums.Status;
import org.example.ecommerce.domain.discount.enums.VoucherType;
import org.example.ecommerce.domain.product.entity.Category;
import org.example.ecommerce.domain.product.entity.Product;
import org.example.ecommerce.domain.shop.entity.Shop;
import org.example.ecommerce.domain.users.entities.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_vouchers")
public class Voucher extends BaseAuditEntity<Integer> implements Serializable {
    @Enumerated(EnumType.STRING)
    private Status status;

    private String code;

    private String name;

    private String description;

    private Long discount;

    private Long minPrice;

    private Long maxPrice;

    private Long maxDiscount;

    private Long quantity;

    private Date startDate;

    private Date endDate;

    private Long quantityUsed;

    private VoucherType type;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "voucher_order", joinColumns = @JoinColumn(name = "voucher_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
//    private List<Order> orders;

    // voucher of shop
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "voucher_product",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> specificProducts;

    // New fields for applicability
    private boolean applyToAll = true;
}