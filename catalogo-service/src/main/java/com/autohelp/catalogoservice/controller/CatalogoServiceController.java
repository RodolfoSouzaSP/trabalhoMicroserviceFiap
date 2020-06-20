package com.autohelp.catalogoservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.autohelp.catalogoservice.model.FilmeFuturo;
import com.autohelp.catalogoservice.model.ListaFilmeFuturoData;
import com.autohelp.catalogoservice.model.ListaGeneroData;
import com.autohelp.catalogoservice.model.ListaPesquisaPalavraChaveData;
import com.autohelp.catalogoservice.repository.CatalogoRepositoryFilme;
import com.autohelp.catalogoservice.repository.CatalogoRepositoryFilmeFuturo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/catalogo")
@Api(value = "Serviço de Catálogo de Filmes")
@CrossOrigin(origins = "*")
public class CatalogoServiceController {

	@Autowired
	CatalogoRepositoryFilme catalogoRepositoryFilme;

	@Autowired
	CatalogoRepositoryFilmeFuturo catalogoRepositoryFilmeFuturo;

	@RequestMapping(value = "/listaGenero/{Gênero}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para pesquisa de filme por gênero")
	public List<ListaGeneroData> listaGenero(@PathVariable("Gênero") String genero) {
		List<Filme> filme = new ArrayList<Filme>();

		List<ListaGeneroData> listaGenero = new ArrayList<ListaGeneroData>();

		filme = catalogoRepositoryFilme.findByGenero(genero);

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

	@RequestMapping(value = "/pesquisaPalavraChave/{Chave}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para pesquisa de filme por palavra Chave")
	public List<ListaPesquisaPalavraChaveData> pesquisaPalavraChave(@PathVariable("Chave") String chave) {
		List<Filme> filme = new ArrayList<Filme>();
		List<ListaPesquisaPalavraChaveData> listaPalavraChave = new ArrayList<ListaPesquisaPalavraChaveData>();

		filme = catalogoRepositoryFilme.findByTitulo(chave);

		if (filme != null) {

			for (Filme item : filme) {

				ListaPesquisaPalavraChaveData dado = new ListaPesquisaPalavraChaveData();

				dado.idFilme = item.getId();
				dado.titulo = item.getTitulo();
				dado.url = item.getUrl();
				dado.detalhe = item.getDetalhe();

				listaPalavraChave.add(dado);
			}
		}

		return listaPalavraChave;

	}

	@HystrixCommand(fallbackMethod = "reliablevisualizarDetalheFilme")
	@RequestMapping(value = "/visualizarDetalheFilme/{idFilme}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para mostrar os detalhes do filme")
	public DetalheFilmeData visualizarDetalheFilme(@PathVariable("idFilme") long idFilme) {

		DetalheFilmeData detalheFilme = new DetalheFilmeData();

		Filme filme = new Filme();

		filme = catalogoRepositoryFilme.findById(idFilme);

		if (filme != null) {
			detalheFilme.ano = filme.getAno();
			detalheFilme.categoria = filme.getCategoria();
			detalheFilme.detalhe = filme.getDetalhe();
			detalheFilme.duracao = filme.getDuracao();
			detalheFilme.genero = filme.getGenero();
			detalheFilme.id = filme.getId();
			detalheFilme.titulo = filme.getTitulo();
			detalheFilme.url = filme.getUrl();
		} else {
			throw new RuntimeException();
		}

		return detalheFilme;
	}

	@PostMapping(value = "/marcarParaVerNoFuturo")
	@ApiOperation(value = "Método para marcar o filme para visualização futura")
	public void marcarParaVerNoFuturo(@RequestBody FilmeFuturo dado) {

		FilmeFuturo filmeFuturo = new FilmeFuturo();

		filmeFuturo.setIdUsuario(dado.getIdUsuario());
		filmeFuturo.setIdFilme(dado.getIdFilme());

		catalogoRepositoryFilmeFuturo.save(filmeFuturo);
	}

	@RequestMapping(value = "/listaFilmeVerFuturo/{idUsuario}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para marcar o filme para visualização futura")
	public List<ListaFilmeFuturoData> listaFilmeVerFuturo(@PathVariable("idUsuario") int idUsuario) {

		List<FilmeFuturo> listaFilmeFuturo = new ArrayList<FilmeFuturo>();
		List<ListaFilmeFuturoData> listaFilmeFuturoData = new ArrayList<ListaFilmeFuturoData>();

		try {

			listaFilmeFuturo = catalogoRepositoryFilmeFuturo.findByIdUsuario(idUsuario);

			if (listaFilmeFuturo != null) {

				for (FilmeFuturo item : listaFilmeFuturo) {
					ListaFilmeFuturoData dado = new ListaFilmeFuturoData();

					dado.IdFilme = item.getIdFilme();

					Filme filme = new Filme();

					filme = catalogoRepositoryFilme.findById(item.getIdFilme());

					dado.titulo = filme.getTitulo();
					dado.url = filme.getUrl();

					listaFilmeFuturoData.add(dado);
				}
			} else {
				ListaFilmeFuturoData listavazia = new ListaFilmeFuturoData();

				listavazia.titulo = "Nenhum título localizado para esse usuário";

				listaFilmeFuturoData.add(listavazia);
			}

		} catch (Exception e) {
			listaFilmeFuturoData = null;
		}

		return listaFilmeFuturoData;

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

		catalogoRepositoryFilme.save(filme);
	}

	public DetalheFilmeData reliablevisualizarDetalheFilme(long idFilme) {

		DetalheFilmeData detalheFilme = new DetalheFilmeData();

		// Implementação para simulação do erro: Devolve como não localizado ou Devolve
		// como erro no serviço
		Random random = new Random();
		int erro = random.nextInt(2) + 1;

		if (erro == 1) {
			detalheFilme.observacao = "Serviço de localização indisponível. Por favor tente mais tarde.";
		} else
			detalheFilme.observacao = "Filme não localizado. Por favor informe um novo id.";

		return detalheFilme;
	}
}
