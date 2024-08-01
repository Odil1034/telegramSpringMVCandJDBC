package uz.pdp.maven.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.maven.model.message.Message;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MessageRepository implements BaseRepository<Message> {

    @Autowired
    private JdbcTemplate template;

    private RowMapper<Message> rowMapper = (rs, rowNum) -> {
        String id = rs.getString("id");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        String text = rs.getString("text");
        String ownerId = rs.getString("owner_id");
        String chatId = rs.getString("chat_id");

        Message message = new Message(id, createdAt, updatedAt, text, ownerId, chatId);
        return message;
    };

    @Override
    public Message get(String id) {
        String query = "SELECT FROM messages m WHERE m.id = ?";
        try {
            Message message = template.queryForObject(query, rowMapper, id);
            return message;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean save(Message message) {
        String query = "INSERT INTO messages (text, owner_id, chat_id) VALUES (?, ?, ?, ?)";
        template.update(query, message.getText(), message.getOwnerId(), message.getChatId());
        return true;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE messages WHERE id = ?";
        template.update(query, id);
        return true;
    }

    @Override
    public boolean update(Message message) {
        String query = "UPDATE messages SET text = ?, owner_id = ?, chat_id = ? WHERE id = ?";
        template.update(query, message.getText(), message.getOwnerId(), message.getChatId(), message.getId());
        return true;
    }

    @Override
    public List<Message> getAll() {
        String query = "INSERT INTO messages (text, owner_id, chat_id) VALUES (?, ?, ?, ?)";
        List<Message> messages = template.query(query, rowMapper);
        return messages;
    }

    public List<Message> getMessageByUserId(String userId) {
        String query = "SELECT * FROM messages WHERE owner_id = ?";
        List<Message> messages = template.query(query, rowMapper, userId);
        return messages;
    }

    public List<Message> getChatMessagesByChatId(String chatId) {
        String query = "SELECT * FROM messages WHERE chat_id = ?";
        List<Message> messages = template.query(query, rowMapper, chatId);
        return messages;
    }
}
