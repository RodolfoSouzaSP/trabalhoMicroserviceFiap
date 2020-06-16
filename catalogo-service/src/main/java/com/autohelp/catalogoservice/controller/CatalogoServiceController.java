package com.autohelp.catalogoservice.controller;

import org.hibernate.mapping.List;

public class CatalogoServiceController {

	public List<ListaGeneroData> listaGenero(String genero)
	{
		// Método para pesquisa de filme por gênero
		
	    return null;
		
	}
	
	public List<ListaPesquisaPalavraChaveData> pesquisaPalavraChave(String chave)
	{
		// Método para pequisa por Palavra Chave
		
		return null;
		
	}
	
	public DetalheFilmeData visualizarDetalheFilme(long idFilme)
	{
		// Método para mostrar os detalhes do filme
		
		return null;
	}
	
	public void MarcarParaVerNoFuturo(long idFilme)
	{
		// Método para marcar o filme para visualização futura
		
	}
	
	public List<ListaFilmeFuturoData> listaFilmeVerFuturo(int idUsuario)
	{
		// Método para retornar a lista de filmes para visualização futura do usuário
		
		
	}
	
	public void cadastrarFilme(FilmeData filme)
	{
		
	}
}
