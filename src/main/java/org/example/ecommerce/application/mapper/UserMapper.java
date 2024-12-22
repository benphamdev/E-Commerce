package org.example.ecommerce.application.mapper;

import org.example.ecommerce.domain.users.dto.requests.UserCreationRequest;
import org.example.ecommerce.domain.users.dto.requests.UserUpdateRequest;
import org.example.ecommerce.domain.users.dto.responses.UserResponse;
import org.example.ecommerce.domain.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreationRequest request);

    @Mapping(
            source = "photo.url",
            target = "profilePicture"
    )
    UserResponse toResponse(User user);

    @Mapping(
            target = "roles",
            ignore = true
    )
    void updateEntity(
            @MappingTarget User user, UserUpdateRequest request
    );

}
