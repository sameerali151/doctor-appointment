package com.egiant.appointment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String dob;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Appointment> appointments;
}
