package org.vicary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vicary.model.User;

public interface UserSpringJpaRepository extends JpaRepository<User, Long> {
    @Query("FROM User WHERE email = :email")
    User findByEmail(@Param("email") String email);
}

