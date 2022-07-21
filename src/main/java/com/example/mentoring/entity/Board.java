package com.example.mentoring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data // Getter + Setter
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 인스턴스를 받는 생성자
@Entity // Board가 Entity인 것을 명시해준다.
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 인스턴스를 생성하면, 자동으로 인덱스를 붙여라 -> 1, 2, , 4 , 5, 6
    private Long id; // 1, 2, 3, 4, 5 테이블에서 구별하기위한 Index Id

    private String title;

    private String content;

    private String writer;
}