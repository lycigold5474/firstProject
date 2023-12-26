package dev.kknd.firstproject.dto;

import dev.kknd.firstproject.entity.Article;

public class ArticleForm {

    private String title;
    private String content;

    public ArticleForm() {
    }

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity(){
        return new Article(null, title, content);
    }


}
