package com.desafio.hexagonal.infrastructure.adapters.input;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.hexagonal.application.ports.input.CreateMoveUseCase;
import com.desafio.hexagonal.application.ports.input.GetMovesUseCase;
import com.desafio.hexagonal.domain.model.ManualMove;
import com.desafio.hexagonal.infrastructure.adapters.input.rest.data.request.MoveRequest;
import com.desafio.hexagonal.infrastructure.adapters.input.rest.data.response.MoveResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/moves")
@RequiredArgsConstructor
public class MoveRestAdapter {
    
    private final CreateMoveUseCase createMoveUseCase;

    private final GetMovesUseCase getMovesUseCase;

    @Autowired
    private ModelMapper mapper;


    @PostMapping
    public ResponseEntity<?> createMove(@RequestBody MoveRequest moveToCreate){
        // Request to domain

	    	moveToCreate.setDtMovement(new Date());
	    	moveToCreate.setUser("TESTE");
	    	Long release = getMovesUseCase.getRelease(moveToCreate.getId().getMonth(), moveToCreate.getId().getYear());
	    	System.out.println("release : " + release);
	    	moveToCreate.getId().setRelease(++release);
	    	
	        ManualMove move = mapper.map(moveToCreate, ManualMove.class);
	        move = createMoveUseCase.createMove(move);
	        // Domain to response
	        return new ResponseEntity<>(mapper.map(move, MoveResponse.class), HttpStatus.CREATED);
   

    }

    @GetMapping
    public ResponseEntity<List<MoveResponse>> getMove(){
        List<ManualMove> allMoves = getMovesUseCase.getAllMoves();
        // Domain to response
        List<MoveResponse> allentities = allMoves.stream()
                .map(move -> mapper.map(move, MoveResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(allentities, HttpStatus.OK);
    }

    @GetMapping("{year}/{month}")
    public ResponseEntity<List<MoveResponse>> getSearch(@PathVariable("year") String year, @PathVariable("month") String month){
        List<ManualMove> allMoves = getMovesUseCase.getMoveListByYearAndMonth(Long.valueOf(year), Long.valueOf(month));
        // Domain to response
        List<MoveResponse> allentities = allMoves.stream()
                .map(move -> mapper.map(move, MoveResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(allentities, HttpStatus.OK);
    }

    
}
