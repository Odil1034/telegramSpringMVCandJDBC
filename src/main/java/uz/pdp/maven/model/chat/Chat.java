package uz.pdp.maven.model.chat;

import lombok.*;
import uz.pdp.maven.model.BaseModel;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Chat extends BaseModel {

    private String user1Id;
    private String user2Id;

    @Builder
    public Chat(String id, LocalDateTime createdAt, LocalDateTime updatedAt,
                String user1Id, String user2Id) {
        super(id, createdAt, updatedAt);
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }
}
