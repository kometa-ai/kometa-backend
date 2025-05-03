package su.kometa.kometabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.dtos.request.ChatCreateDTO;
import su.kometa.kometabackend.dtos.request.ChatEditDTO;
import su.kometa.kometabackend.exceptions.ChatNotFoundException;
import su.kometa.kometabackend.exceptions.ModelNotFoundException;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.repositories.ChatRepository;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final ModelService modelService;

    @Autowired
    public ChatService(ChatRepository chatRepository, ModelService modelService) {
        this.chatRepository = chatRepository;
        this.modelService = modelService;
    }

    public Chat getById(long id) {
        return chatRepository.findById(id).orElseThrow(ChatNotFoundException::new);
    }

    public List<Chat> getAllByUser(User user) {
        return chatRepository.findAllByUser(user);
    }

    public Chat create(ChatCreateDTO body, User user) {
        if (modelService.getByProvider(body.getModel().getProvider()) == null) throw new ModelNotFoundException();

        return chatRepository.save(new Chat(null, user, body.getModel()));
    }

    public Chat edit(Chat chat, ChatEditDTO body) {
        chat.setTitle(body.getTitle());
        return chatRepository.save(chat);
    }

    public void delete(long id) {
        chatRepository.delete(getById(id));
    }
}
