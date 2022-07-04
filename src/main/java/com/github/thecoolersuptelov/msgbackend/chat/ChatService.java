package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.User;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    boolean isUserInAChat(ChatDto chatFromRequest) {
        return getChatRepository().findByNameEqualsIgnoreCase(chatFromRequest.getName()).isPresent();
    }

    public ResponseEntity<String> addNewChat(ChatDto chatFromRequest, String userSearchAttribute) {
        Set<User> usersInChat = null;
        if (isUserInAChat(chatFromRequest)) {
            return new ResponseEntity<>("Chat with that name already exist. Please, change name and try again.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (userSearchAttribute == null && userSearchAttribute.isEmpty()) {
            userSearchAttribute = "Id";
        }

        if ("username".equals(userSearchAttribute)) {
            usersInChat = userRepository.findByUsernameIn(chatFromRequest.getUsers());
        } else {
            usersInChat = userRepository.findByIdIn(chatFromRequest.getUsers().stream().map(e -> UUID.fromString(e)).collect(Collectors.toSet()));
        }
        if (chatFromRequest.getUsers().size() != usersInChat.size()) {
            return new ResponseEntity<>(buildErrorDescriptionUsersNotFound(chatFromRequest.getUsers(), usersInChat), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Chat createdChat = new Chat();
        createdChat.setName(chatFromRequest.getName());
        createdChat.setUsers(usersInChat);
        chatRepository.save(createdChat);
        return new ResponseEntity<>(createdChat.getId().toString(), HttpStatus.CREATED);
    }

    public String buildErrorDescriptionUsersNotFound(Set<String> usersFromRequest, Set<User> usersAlreadyExist) {

        String notFoundedUsernames = usersAlreadyExist.stream().filter(e -> !usersFromRequest.contains(e)).map(e -> e.getUsername()).collect(Collectors.joining(","));

        return "Cannot create chat.Users not found: " + notFoundedUsernames;
    }

    public boolean existUserInChat(UUID chatId, UUID userId) {
        return chatRepository.existsByUsers_IdEqualsAndIdEquals(userId, chatId);
    }

    public List<ChatDto> getAllChatsByUser(UUID userUuid, Integer pageNo, Integer pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);
        return chatRepository.findAllChatsByUserSortedByLatestMessage(userUuid, paging).stream().map(ChatDto::new).collect(Collectors.toList());
    }
}
