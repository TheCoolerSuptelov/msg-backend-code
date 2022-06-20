package com.github.thecoolersuptelov.msgbackend.chatUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameEqualsIgnoreCase(@NonNull String username);

}