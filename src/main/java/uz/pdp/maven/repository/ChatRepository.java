package uz.pdp.maven.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.maven.model.chat.Chat;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ChatRepository implements BaseRepository<Chat> {

    @Autowired
    private JdbcTemplate template;
    private final RowMapper<Chat> rowMapper = (rs, rowNum) -> {
        String user1Id = rs.getString("user1_id");
        String user2Id = rs.getString("user2_id");
        String id = rs.getString("id");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

        Chat chat = new Chat(id, createdAt, updatedAt, user1Id, user2Id);
        return chat;
    };

    @Override
    public Chat get(String id) {
        try {
            String query = "SELECT FROM chats WHERE id = ?";
            Chat chat = template.queryForObject(query, rowMapper, id);
            return chat;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public boolean save(Chat chat) {
        String query = "INSERT INTO chats (user1_id, user2_id) VALUES (?, ?)";
        template.update(query, chat.getUser1Id(), chat.getUser2Id());
        return true;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE chats WHERE id = ?";
        template.update(query, id);
        return true;
    }

    @Override
    public boolean update(Chat chat) {
        String query = "UPDATE chats SET user1_id = ?, user2_id = ? WHERE id = ?";
        template.update(query, chat.getUser1Id(), chat.getUser2Id(), chat.getId());
        return true;
    }

    @Override
    public List<Chat> getAll() {
        String query = "SELECT * FROM chats";
        List<Chat> chats = template.query(query, rowMapper);
        return chats;
    }

    public List<Chat> getUserChatsByUserId(String userId) {
        String query = "SELECT * FROM chats WHERE user1_id = ? OR user2_id = ?";
        List<Chat> chats = template.query(query, rowMapper, userId, userId);
        return chats;
    }

    public Chat getChat(String user1Id, String user2Id) {
        String query = "SELECT FROM chats WHERE (user1_id = ? AND user2_id = ?) OR (user2_id = ? AND user1_id = ?)";
        Chat chat = template.queryForObject(query, rowMapper, user1Id, user2Id, user2Id, user1Id);
        return chat;
    }
}
