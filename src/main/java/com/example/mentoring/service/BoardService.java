package com.example.mentoring.service;

import com.example.mentoring.entity.Board;
import com.example.mentoring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service 는 기능을 구현하는 것에 초점을 둔 클래스입니다.
 * Repository 를 불러와서, 데이터베이스에 데이터를 넣거나 혹은 가져와서 기능을 구현합니다.
 * <p>
 * Service 클래스는 Controller 에서 불러와서 사용합니다.
 */

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<Board> findAllBoard() {
        Board board = boardRepository.findById(1).get();

        return boardRepository.findAll();
    }

    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public Board findBoard(int id) {
        return boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    // 게시글 작성
    @Transactional
    public Board writeBoard(Board boardReq) {
        Board board = new Board(boardReq.getTitle(), boardReq.getContent(), boardReq.getWriter());
        boardRepository.save(board);
        return board;
    }


    // 게시글 수정
    @Transactional
    public Board editBoard(int id, Board updateBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException();
        });

        board.setTitle(updateBoard.getTitle());
        board.setContent(updateBoard.getContent());
        return board;
    }


    // 게시글 삭제
    @Transactional
    public void deleteBoard(int id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException();
        });

        boardRepository.deleteById(id);
    }
}

