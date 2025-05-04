package su.kometa.kometabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.dtos.request.MessageCreateDTO;
import su.kometa.kometabackend.exceptions.MessageNotFoundException;
import su.kometa.kometabackend.exceptions.ModelNotFoundException;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.Message;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.repositories.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final ChatService chatService;

    private final GeminiService geminiService;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatService chatService, GeminiService geminiService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
        this.geminiService = geminiService;
    }

    public Message getMessageById(long id) {
        return messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);
    }

    public List<Message> getAllByChat(Chat chat, long before, int limit) {
        if (limit <= 0) limit = 25;
        if (before <= 0) before = System.currentTimeMillis();

        return messageRepository.findAllByChatAndTimestampBeforeOrderByTimestampDesc(chat, before, limit);
    }

    public Message createMessageAndRequestModelResponse(Chat chat, User user, MessageCreateDTO body) {
        chat = chatService.getById(chat.getId());

        String modelProvider = chat.getModel().getProvider();

        Message userMessage = messageRepository.save(new Message(user, null, chat, body.getContent(), null));
        Message modelMessage;

        switch (modelProvider) {
            case "openai":
                return null;
            case "gemini":
                modelMessage = geminiService.sendMessage(chat, body.getContent());
                messageRepository.save(modelMessage);
                break;
            default:
                throw new ModelNotFoundException();
        }

        userMessage.setReplyMessage(modelMessage);
        messageRepository.save(userMessage);

        return userMessage;
    }
}
