import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentResponse {
    @JsonProperty("commentId")
    private int commentId;

    @JsonProperty("postId")
    private int postId;

    @JsonProperty("content")
    private String content;




    public int getPostId() {
        return postId;
    }

    public CommentResponse(int commentId, int postId, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
    }



    public String getContent() {
        return content;
    }
}


