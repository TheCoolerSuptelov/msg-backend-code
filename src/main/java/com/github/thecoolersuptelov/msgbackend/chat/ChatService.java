package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.User;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserRepository;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserSearchStrategy;
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
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    boolean isUserInAChat(ChatDto chat) {
        return chatRepository.findByNameEqualsIgnoreCase(chat.getName()).isPresent();
    }

    public ResponseEntity<String> addNewChat(ChatDto chat, UserSearchStrategy userSearchAttribute) throws ChatCreationException {
        if (isUserInAChat(chat)) {
            throw new ChatCreationException("Chat with that name already exist. Please, change name and try again.");
        }
        if (userSearchAttribute == null) {
            userSearchAttribute = UserSearchStrategy.BY_ID;
        }

        Set<User> usersInChat = null;
        if (UserSearchStrategy.BY_USERNAME == userSearchAttribute) {
            usersInChat = userRepository.findByUsernameIn(chat.getUsers());
        } else {
            usersInChat = userRepository.findByIdIn(chat.getUsers().stream().map(UUID::fromString).collect(Collectors.toSet()));
        }
        if (chat.getUsers().size() != usersInChat.size()) {
            throw new ChatCreationException(buildErrorDescriptionUsersNotFound(chat.getUsers(), usersInChat));
        }

        Chat createdChat = new Chat();
        createdChat.setName(chat.getName());
        createdChat.setUsers(usersInChat);
        chatRepository.save(createdChat);
        return new ResponseEntity<>(createdChat.getId().toString(), HttpStatus.CREATED);
    }

    public String buildErrorDescriptionUsersNotFound(Set<String> users, Set<User> usersAlreadyExist) {
        // awful. refactor.
        return "Cannot create chat.Users not found: " + users.stream().filter(e -> !usersAlreadyExist.stream().map(a -> a.getId().toString()).toList().contains(e)).collect(Collectors.joining(","));
    }

    public boolean existUserInChat(UUID chatId, UUID userId) {
        return chatRepository.existsByUsers_IdEqualsAndIdEquals(userId, chatId);
    }

    public List<ChatDto> getAllChatsByUser(UUID userUuid, Integer pageNo, Integer pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);
        return chatRepository.findAllChatsByUserSortedByLatestMessage(userUuid, paging).stream().map(ChatDto::new).toList();
    }
}
