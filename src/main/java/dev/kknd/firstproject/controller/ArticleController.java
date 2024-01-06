package dev.kknd.firstproject.controller;

import dev.kknd.firstproject.dto.ArticleForm;
import dev.kknd.firstproject.entity.Article;
import dev.kknd.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        form.setId(saved.getId());

        // 리다이렉트 적용: 생성 후, 브라우저가 해당 URL로 재요청
        return form;
    }

    @PostMapping("/articles/save1")
    public String saveJsonData1(@RequestBody ArticleForm form, Model model) {
        log.info(form.toString());
        // System.out.println(form.toString());  // 로깅으로 대체

        // 1. Dto를 변환! Entity!
        Article article = form.toEntity();
        log.info("article = {}", article);

        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        log.info("saved = {}", saved);
        log.info("saved.getId() = " + saved.getId());


        // 리다이렉트 적용: 생성 후, 브라우저가 해당 URL로 재요청
        return "redirect:/articles/" + saved.getId();  // 리다이렉트가 되었으나 화면에서 이동이 안됨
    }

    /**
     * url요청받기
     * @param id
     * @return
     */
    @CrossOrigin(originPatterns = "*")
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

    /**
     * 모든 게시글을 가져온다
     * @return
     */
    @GetMapping("/articles")
    public String index(Model model) {
        // 1: 모든 Article을 가져온다!
        List<Article> articleEntityList = articleRepository.findAll();
        // 2: 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList", articleEntityList);
        // 3: 뷰 페이지를 설정!
        return "articles/index";
    }

    /**
     * 수정 페이지 이동 및 수정데이터 가져오기
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(@RequestBody ArticleForm form) {
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. 엔티티를 DB로 저장
        // 2-1. DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId())
                .orElse(null);
        // 2-2: 기존 데이터가 있다면, 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        return "redirect:/articles/"+ articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable(value="id") Long id,
                         RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");
        // 1: 삭제 대상을 가져옴
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2: 대상을 삭제
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }
        // 3: 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
