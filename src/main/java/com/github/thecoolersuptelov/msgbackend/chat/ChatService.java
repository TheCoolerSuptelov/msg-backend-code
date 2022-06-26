package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.User;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private final UserService userService;
    @Autowired
    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    boolean isUserInAChat(ChatDto chatFromRequest) {
        return getChatRepository()
                .findByNameEqualsIgnoreCase(
                        chatFromRequest.getName()
                )
                .isPresent();
    }
    public String addNewChat(ChatDto chatFromRequest, String userSearchAttribute) {
        Set<User> usersInChat = null;
        // TODO
        // use Strategy pattern
        // move to userService
        if (userSearchAttribute == "username") {
            usersInChat = userService.getUserRepository().findByUsernameIn(chatFromRequest.getUsers());
        } else {
            usersInChat = userService.getUserRepository().findByIdIn(
                    chatFromRequest
                            .getUsers()
                            .stream()
                            .map(e -> UUID.fromString(e))
                            .collect(Collectors.toSet())
            );
        }
        if (chatFromRequest.getUsers().size() != usersInChat.size()) {
            return buildErrorDescriptionUsersNotFound(chatFromRequest.getUsers(), usersInChat);
        }

        Chat createdChat = new Chat();
        createdChat.setName(chatFromRequest.getName());
        createdChat.setUsers(usersInChat);
        chatRepository.save(createdChat);
        return createdChat.getId().toString();
    }

    public String buildErrorDescriptionUsersNotFound(Set<String> usersFromRequest, Set<User> usersAlreadyExist) {

        String notFoundedUsernames = usersAlreadyExist.stream()
                .filter(e -> !usersFromRequest.contains(e))
                .map(e -> e.getUsername())
                .collect(Collectors.joining(","));

        return "Cannot create chat.Users not found: " + notFoundedUsernames;
    }

    public boolean existUserInChat(UUID chatId, UUID userId) {
        return chatRepository.existsByUsers_IdEqualsAndIdEquals(userId, chatId);
    }

    public List<ChatDto> getAllChatsByUser(UUID userUuid, Integer pageNo, Integer pageSize) {
        var paging  = PageRequest.of(pageNo, pageSize);
        return chatRepository.findAllChatsByUserSortedByLatestMessage(userUuid,paging)
                .stream()
                .map(ChatDto::new)
                .collect(Collectors.toList());
    }
}
