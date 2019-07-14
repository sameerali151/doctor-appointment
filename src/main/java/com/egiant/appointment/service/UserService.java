package com.egiant.appointment.service;

import com.egiant.appointment.dto.UserDto;

import java.util.List;


public interface UserService {
    UserDto getUserById(Integer userId);

    void saveUser(UserDto userDto);

    List<UserDto> getAllUsers();
}
