import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponse {
    @JsonProperty("postId")
    private int postId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;
}

