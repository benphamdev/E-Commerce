package org.example.ecommerce.domain.users.services;

import org.example.ecommerce.contract.abstractions.shared.response.PageResponse;
import org.example.ecommerce.domain.users.dto.requests.UserCreationRequest;
import org.example.ecommerce.domain.users.dto.requests.UserUpdateRequest;
import org.example.ecommerce.domain.users.dto.responses.UserResponse;
import org.example.ecommerce.domain.users.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    long createUser(UserCreationRequest userRequest);

    User getUserByEmail(String email);

    User getUserByAccountNumber(String accountNumber);

    UserResponse getUserResponseByID(Integer id);

    UserResponse getMyProfile();

    void updateUser(Integer id, UserUpdateRequest userUpdateRequest);

    UserResponse updateProfile(int userId, MultipartFile profilePicture) throws IOException;

    void updatePhoneToken(Integer userId, String phoneToken);

    void deleteUser(Integer id);

    List<User> getAllUsersWithBirthdayToday();

    PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize);

    PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy);

    PageResponse<?> getAllUsersWithSortByMultiColumns(
            int pageNo, int pageSize, String... sorts
    );

    PageResponse<?> getAllUsersWithPagingAndSorting(
            int pageNo, int pageSize, String search, String sortBy
    );

    PageResponse<?> advancedSearchWithCriteria(
            int pageNo, int pageSize, String sortBy, String address, String... search
    );

    PageResponse<?> advancedSearchWithSpecification(
            Pageable pageable, String[] users, String[] search
    );
}
