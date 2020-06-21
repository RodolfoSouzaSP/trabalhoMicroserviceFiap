package com.autohelp.catalogoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autohelp.catalogoservice.model.FilmeFuturo;

public interface CatalogoRepositoryFilmeFuturo extends JpaRepository<FilmeFuturo, Long> {

	@Query(value = "SELECT * FROM tb_filme_futuro WHERE id_Usuario = ?1", nativeQuery = true)
	List<FilmeFuturo> findByIdUsuario(long idUsuario);
}
