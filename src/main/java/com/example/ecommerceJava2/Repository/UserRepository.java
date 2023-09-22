package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsUserByUsername(String username);

    @Query("select u from User u where u.email =:email")
    public User loadUserByUserName(@Param("email") String email);

    public User findByVerificationCode(String code);

}
