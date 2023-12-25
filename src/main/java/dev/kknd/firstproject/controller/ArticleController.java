package dev.kknd.firstproject.controller;

import dev.kknd.firstproject.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {
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
}
