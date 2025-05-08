package com.desafio.hexagonal.application.ports.input;

import com.desafio.hexagonal.domain.model.ManualMove;

public interface CreateMoveUseCase {
   
    ManualMove createMove(ManualMove move);
    
}
