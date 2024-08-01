package uz.pdp.maven.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.maven.dto.LoginDto;
import uz.pdp.maven.model.user.User;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository implements BaseRepository<User> {

    @Autowired
    private JdbcTemplate template;
    private final RowMapper<User> rowMapper = (rs, rowNum) -> {

        String id = rs.getString("id");
        String name = rs.getString("name");
        String username = rs.getString("username");
        String password = rs.getString("password");
        Integer age = rs.getInt("age");
        String email = rs.getString("email");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

        return new User(id, createdAt, updatedAt, name, age, email, username, password);
    };

    @Override
    public boolean save(User user) {
        System.out.println("user = " + user);
        String query = "INSERT INTO users(name, username, age, password, email) VALUES(?, ?, ?, ?, ?)";
        template.update(query, user.getName(), user.getUsername(), user.getAge(), user.getPassword(), user.getEmail());
        return true;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM users WHERE id = ?";
        template.update(query, id);
        return true;
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> users = template.query(query, rowMapper);
        return users;
    }

    @Override
    public User get(String id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            return template.queryForObject(query, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean update(User user) {
        String query = "UPDATE users SET username = ?, password = ?, name = ?, age = ?, email =? WHERE id = ?";
        template.update(query, user.getUsername(), user.getPassword(), user.getName(), user.getAge(), user.getEmail(), user.getId());
        return true;
    }

    public User login(LoginDto loginDto) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
        List<User> users = template.query(query, rowMapper, loginDto.username(), loginDto.password());
        return users.isEmpty() ? null : users.getFirst();
    }

    public List<User> getUsersByUsername(String searchUsername) {
        String query = "SELECT * FROM users WHERE username ILIKE ?";

        List<User> userList = template.query(query, rowMapper, "%" + searchUsername + "%");
        return userList;
    }

}
