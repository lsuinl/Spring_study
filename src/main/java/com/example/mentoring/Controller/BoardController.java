package com.example.mentoring.Controller;

import com.example.mentoring.entity.Board;
import com.example.mentoring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // @Non null + static 생성자
@RestController
public class BoardController {

    private final BoardService boardService;

    // GET 전체 게시물 조회 -> 대략적인 게시물 정보 확인
    // GET 상세 게시물 조회 -> 게시글 상세 조회
    // POST 게시글 작성
    // PUT 게시글 수정
    // DELETE 게시글 삭제


    // localhost:8080/boards
    // 전체 게시물 조회
    @GetMapping("/boards")
    public ResponseEntity<?> getBoards() {
        return new ResponseEntity<>(boardService.getBoards(), HttpStatus.OK);
    }


    // ex) localhost:8080/boards/3
    // 단건 게시글 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<?> getBoard(@PathVariable("id") Long id) {
        return new ResponseEntity<>(boardService.getBoard(id), HttpStatus.OK);
    }


    // HttpStatus.OK == 200, HttpStatus.CREATED == 201
    // localhost:8080/boards (Only POST)
    // POST 게시글 작성
    // 매개변수로 게시글이 들어온다 -> 들어온 게시글을 데이터베이스에 저장해준다.
    // @RequestBody 를 붙이는 이유 -> JSON 타입으로 데이터가 들어오는데, 이걸 자바에서 인식할 수 있게, 자바 클래스로 매핑 시켜준다.

    // REST API -> JSON 형식으로 데이터를 받아야한다.
    // @RequestBody Entity entity -> JSON 형식인 데이터를, 자바 타입으로 바꿔준다.
    @PostMapping("/boards")
    public ResponseEntity<?> save(@RequestBody Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.CREATED);
    }


    // PUT 게시글 수정
    // ex) localhost:8080/boards/3
    // 게시글 수정하고 -> 완료 버튼을 누른다 -> 백엔드 서버 요청 (id, updateBoard)
    @PutMapping("/boards/{id}")
    public ResponseEntity<?> editBoard(@PathVariable("id") Long id, @RequestBody Board updateBoard) {
        return new ResponseEntity<>(boardService.editBoard(id, updateBoard), HttpStatus.OK);
    }


    // DELETE 게시글 삭제
    // ex) localhost:8080/boards/3
    // 게시글 삭제하기
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }
}
