package com.example.lfd1back.repository;

import com.example.lfd1back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    boolean existsByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :newPass where u.id = :id")
    void changePassword(String newPass, Long id);
}
