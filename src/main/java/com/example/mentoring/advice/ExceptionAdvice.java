package com.example.mentoring.advice;

import com.example.mentoring.exception.WriterNotFoundException;
import com.example.mentoring.exception.BoardNotFoundException;
import com.example.mentoring.response.Response;//반응?
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //RestController과 Advice의 결합
public class ExceptionAdvice{
    //ExceptionAdvice == Exception을 관리하는 통제소== 실패할 경우에는 실패 메세지를 RestController로 전달해준다.

    //404 에러: 게시글을 찾기 못한 경우
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException(){
        return Response.failure(404, "게시글을 찾을 수 없습니다.");
    }

    //404 에러: 작성자를 찾지 못한 경우
    @ExceptionHandler(WriterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response writeNotFoundException(){
        return Response.failure(404, "작성자를 입력하세욥.");
    }
}