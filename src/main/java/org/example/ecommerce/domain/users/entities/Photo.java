package org.example.ecommerce.domain.users.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.abstractions.shared.model.DateTrackingBase;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_photo")
public class Photo extends DateTrackingBase<Integer> implements Serializable {
    @Column(name = "public_id")
    @JsonIgnore
    String publicId;

    @Column(name = "url")
    String url;
}
