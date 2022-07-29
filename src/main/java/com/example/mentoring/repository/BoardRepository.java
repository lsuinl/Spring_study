package com.example.mentoring.repository;

import com.example.mentoring.entity.Board; //게시판 데이터 받아오기.
import org.springframework.data.jpa.repository.JpaRepository; //검색메소드, 메소드 호출만으로 데이터 검색이 가능
//+ 데이터 조회 또는 저장, 변경, 삭제 시 해당 인터페이스로 데이터를 검색하고 관리 및 사용
//https://araikuma.tistory.com/329

public interface BoardRepository extends JpaRepository<Board, Long> { //게시판에서 받아온 데이터를 Jpa로 전송(게시물의 양이 늘어나더라도 문제없이 값을 받아올 수 있도록 Long로 id 받기)
}