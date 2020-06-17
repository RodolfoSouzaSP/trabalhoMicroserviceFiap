package com.autohelp.catalogoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autohelp.catalogoservice.model.Filme;

public interface CatalogoRepository extends JpaRepository<Filme, Long> {

	Filme findById(long id);
	
	@Query(value = "SELECT * FROM tb_filme WHERE genero = ?1", nativeQuery = true)
	List<Filme> findByGenero(String genero);
	
	@Query(value = "SELECT * FROM tb_filme WHERE titulo like %?1% or detalhe like %?1%", nativeQuery = true)
	List<Filme> findByTitulo(String chave);
}
