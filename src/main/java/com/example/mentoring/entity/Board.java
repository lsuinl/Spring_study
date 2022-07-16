package com.example.mentoring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity 는 데이터베이스 테이블이라고 보면 됩니다.
 * Entity를 만들고 실행한 후, 데이터베이스를 확인해보면 아래 코드대로 테이블이 생성된 걸 볼 수 있습니다.
 */

@Data // Getter + Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity // 얘가 있어야지 스프링이 Board Entity 인식함
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30) // null값 허용 안됨, 길이 제한 30자 이내
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

}
