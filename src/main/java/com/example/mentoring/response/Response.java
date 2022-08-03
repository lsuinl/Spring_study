package com.example.mentoring.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Response {
    private boolean success;
    private int code;
    private Result result;

    public static Response success(){
        //데이터 없이 성공 반환해주는 경우
        return new Response(true,0,null);
    }

    public static <T> Response success(T data){
        //데이터를 포함한 성공반환의 경우
        return new Response(true,0,new Success<>(data));
    }

    public  static Response failure(int code, String msg){
        //에러 발생 반환의 경우
        return new Response(false,code,new Failure(msg));
    }
}
