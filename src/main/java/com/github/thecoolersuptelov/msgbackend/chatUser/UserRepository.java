package com.github.thecoolersuptelov.msgbackend.chatUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.*;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameEqualsIgnoreCase(@NonNull String username);
//    @Query("Select u from userapp as u where u.username in (:#{users.username})")
//    List<User> findUsersByNamesInList(@Param("users") Set<UserDto> UsersDtos);

    Set<User> findByUsernameIn(Collection<String> usernames);

}