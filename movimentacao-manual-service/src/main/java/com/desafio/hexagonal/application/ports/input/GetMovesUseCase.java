package com.desafio.hexagonal.application.ports.input;

import java.util.List;

import com.desafio.hexagonal.domain.model.ManualMove;

public interface GetMovesUseCase {
   
	List<ManualMove> getAllMoves();
	
	Long getRelease(Long month, Long year);

	List<ManualMove> getMoveListByYearAndMonth(Long year, Long month);
    
}
