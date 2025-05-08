package com.desafio.hexagonal.application.ports.output;

import java.util.List;

import com.desafio.hexagonal.domain.model.ManualMove;

public interface MoveOutputPort {
    
    ManualMove saveMove(ManualMove move);
    
    List<ManualMove> getAllMoves();
    
    Long getRelease(Long month, Long year);

	List<ManualMove> getMoveListByYearAndMonth(Long year, Long month);
    
}
