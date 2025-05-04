package su.kometa.kometabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.kometa.kometabackend.dtos.request.MessageCreateDTO;
import su.kometa.kometabackend.dtos.request.MessageEditDTO;
import su.kometa.kometabackend.exceptions.ChatNotFoundException;
import su.kometa.kometabackend.exceptions.MessageNotFoundException;
import su.kometa.kometabackend.exceptions.ModelNotFoundException;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.Message;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.repositories.MessageRepository;

import javax.annotation.processing.Messager;
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

    @Transactional
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
                modelMessage.setParentMessage(userMessage);

                messageRepository.save(modelMessage);
                break;
            default:
                throw new ModelNotFoundException();
        }

        messageRepository.save(userMessage);

        return userMessage;
    }

    @Transactional
    public Message updateMessageAndRequestModelResponse(Chat chat, User user, MessageEditDTO body, long messageId) {
        Message message = getMessageById(messageId);
        message.setContent(body.getContent());
        messageRepository.save(message);

        String modelProvider = chat.getModel().getProvider();
        Message oldModelMessage = messageRepository.findByParentMessage(message).orElse(null);
        Message modelMessage;

        switch (modelProvider) {
            case "openai":
                return message;
            case "gemini":
                modelMessage = geminiService.sendMessage(chat, body.getContent());

                if (oldModelMessage != null) {
                    oldModelMessage.setContent(modelMessage.getContent());
                    messageRepository.save(oldModelMessage);
                } else {
                    modelMessage.setParentMessage(message);
                    messageRepository.save(modelMessage);
                }
                break;
            default:
                throw new ModelNotFoundException();
        }

        return message;
    }
}
