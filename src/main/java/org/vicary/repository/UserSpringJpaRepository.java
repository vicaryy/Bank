package org.vicary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vicary.model.User;

import java.util.List;

public interface UserSpringJpaRepository extends JpaRepository<User, Long> {
//    @Query("FROM User WHERE email = :email")
//    User findByEmail(@Param("email") String email);

    User findByEmail(String email);

    List<User> findByName(String name);

    Page<User> findByName(String name, Pageable pageable);          // Pageable - paginacja,
}

