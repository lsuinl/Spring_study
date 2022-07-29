package com.example.mentoring.response;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class Failure implements Result{
    private String msg;
}
