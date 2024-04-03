package shop.mtcoding.blog.model.user;

import lombok.Builder;

import java.sql.Timestamp;
import java.time.LocalDate;

public class SessionUser {
    private Integer id;
    private String email;
    private LocalDate createdAt;
    private Integer role;

    @Builder
    public SessionUser(Integer id, String email, LocalDate createdAt, Integer role) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.role = role;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.role = user.getRole();
    }
}
