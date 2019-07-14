package com.egiant.appointment.converter;

import com.egiant.appointment.dto.AppointmentDto;
import com.egiant.appointment.entity.Appointment;


public class AppointmentConverter {
    public static Appointment dtoToEntity(AppointmentDto appointmentDto) {
        return Appointment.builder()
                .appointmentId(appointmentDto.getAppointmentId())
                .appointmentDateTime(appointmentDto.getAppointmentDttm())
                .build();
    }

    public static AppointmentDto entityToDto(Appointment appointment) {
        return AppointmentDto.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentDttm(appointment.getAppointmentDateTime())
                .build();
    }
}
