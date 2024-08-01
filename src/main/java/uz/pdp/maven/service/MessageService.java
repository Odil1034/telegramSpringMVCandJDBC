package uz.pdp.maven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.maven.model.message.Message;
import uz.pdp.maven.repository.MessageRepository;

import java.util.List;

@Component
public class MessageService implements BaseService<Message> {

    @Autowired
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message get(String id) {
        return messageRepository.get(id);
    }

    @Override
    public boolean save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public boolean delete(String id) {
        return messageRepository.delete(id);
    }

    @Override
    public boolean update(Message message) {
        return messageRepository.update(message);
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public List<Message> getMessageByUserID(String userId) {
        return messageRepository.getMessageByUserId(userId);
    }

    public List<Message> getChatMessagesByChatId(String chatId) {
        return messageRepository.getChatMessagesByChatId(chatId);
    }
}
