package com.desafio.hexagonal.domain.service;

import java.util.List;

import com.desafio.hexagonal.application.ports.input.CreateMoveUseCase;
import com.desafio.hexagonal.application.ports.input.GetMovesUseCase;
import com.desafio.hexagonal.application.ports.output.MoveOutputPort;
import com.desafio.hexagonal.domain.model.ManualMove;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MoveService implements CreateMoveUseCase, GetMovesUseCase{

    private final MoveOutputPort moveOutputPort;

    @Override
    public ManualMove createMove(ManualMove move) {
        System.out.println("Criando Movimento Manual");
        return moveOutputPort.saveMove(move);
    }

	@Override
	public List<ManualMove> getAllMoves() {
        System.out.println("Retornando todos os Movimentos Manuais");
       return moveOutputPort.getAllMoves();
	}

	@Override
	public Long getRelease(Long month, Long year) {
		System.out.println("Retornando Lancamento");
	    return moveOutputPort.getRelease(month, year);
    
	}

	@Override
	public List<ManualMove> getMoveListByYearAndMonth(Long year, Long month) {
		System.out.println("Retornando Lancamento");
	    return moveOutputPort.getMoveListByYearAndMonth(year, month);
	}
}