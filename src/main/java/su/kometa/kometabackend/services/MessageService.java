
package su.kometa.kometabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Message> createMessageAndRequestModelResponse(Chat chat, User user, String content) {
        chat = chatService.getById(chat.getId());

        String modelProvider = chat.getModel().getProvider();

        List<Message> messages = new ArrayList<>();

        switch (modelProvider) {
            case "openai":
                return null;
            case "gemini":
                messages.add(geminiService.sendMessage(chat, content));
                break;
            default:
                throw new ModelNotFoundException();
        }

        messages.add(new Message(user, null, chat, content));

        return messages;
    }
}
