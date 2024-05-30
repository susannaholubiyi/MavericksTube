package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.CreateUserRequest;
import com.maverickstube.maverickshub.dtos.response.CreateUserResponse;

public interface UserService {
    CreateUserResponse register(CreateUserRequest createUserRequest);
}
