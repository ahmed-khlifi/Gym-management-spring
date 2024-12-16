package com.gym.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.gym.model.Role;
import com.gym.gym.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByRole(Role role);

}
