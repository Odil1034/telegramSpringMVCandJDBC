package uz.pdp.maven.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
public abstract class BaseModel {

    protected String id;
    protected LocalDateTime createdAt;
    @Setter
    protected LocalDateTime updatedAt;

    public BaseModel(String id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
