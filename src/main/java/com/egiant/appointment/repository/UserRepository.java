package com.egiant.appointment.repository;

import com.egiant.appointment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer>, CrudRepository<User, Integer> {
}
