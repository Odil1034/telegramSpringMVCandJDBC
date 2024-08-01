package uz.pdp.maven.model.user;

import lombok.*;
import uz.pdp.maven.model.BaseModel;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User extends BaseModel {

    private String name;
    private Integer age;
    private String email;
    private String username;
    private String password;

    @Builder
    public User(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, Integer age,
                String email, String username, String password) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
