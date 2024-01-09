package dev.kknd.firstproject.repository;

import dev.kknd.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(
            value =
                    "SELECT * " +
                    "FROM comment " +
                    "WHERE article_id = :articleId",
            nativeQuery = true
    )
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    List<Comment> findByNickname(String nickname);
}
