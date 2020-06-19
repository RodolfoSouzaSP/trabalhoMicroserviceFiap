package com.autohelp.catalogoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autohelp.catalogoservice.model.FilmeFuturo;

public interface CatalogoRepositoryFilmeFuturo extends JpaRepository<FilmeFuturo, Long> {

	FilmeFuturo findByIdUsuario(long idUsuario);
}
