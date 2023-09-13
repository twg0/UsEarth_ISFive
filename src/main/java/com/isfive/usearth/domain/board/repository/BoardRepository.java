package com.isfive.usearth.domain.board.repository;

import com.isfive.usearth.domain.board.entity.Board;
import com.isfive.usearth.exception.EntityNotFoundException;
import com.isfive.usearth.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

	default Board findByIdOrThrow(Long id) {
		return findById(id).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.BOARD_NOT_FOUND));
	}

	;
}
