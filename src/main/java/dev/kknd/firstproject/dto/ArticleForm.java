package dev.kknd.firstproject.dto;

import dev.kknd.firstproject.entity.Article;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class ArticleForm {

    private String title;
    private String content;

    public Article toEntity(){
        return new Article(null, title, content);
    }


}
