package uz.pdp.maven.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.pdp.maven.model.upload.Upload;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UploadRepository implements BaseRepository<Upload> {

    @Autowired
    private JdbcTemplate template;
    private final RowMapper<Upload> rowMapper = ((rs, rowNum) -> {
        String id = rs.getString("id");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        String generatedName = rs.getString("generated_name");
        String extension = rs.getString("extension");
        String mimeType = rs.getString("mime_type");
        String originalName = rs.getString("original_name");
        String ownerId = rs.getString("owner_id");

        Upload upload = new Upload(id, createdAt, updatedAt, generatedName, extension, originalName, mimeType, ownerId);
        return upload;
    });

    @Override
    public Upload get(String id) {
        String query = "SELECT FROM uploads u WHERE u.id = ?";
        try {
            Upload upload = template.queryForObject(query, rowMapper, id);
            return upload;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean save(Upload upload) {
        String query = "INSERT INTO uploads (extension, generated_name, mime_type, original_name) VALUES (?, ?, ?, ?)";
        template.update(query, upload.getExtension(), upload.getGeneratedName(), upload.getMimeType(), upload.getOriginalName());
        return true;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM uploads u where u.id = ?";
        template.update(query, id);
        return true;
    }

    @Override
    public boolean update(Upload upload) {
        String query = "UPDATE uploads SET extension = ?, mime_type = ?, generated_name = ?, original_name = ? WHERE id = ?";
        template.update(query, upload.getExtension(), upload.getMimeType(), upload.getGeneratedName(), upload.getOriginalName(), upload.getId());
        return true;
    }

    @Override
    public List<Upload> getAll() {
        String query = "SELECT * FROM uploads";
        List<Upload> uploads = template.query(query, rowMapper);
        return uploads;
    }

    public Upload getUserImageByUserId(String userId) {
        String query = "SELECT FROM uploads WHERE owner_id = ?";
        template.queryForObject(query, rowMapper, userId);
        return null;
    }
}
