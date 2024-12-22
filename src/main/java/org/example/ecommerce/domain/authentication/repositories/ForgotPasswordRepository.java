package org.example.ecommerce.domain.authentication.repositories;

import jakarta.transaction.Transactional;
import org.example.ecommerce.domain.authentication.entity.ForgotPassword;
import org.example.ecommerce.domain.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
    @Query("SELECT f FROM ForgotPassword f WHERE f.otp = :otp AND f.user = :user")
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);

    @Query("DELETE FROM ForgotPassword f WHERE f.id = :id")
    @Transactional
    @Modifying
    void deleteById(Integer id);
}
