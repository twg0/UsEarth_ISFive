package com.isfive.usearth.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
