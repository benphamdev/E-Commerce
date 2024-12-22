package org.example.ecommerce.domain.authentication.services;

import org.example.ecommerce.domain.authentication.dto.requests.ChangePasswordRequest;

public interface IForgotPassword {
    void verifyEmail(String email);

    boolean verifyOTP(String email, Integer otp);

    boolean changePassword(ChangePasswordRequest changePasswordRequest, String email);
}
