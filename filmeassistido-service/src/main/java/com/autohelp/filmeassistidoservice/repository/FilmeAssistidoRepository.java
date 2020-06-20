package com.autohelp.filmeassistidoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autohelp.filmeassistidoservice.model.FilmeAssistido;

public interface FilmeAssistidoRepository extends JpaRepository<FilmeAssistido, Long> {

	@Query(value = "SELECT * FROM tb_filme_assistido WHERE id_usuario = ?1", nativeQuery = true)
	List<FilmeAssistido> findByIdUsuario(long idUsuario);

	@Query(value = "SELECT * FROM tb_filme_assistido WHERE id_usuario = ?1 and categoria = ?2", nativeQuery = true)
	List<FilmeAssistido> findByIdUsuarioeCategoria(long idUsuario, String categoria);

	@Query(value = "SELECT * FROM tb_filme_assistido WHERE id_usuario = ?1 and gostou = 1", nativeQuery = true)
	List<FilmeAssistido> findByGostei(long idUsuario);

	@Query(value = "SELECT * FROM tb_filme_assistido WHERE categoria = ?1", nativeQuery = true)
	List<FilmeAssistido> findByCategoria(String categoria);

	@Query(value = "SELECT * FROM tb_filme_assistido WHERE id_usuario = ?1 and id_filme = ?2", nativeQuery = true)
	FilmeAssistido findByIdUsuarioeFilme(long idUsuario, long idFilme);

}
