package com.github.thecoolersuptelov.msgbackend.chatUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameEqualsIgnoreCase(@NonNull String username);

    Set<User> findByIdIn(Collection<UUID> ids);
//    @Query("Select u from userapp as u where u.username in (:#{users.username})")
//    List<User> findUsersByNamesInList(@Param("users") Set<UserDto> UsersDtos);

    Set<User> findByUsernameIn(Collection<String> usernames);

    @Override
    Optional<User> findById(UUID uuid);
}