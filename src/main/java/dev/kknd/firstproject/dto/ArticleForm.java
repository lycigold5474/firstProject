package dev.kknd.firstproject.dto;

import dev.kknd.firstproject.entity.Article;
import lombok.*;

@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public ArticleForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Article toEntity(){
        return new Article(id, title, content);
    }


}
