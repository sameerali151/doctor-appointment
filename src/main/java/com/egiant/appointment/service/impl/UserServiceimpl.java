package com.egiant.appointment.service.impl;

import static com.egiant.appointment.converter.UserConverter.entityToDto;
import static com.egiant.appointment.converter.UserConverter.dtoToEntity;

import com.egiant.appointment.converter.UserConverter;
import com.egiant.appointment.dto.UserDto;
import com.egiant.appointment.entity.User;
import com.egiant.appointment.exception.UserRuntimeException;
import com.egiant.appointment.repository.UserRepository;
import com.egiant.appointment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUserById(Integer userId) {
        try {
            User user = userRepository.getOne(userId);
            if (user == null || user.getUserId() == null) {
                throw new UserRuntimeException("EntityNotFoundException");
            }

            return entityToDto(user);
        } catch (UserRuntimeException e) {
            log.error(e.getMessage(), e);
        }
        return UserDto.builder().build();
    }

    @Override
    public void saveUser(UserDto userDto) {
        try {
            User user = dtoToEntity(userDto);
            userRepository.save(user);
        } catch (UserRuntimeException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserConverter::entityToDto).collect(Collectors.toList());
    }
}
