package uz.pdp.maven.model.upload;

import lombok.*;
import uz.pdp.maven.model.BaseModel;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Upload extends BaseModel {

    private String generatedName;
    private String extension;
    private String originalName;
    private String mimeType;
    private String ownerId;

    @Builder
    public Upload(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String ownerId,
                  String generatedName, String extension, String originalName, String mimeType) {
        super(id, createdAt, updatedAt);
        this.ownerId = ownerId;
        this.generatedName = generatedName;
        this.extension = extension;
        this.originalName = originalName;
        this.mimeType = mimeType;
    }
}
