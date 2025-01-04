package com.agesh.busbuddy.service;

import org.springframework.stereotype.Service;

import com.agesh.busbuddy.dto.UserDto;
import com.agesh.busbuddy.model.User;

@Service
public interface UserService {
    User registerUser(UserDto userDto);
    User findByEmail(String email);
}
