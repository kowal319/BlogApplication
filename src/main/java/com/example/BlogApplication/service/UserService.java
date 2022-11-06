package com.example.BlogApplication.service;

import com.example.BlogApplication.dto.RegistrationDto;
import com.example.BlogApplication.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
