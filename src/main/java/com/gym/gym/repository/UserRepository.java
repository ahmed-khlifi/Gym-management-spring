package com.gym.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.gym.model.ModelAbonnementEnum;
import com.gym.gym.model.Role;
import com.gym.gym.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByRole(Role role);

    // Method to fetch users based on role and optionally filter by membership type
    @Query("SELECT u FROM User u " +
            "JOIN u.abonnement a " +
            "JOIN a.modelAbonnement ma " +
            "WHERE u.role = 'USER' " +
            "AND (:type IS NULL OR ma.type = :type)" +
            "AND (:name IS NULL OR LOWER(u.nom) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<User> getUsersByMembershipTypeAndName(@Param("type") ModelAbonnementEnum type, @Param("name") String name);

}
