package dev.kknd.firstproject.service;

import dev.kknd.firstproject.repository.ArticleRepository;
import dev.kknd.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;
}
