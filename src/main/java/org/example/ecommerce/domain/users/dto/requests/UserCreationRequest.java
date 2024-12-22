package org.example.ecommerce.domain.users.dto.requests;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.domain.common.constants.Enums;
import org.example.ecommerce.domain.users.dto.validators.DobConstraint;
import org.example.ecommerce.domain.users.dto.validators.EnumValue;
import org.example.ecommerce.domain.users.dto.validators.PhoneNumberConstraint;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest implements Serializable {
    @NotBlank(message = "FIRST_NAME_REQUIRED") final
    String firstName;

    @NotNull(message = "LAST_NAME_REQUIRED") final
    String lastName;

    @Email(message = "INVALID_EMAIL") final String email;
    @PhoneNumberConstraint(
            min = 10,
            message = "INVALID_PHONE_NUMBER"
    ) final String phoneNumber;

    @DobConstraint(
            message = "INVALID_DOB",
            min = 18
    )
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern = "MM/dd/yyyy")
    LocalDate dob;
    @NotNull(message = "gender is required")
    @EnumValue(name = "gender", enumClass = Enums.Gender.class)
    String gender;
    @Size(
            min = 6,
            message = "PASSWORD_INVALID"
    )
    String password;

    @NotEmpty(message = "ADDRESS_REQUIRED")
    Set<AddressRequest> addresses;

    public UserCreationRequest(
            String firstName, String lastName, String email, String phoneNumber
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
