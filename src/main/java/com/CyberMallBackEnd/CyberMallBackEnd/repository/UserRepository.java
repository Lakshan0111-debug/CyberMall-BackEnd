package com.CyberMallBackEnd.CyberMallBackEnd.repository;


import com.CyberMallBackEnd.CyberMallBackEnd.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    @Transactional
    void deleteByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);

}
