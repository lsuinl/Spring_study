package com.example.mentoring.controller;

import com.example.mentoring.Controller.BoardController;
import com.example.mentoring.entity.Board;
import com.example.mentoring.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TDD테스트 주도 개발 ..?
@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
    @InjectMocks
    BoardController boardController;

    @Mock
    BoardService boardService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }
    @Test
    @DisplayName("게시글 작성")
    public void saveBoardTest() throws Exception {
    //줍니다.
        Board board = new Board(1L,"제목", "내용", "홍길동");

    //언제, 그리고(?)
        mockMvc.perform(
                post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(board)))
                .andExpect(status().isCreated());
        verify(boardService).save(board);
    }

    @Test
    @DisplayName("전체 게시글 조회")
    public void findBoardsTest() throws Exception{
        mockMvc.perform(
                get("/boards"))
                .andExpect(status().isOk());
        verify(boardService).getBoards();
    }

    @Test
    @DisplayName("게시글 단건 조회")
    public void findBoardTest() throws Exception{
        Long id = 1L;

        mockMvc.perform(
                get("/boards/{id}",id))
                .andExpect(status().isOk());
        verify(boardService).getBoard(id);
    }

    @Test
    @DisplayName("게시글 수정")
    public void editBoardTest() throws  Exception{
        Long id = 1L;
        Board board = new Board(1L, "제목1", "내용1", "작성자");

        mockMvc.perform(
                put("/boards/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(board)))
                .andExpect(status().isOk());

        verify(boardService).editBoard(id, board);
        assertThat(board.getTitle()).isEqualTo("제목1");
    }

    @Test
    @DisplayName("게시글 삭제")
    public void deleteBoardTest() throws Exception{
        Long id = 1L;

        mockMvc.perform(
                delete("/boards/{id}", id))
                .andExpect(status().isOk());
        verify(boardService).deleteBoard(id);
    }
}

