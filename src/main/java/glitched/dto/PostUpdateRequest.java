package glitched.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostUpdateRequest {
    private String title;
    private String content;
    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
