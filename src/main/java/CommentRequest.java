import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentRequest {
    @JsonProperty("postId")
    private int postId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("content")
    private String content;

    public int getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }
}
