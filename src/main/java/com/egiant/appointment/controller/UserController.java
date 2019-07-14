package com.egiant.appointment.controller;


import com.egiant.appointment.dto.UserDto;
import com.egiant.appointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUser/{userId}")
    public UserDto getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public void saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }
}
