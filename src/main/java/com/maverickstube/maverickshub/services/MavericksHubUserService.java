package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.CreateUserRequest;
import com.maverickstube.maverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.maverickshub.models.User;
import com.maverickstube.maverickshub.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MavericksHubUserService implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public CreateUserResponse register(CreateUserRequest createUserRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(createUserRequest, User.class);
        return null;
    }
}
