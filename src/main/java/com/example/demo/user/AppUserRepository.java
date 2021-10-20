package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.isEnabled= TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.isUnlocked= FALSE WHERE a.email = ?1")
    int lockAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.karma= ?2 WHERE a.email = ?1")
    int updateKarma(String email, int karma);
}
