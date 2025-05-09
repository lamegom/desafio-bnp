package com.desafio.hexagonal.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.hexagonal.application.ports.output.MoveOutputPort;
import com.desafio.hexagonal.domain.model.ManualMove;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.client.ProdutoApi;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity.ManualMoveEntity;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.mapper.MoveMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovePersistenceAdapter implements MoveOutputPort {

    private final MoveMapper moveMapper;
    
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProdutoApi produtoApi;

    @Transactional
	@Override
	public ManualMove saveMove(ManualMove move) {
        ManualMoveEntity moveEntity = moveMapper.toEntity(move);
        em.persist(moveEntity);
        return moveMapper.toManualMove(moveEntity);
	}

    @Transactional
	@Override
	public List<ManualMove> getAllMoves() {
    	TypedQuery<ManualMoveEntity> query = em.createQuery("SELECT u FROM ManualMoveEntity u", ManualMoveEntity.class);
    	List<ManualMoveEntity> moveList = query.getResultList();
    	
    	for(ManualMoveEntity m : moveList) {
			String cosif = produtoCosifById(m.getId().getIdProduct() , m.getId().getIdCosif());
			m.setCosif(cosif);
			String prod = produtoById(m.getId().getIdProduct());
			m.setProduto(prod);
    	}
    	
    	return moveList.stream()
	    	.map(moveEntity -> moveMapper.toManualMove(moveEntity))
	    	.collect(Collectors.toList());
	}


	@Override
	public Long getRelease(Long month, Long year) {
    	TypedQuery<Long> query = em.createQuery("SELECT u.id.release FROM ManualMoveEntity u WHERE u.id.month = :month and u.id.year = :year ORDER BY u.id.release DESC", Long.class);
    	query.setParameter("month", month);
    	query.setParameter("year", year);
    	try {
    		return query.getResultList().stream().findFirst().orElse(0L);
    	} catch(NoResultException n) {
    		return 0L;
    	}
	}
	
	@Override
	public List<ManualMove> getMoveListByYearAndMonth(Long year, Long month) {
    	TypedQuery<ManualMoveEntity> query = em.createQuery("SELECT u FROM ManualMoveEntity u WHERE u.id.month = :month and u.id.year = :year", ManualMoveEntity.class);
    	query.setParameter("month", month);
    	query.setParameter("year", year);

    	List<ManualMoveEntity> moveList = query.getResultList();
    	
    	for(ManualMoveEntity m : moveList) {
			String cosif = produtoCosifById(m.getId().getIdProduct() , m.getId().getIdCosif());
			m.setCosif(cosif);
			String prod = produtoById(m.getId().getIdProduct());
			m.setProduto(prod);
    	}
    	
    	return moveList.stream()
	    	.map(moveEntity -> moveMapper.toManualMove(moveEntity))
	    	.collect(Collectors.toList());

	}

	@Override
	public String produtoCosifById(Long idProduto, Long idCosif) {
		return produtoApi.produtoCosifById(idProduto, idCosif).getBody();

	}
	
	@Override
	public String produtoById(Long idProduto) {
		return produtoApi.produtosById(idProduto).getBody();

	}
	
    
}
