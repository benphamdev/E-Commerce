package org.example.ecommerce.domain.users.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.abstractions.shared.model.DateTrackingBase;
import org.example.ecommerce.domain.authentication.entity.ForgotPassword;
import org.example.ecommerce.domain.authentication.entity.Role;
import org.example.ecommerce.domain.common.constants.Enums;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(
        name = "tbl_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"}),
                @UniqueConstraint(columnNames = {"phone_number"})
        }
)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends DateTrackingBase<Integer> implements Serializable {
    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "dob")
    LocalDate dob; //don't change in sql
    //    Date dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    Enums.Gender gender;

    @Column(name = "email")
    String email;

    @Column(
            name = "phone_number",
            unique = true
    )
    String phoneNumber;

    @Column(
            name = "password",
            nullable = false
    )
    String password;

//    @OneToOne(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    @JsonIgnore
//    Account account;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    ForgotPassword forgotPassword;

    @OneToOne(
            targetEntity = Photo.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "photo_id")
    Photo photo;

//    @OneToOne(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(name = "phone_token_id")
//    @JsonIgnore
//    PhoneToken phoneToken;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;

//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private Set<Address> addresses = new HashSet<>();
//
//    public void saveAddress(Address address) {
//        if (address != null) {
//            if (addresses == null) {
//                addresses = new HashSet<>();
//            }
//            addresses.add(address);
//            address.setUser(this);
//        }
//    }
//
//    @JsonIgnore // Stop infinite loop
//    public Set<Address> getAddresses() {
//        return addresses;
//    }
}
