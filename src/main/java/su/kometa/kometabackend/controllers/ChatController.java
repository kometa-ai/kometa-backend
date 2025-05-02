package su.kometa.kometabackend.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import su.kometa.kometabackend.constants.AttributesConstants;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.request.ChatCreateDTO;
import su.kometa.kometabackend.dtos.request.ChatEditDTO;
import su.kometa.kometabackend.dtos.response.ChatDTO;
import su.kometa.kometabackend.dtos.response.OkDTO;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.services.ChatService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.CHATS_ROUTE, produces = "application/json")
public class ChatController {

    final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/@me")
    public List<ChatDTO> getAllByUser(@RequestAttribute(name = AttributesConstants.USER) User user) {
        return chatService.getAllByUser(user).stream()
                .map(ChatDTO::new)
                .toList();
    }

    @PostMapping("/")
    public ChatDTO createChat(@RequestAttribute(name = AttributesConstants.USER) User user, @Valid @RequestBody ChatCreateDTO body) {
        return new ChatDTO(chatService.create(body, user));
    }

    @PatchMapping("/{id}")
    public ChatDTO updateChat(@RequestAttribute(name = AttributesConstants.CHAT) Chat chat, @PathVariable("id") long id, @Valid @RequestBody ChatEditDTO body) {
        return new ChatDTO(chatService.edit(chat, body));
    }

    @DeleteMapping("/{id}")
    public OkDTO deleteChat(@RequestAttribute(name = AttributesConstants.USER) User user, @PathVariable("id") long id) {
        chatService.delete(id);
        return new OkDTO();
    }
}
