package com.egiant.appointment.converter;

import com.egiant.appointment.dto.AppointmentDto;
import com.egiant.appointment.dto.UserDto;
import com.egiant.appointment.entity.Appointment;
import com.egiant.appointment.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static User dtoToEntity(UserDto userDto) {

        Appointment appointment = Appointment.builder()
                .appointmentDateTime(userDto.getAppointmentDttm())
                .build();

        List<Appointment> list = new ArrayList<>();
        list.add(appointment);

        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .dob(userDto.getDob())
                .appointments(list)
                .build();
    }

    public static UserDto entityToDto(User user) {
        List<AppointmentDto> appointmentList = user.getAppointments().stream().map(AppointmentConverter::entityToDto).collect(Collectors.toList());
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dob(user.getDob())
                .appointmentDttm(appointmentList.get(0).getAppointmentDttm())
                .appointments(appointmentList)
                .build();
    }
}
