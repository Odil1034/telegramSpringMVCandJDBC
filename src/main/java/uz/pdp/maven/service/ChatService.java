package uz.pdp.maven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.maven.model.chat.Chat;
import uz.pdp.maven.model.message.Message;
import uz.pdp.maven.repository.ChatRepository;

import java.util.List;

@Component
public class ChatService implements BaseService<Chat> {

    @Autowired
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    public List<Chat> getUserChatsByUserId(String userId) {
        List<Chat> userChats = chatRepository.getUserChatsByUserId(userId);
        return userChats;
    }

    @Override
    public Chat get(String id) {
        return chatRepository.get(id);
    }

    @Override
    public boolean save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public boolean delete(String id) {
        return chatRepository.delete(id);
    }

    @Override
    public boolean update(Chat chat) {
        return chatRepository.update(chat);
    }

    @Override
    public List<Chat> getAll() {
        return chatRepository.getAll();
    }


    public Chat getChat(String user1Id, String user2Id) {
        return chatRepository.getChat(user1Id, user2Id);
    }
}
