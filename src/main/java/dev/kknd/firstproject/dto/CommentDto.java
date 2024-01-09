package dev.kknd.firstproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.kknd.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;

    // 참조 https://cbw1030.tistory.com/315
    @JsonProperty("article_id")
    private Long articleId;
    private String nicknmae;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
