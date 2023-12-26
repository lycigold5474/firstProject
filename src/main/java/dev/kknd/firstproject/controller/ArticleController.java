package dev.kknd.firstproject.controller;

import dev.kknd.firstproject.dto.ArticleForm;
import dev.kknd.firstproject.entity.Article;
import dev.kknd.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
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
        log.info(form.toString());
        // System.out.println(form.toString());  // 로깅으로 대체

        // 1. Dto를 변환! Entity!
        Article article = form.toEntity();
        log.info("article = {}", article);

        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        log.info("saved = {}", saved);
        return form;
    }

    /**
     * url요청받기
     * @param id
     * @return
     */
    @GetMapping("/articles/{id}") // 해당 URL요청을 처리 선언
    public String show(@PathVariable(value="id") Long id, Model model) { // URL에서 id를 변수로 가져옴
        log.info("id = " + id);

        // 1: id로 데이터를 가져옴!
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2: 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);
        // 3: 보여줄 페이지를 설정!
        return "articles/show";
    }
}
