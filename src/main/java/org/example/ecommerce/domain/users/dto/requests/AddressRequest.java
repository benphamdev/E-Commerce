package org.example.ecommerce.domain.users.dto.requests;

import lombok.Getter;

@Getter
public class AddressRequest {
    private String apartmentNumber;
    private String floor;
    private String building;
    private String streetNumber;
    private String street;
    private String city;
    private String country;
    private Integer addressType;
}
