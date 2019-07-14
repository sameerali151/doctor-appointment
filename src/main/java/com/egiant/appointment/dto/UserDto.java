package com.egiant.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String dob;
    private String appointmentDttm;
    private List<AppointmentDto> appointments;
}
