package glitched.dto;

import glitched.domain.Post;
import lombok.Getter;

public class PostResponse {
    private final long id;
    private final String title;
    private final String content;

    public PostResponse(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
