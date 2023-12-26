package dev.kknd.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {

    @Id // 대표값을 지정
    @GeneratedValue //자동 생성 어노테이션
    private Long id;
    @Column
    private String title;

    @Column
    private String content;

}
