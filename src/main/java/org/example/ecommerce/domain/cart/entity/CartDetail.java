package org.example.ecommerce.domain.cart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_cart_details")
public class CartDetail implements Serializable {
    @EmbeddedId
    private CartDetailId idCartDetail;

    @ManyToOne
    @JoinColumn(name = "id_cart", insertable = false, updatable = false)
    private CartEntity cartByCartDetail;

    @ManyToOne
    @JoinColumn(name = "id_milk_tea", insertable = false, updatable = false)
    private MilkTeaEntity milkTeaByCartDetail;
}
