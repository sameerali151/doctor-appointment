package com.egiant.appointment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer appointmentId;

    @Column
    private String appointmentDateTime;

    @ManyToOne
    private User user;
}
