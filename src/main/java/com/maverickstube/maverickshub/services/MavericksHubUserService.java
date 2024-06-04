package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.CreateUserRequest;
import com.maverickstube.maverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.maverickshub.exceptions.UserNotFoundException;
import com.maverickstube.maverickshub.models.User;
import com.maverickstube.maverickshub.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MavericksHubUserService implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public MavericksHubUserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper =modelMapper;
    }
    @Override
    public CreateUserResponse register(CreateUserRequest createUserRequest) {
        User user = modelMapper.map(createUserRequest, User.class);
        user = userRepository.save(user);
        var response = modelMapper.map(user, CreateUserResponse.class);
        response.setMessage("User registered successfully");
        return response;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(
                        String.format("user with id %d not found", id)
                ));
    }
}
