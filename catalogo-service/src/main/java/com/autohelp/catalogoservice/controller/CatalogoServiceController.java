package com.autohelp.catalogoservice.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autohelp.catalogoservice.model.DetalheFilmeData;
import com.autohelp.catalogoservice.model.Filme;
import com.autohelp.catalogoservice.model.FilmeData;
import com.autohelp.catalogoservice.model.ListaFilmeFuturoData;
import com.autohelp.catalogoservice.model.ListaGeneroData;
import com.autohelp.catalogoservice.model.ListaPesquisaPalavraChaveData;
import com.autohelp.catalogoservice.repository.CatalogoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/catalogo")
@Api(value = "Serviço de Catálogo de Filmes")
@CrossOrigin(origins = "*")
public class CatalogoServiceController {

	@Autowired
	CatalogoRepository catalogoRepository;

	@RequestMapping(value = "/listaGenero/{valor}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para pesquisa de filme por gênero")
	public List<ListaGeneroData> listaGenero(@PathVariable("valor") String genero) {
		List<Filme> filme = new ArrayList<Filme>();

		List<ListaGeneroData> listaGenero = new ArrayList<ListaGeneroData>();

		filme = catalogoRepository.findByGenero(genero);

		if (filme != null) {
			for (Filme item : filme) {
				ListaGeneroData dado = new ListaGeneroData();

				dado.idFilme = item.getId();
				dado.titulo = item.getTitulo();
				dado.url = item.getUrl();

				listaGenero.add(dado);
			}
		}

		return listaGenero;

	}

	@RequestMapping(value = "/pesquisaPalavraChave/{valor}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para pesquisa de filme por palavra chave")
	public List<ListaPesquisaPalavraChaveData> pesquisaPalavraChave(String chave) {
		List<Filme> filme = new ArrayList<Filme>();
		List<ListaPesquisaPalavraChaveData> listaPalavraChave = new ArrayList<ListaPesquisaPalavraChaveData>();

		filme = catalogoRepository.findByTitulo(chave);

		if (filme != null) {
			for (Filme item : filme) {
				
				ListaPesquisaPalavraChaveData dado = new ListaPesquisaPalavraChaveData();

				dado.IdFilme = item.getId();
				dado.titulo = item.getTitulo();
				dado.url = item.getUrl();

				listaPalavraChave.add(dado);
			}
		}

		return listaPalavraChave;

	}

	@RequestMapping(value = "/visualizarDetalheFilme/{valor}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para mostrar os detalhes do filme")
	public DetalheFilmeData visualizarDetalheFilme(@PathVariable("valor") long idFilme) {

		DetalheFilmeData detalheFilme = new DetalheFilmeData();

		Filme filme = new Filme();

		filme = catalogoRepository.findById(idFilme);

		if (filme != null) {
			detalheFilme.ano = filme.getAno();
			detalheFilme.categoria = filme.getCategoria();
			detalheFilme.detalhe = filme.getDetalhe();
			detalheFilme.duracao = filme.getDuracao();
			detalheFilme.genero = filme.getGenero();
			detalheFilme.id = filme.getId();
			detalheFilme.titulo = filme.getTitulo();
			detalheFilme.url = filme.getUrl();
		}

		return detalheFilme;
	}

	public void MarcarParaVerNoFuturo(long idFilme) {
		// Método para marcar o filme para visualização futura

	}

	public List<ListaFilmeFuturoData> listaFilmeVerFuturo(int idUsuario) {
		// Método para retornar a lista de filmes para visualização futura do usuário
		List<ListaFilmeFuturoData> listaFilmeFuturo = new ArrayList<ListaFilmeFuturoData>();

		return listaFilmeFuturo;

	}

	@PostMapping("/filme")
	@ApiOperation(value = "Método para gravar os dados do filme")
	public void cadastrarFilme(@RequestBody FilmeData dado) {
		Filme filme = new Filme();

		filme.setAno(dado.ano);
		filme.setCategoria(dado.categoria);
		filme.setDetalhe(dado.detalhe);
		filme.setDuracao(dado.duracao);
		filme.setGenero(dado.genero);
		filme.setTitulo(dado.titulo);
		filme.setUrl(dado.url);

		catalogoRepository.save(filme);
	}
}
