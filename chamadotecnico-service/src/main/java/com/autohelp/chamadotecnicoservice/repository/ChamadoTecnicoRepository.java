package com.autohelp.chamadotecnicoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autohelp.chamadotecnicoservice.model.ChamadoTecnico;

public interface ChamadoTecnicoRepository extends JpaRepository<ChamadoTecnico, Long> {
	
	ChamadoTecnico findById(long id);
	
	@Query(value = "SELECT * FROM tb_chamado_tecnico WHERE status = ?1", nativeQuery = true)
	List<ChamadoTecnico> findByStatus(String status);

}
