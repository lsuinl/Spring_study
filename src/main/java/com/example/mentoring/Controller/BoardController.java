package com.example.mentoring.Controller;

import com.example.mentoring.entity.Board; //게시판 데이터
import com.example.mentoring.service.BoardService; //게시판 기능
import lombok.RequiredArgsConstructor; //이거머야
import org.springframework.http.HttpStatus; //상태코드 표기를 위해서
import org.springframework.http.ResponseEntity; // HttpHeader와 body, object status 3가지 반환해줄 수 있게 해줌
import org.springframework.web.bind.annotation.*; //오버라이드 등의 annotation이 가능하도록 함
import com.example.mentoring.response.Response;
@RequiredArgsConstructor // @Non null + static 생성자
// final 필드에 대해 생성자를 만들어주는 메서드이다 굳이 매번 생성자를 따로 정의할 필요가 없도록 도와준다.
@RestController
public class BoardController {

    private final BoardService boardService; //게시판 기능을 가져올 수 있도록 함

    // GET 전체 게시물 조회 -> 대략적인 게시물 정보 확인
    // GET 상세 게시물 조회 -> 게시글 상세 조회
    // POST 게시글 작성
    // PUT 게시글 수정
    // DELETE 게시글 삭제


    // localhost:8080/boards
    // 전체 게시물 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards") //해당 주소에 저장된 게시물 GetMapping
    public Response getBoards() {
        return Response.success(boardService.getBoards());
    }
    ////boardService에서 구현한 findAllBoard()함수를 실행하여 반환하고, 상태코드 (ok)를 반환해준다.


    // ex) localhost:8080/boards/3
    // 단건 게시글 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{id}")
    public Response getBoard(@PathVariable("id") Long id) { //입력받은 id를 받아와 이를 기반으로 주소를 변경하고 정보를 불러옴
        return Response.success(boardService.getBoard(id));
    }


    // HttpStatus.OK == 200, HttpStatus.CREATED == 201
    // localhost:8080/boards (Only POST)
    // POST 게시글 작성
    // 매개변수로 게시글이 들어온다 -> 들어온 게시글을 데이터베이스에 저장해준다.
    // @RequestBody 를 붙이는 이유 -> JSON 타입으로 데이터가 들어오는데, 이걸 자바에서 인식할 수 있게, 자바 클래스로 매핑 시켜준다.

    // REST API -> JSON 형식으로 데이터를 받아야한다.
    // @RequestBody Entity entity -> JSON 형식인 데이터를, 자바 타입으로 바꿔준다.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/boards")
    public Response save(@RequestBody Board board) { //게시글의 전체 정보를 받아아서 추가
        return Response.success(boardService.save(board));
    }


    // PUT 게시글 수정
    // ex) localhost:8080/boards/3
    // 게시글 수정하고 -> 완료 버튼을 누른다 -> 백엔드 서버 요청 (id, updateBoard)
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/boards/{id}")
    public Response editBoard(@PathVariable("id") Long id, @RequestBody Board updateBoard) { //게시글의 전체 정보를 가져와 이전 정보에서 새롭게 붙여넣기
        return Response.success(boardService.editBoard(id, updateBoard));
    }


    // DELETE 게시글 삭제
    // ex) localhost:8080/boards/3
    // 게시글 삭제하기
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/{id}")
    public Response deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id); //삭제
        return Response.success("삭제완료");  //메세지와 함께 상태코드 전송
    }
}
