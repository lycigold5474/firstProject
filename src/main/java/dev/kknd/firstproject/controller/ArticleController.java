package dev.kknd.firstproject.controller;

import dev.kknd.firstproject.dto.ArticleForm;
import dev.kknd.firstproject.entity.Article;
import dev.kknd.firstproject.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    private ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles/new")
    public String newAtricleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());
        return "articles/new1";
    }

    @GetMapping("/articles/new1")
    public String newAtricleForm1() {
        return "articles/new1";
    }

    @PostMapping("/articles/create1")
    @ResponseBody
    public ArticleForm receiveFormData(ArticleForm form) {
        System.out.println(form.toString());
        return form;
    }

    @PostMapping("/articles/create2")
    @ResponseBody
    public ArticleForm receiveJsonData(@RequestBody ArticleForm form) {
        System.out.println(form.toString());
        return form;
    }

    @PostMapping("/articles/save")
    @ResponseBody
    public ArticleForm saveJsonData(@RequestBody ArticleForm form) {
        System.out.println(form.toString());

        // 1. Dto를 변환! Entity!
        Article article = form.toEntity();
        System.out.println("article = " + article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        System.out.println("saved.toString() = " + saved.toString());
        return form;
    }
}
