package uz.pdp.maven.model.message;

import lombok.*;
import uz.pdp.maven.model.BaseModel;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Message extends BaseModel {

    private String text;
    private String ownerId;
    private String chatId;

    @Builder
    public Message(String id, LocalDateTime createdAt, LocalDateTime updatedAt,
                   String text, String ownerId, String chatId) {
        super(id, createdAt, updatedAt);
        this.text = text;
        this.ownerId = ownerId;
        this.chatId = chatId;
    }
}
