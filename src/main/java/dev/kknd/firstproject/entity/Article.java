package dev.kknd.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Article {

    @Id // 대표값을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 전략
    private Long id;
    @Column
    private String title;

    @Column
    private String content;

}
