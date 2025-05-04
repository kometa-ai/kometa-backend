package su.kometa.kometabackend.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import su.kometa.kometabackend.constants.AttributesConstants;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.request.ChatCreateDTO;
import su.kometa.kometabackend.dtos.request.ChatEditDTO;
import su.kometa.kometabackend.dtos.request.MessageCreateDTO;
import su.kometa.kometabackend.dtos.request.MessageEditDTO;
import su.kometa.kometabackend.dtos.response.ChatDTO;
import su.kometa.kometabackend.dtos.response.MessageDTO;
import su.kometa.kometabackend.dtos.response.OkDTO;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.services.ChatService;
import su.kometa.kometabackend.services.MessageService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.CHATS_ROUTE, produces = "application/json")
public class ChatController {

    final ChatService chatService;
    private final MessageService messageService;

    public ChatController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @GetMapping("/@me")
    public List<ChatDTO> getAllByUser(@RequestAttribute(name = AttributesConstants.USER) User user) {
        return chatService.getAllByUser(user).stream()
                .map(ChatDTO::new)
                .toList();
    }

    @PostMapping("/")
    public ChatDTO create(@RequestAttribute(name = AttributesConstants.USER) User user, @Valid @RequestBody ChatCreateDTO body) {
        return new ChatDTO(chatService.create(body, user));
    }

    @PatchMapping("/{id}")
    public ChatDTO update(@RequestAttribute(name = AttributesConstants.CHAT) Chat chat, @PathVariable("id") long id, @Valid @RequestBody ChatEditDTO body) {
        return new ChatDTO(chatService.edit(chat, body));
    }

    @DeleteMapping("/{id}")
    public OkDTO delete(@RequestAttribute(name = AttributesConstants.USER) User user, @PathVariable("id") long id) {
        chatService.delete(id);
        return new OkDTO();
    }

    @GetMapping("/{id}/messages")
    public List<MessageDTO> getAllMessages(@RequestAttribute(name = AttributesConstants.CHAT) Chat chat,
       @RequestParam(defaultValue = "0") long before,
       @RequestParam(defaultValue = "25") int limit
    ) {
        return messageService.getAllByChat(chat, before, limit).stream()
                .map(MessageDTO::new)
                .toList();
    }

    @PostMapping("/{id}/messages")
    public MessageDTO createMessage(@RequestAttribute(name = AttributesConstants.CHAT) Chat chat,
        @RequestAttribute(name = AttributesConstants.USER) User user,
        @Valid @RequestBody MessageCreateDTO body
    ) {
        return new MessageDTO(messageService.createMessageAndRequestModelResponse(chat, user, body));
    }

    @GetMapping("/{id}/messages/{messageId}")
    public MessageDTO getMessage(@RequestAttribute(name = AttributesConstants.CHAT) Chat chat,
        @PathVariable("id") long id,
        @PathVariable("messageId") long messageId
    ) {
        return new MessageDTO(messageService.getMessageById(messageId));
    }

    @PatchMapping("/{id}/messages/{messageId}")
    public MessageDTO updateMessage(@RequestAttribute(name = AttributesConstants.CHAT) Chat chat,
        @RequestAttribute(name = AttributesConstants.USER) User user,
        @PathVariable("messageId") long messageId,
        @Valid @RequestBody MessageEditDTO body
    ) {
        return new MessageDTO(messageService.updateMessageAndRequestModelResponse(chat, user, body, messageId));
    }
}
