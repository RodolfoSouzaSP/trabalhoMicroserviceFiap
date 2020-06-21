package com.autohelp.filmeassistidoservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autohelp.filmeassistidoservice.model.FilmeAssistido;
import com.autohelp.filmeassistidoservice.model.FilmeAssistidoGosteiData;
import com.autohelp.filmeassistidoservice.repository.FilmeAssistidoData;
import com.autohelp.filmeassistidoservice.repository.FilmeAssistidoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/filmeassistido")
@Api(value = "Serviço de Filmes Assistidos")
@CrossOrigin(origins = "*")
public class FilmeAssistidoServiceController {

	@Autowired
	FilmeAssistidoRepository filmeAssistidoRepository;

	@RequestMapping(value = "/listaFilmesAssitidos/{idUsuario}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para pesquisar filmes assistidos por usuário")
	public List<FilmeAssistido> listaFilmesAssitidos(@PathVariable("idUsuario") long idUsuario) {
		List<FilmeAssistido> filmeAssistido = new ArrayList<FilmeAssistido>();

		filmeAssistido = filmeAssistidoRepository.findByIdUsuario(idUsuario);

		return filmeAssistido;
	}

	@RequestMapping(value = "/listaFilmesVistosporCategoria/{categoria}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para pesquisar filmes assistidos por categoria")
	public List<FilmeAssistido> listaFilmesVistosporCategoria(@PathVariable("categoria") String categoria) {
		List<FilmeAssistido> filmeAssistido = new ArrayList<FilmeAssistido>();

		filmeAssistido = filmeAssistidoRepository.findByCategoria(categoria);

		return filmeAssistido;
	}

	private FilmeAssistido pesquisafilmeAssistido(long idUsuario, long idFilme) {

		FilmeAssistido filme = new FilmeAssistido();

		filme = filmeAssistidoRepository.findByIdUsuarioeFilme(idUsuario, idFilme);

		return filme;
	}

	@PostMapping(value = "/marcarComoGostei")
	@ApiOperation(value = "Método para marcar filmes assistidos com gostei")
	public void marcarComoGostei(@RequestBody FilmeAssistidoGosteiData filme) {
		
		FilmeAssistido filmeAssistido = new FilmeAssistido();
		FilmeAssistido pesquisaFilme = new FilmeAssistido();

		pesquisaFilme = pesquisafilmeAssistido(filme.idUsuario, filme.idFilme);

		if (pesquisaFilme == null) {

			filmeAssistido.setIdUsuario(filme.idUsuario);
			filmeAssistido.setIdFilme(filme.idFilme);
		}
		else
		{
			filmeAssistido.setId(pesquisaFilme.getId());
			filmeAssistido.setIdUsuario(pesquisaFilme.getIdUsuario());
			filmeAssistido.setIdFilme(pesquisaFilme.getIdFilme());
			filmeAssistido.setCategoria(pesquisaFilme.getCategoria());
		}
		
		filmeAssistido.setGostou(true);
		
		filmeAssistidoRepository.save(filmeAssistido);

	}

	@PostMapping(value = "/marcarAssistido")
	@ApiOperation(value = "Método para marcar filme como assistido")
	public void marcarAssistido(@RequestBody FilmeAssistidoData filme) {

		FilmeAssistido filmeAssistido = new FilmeAssistido();
		FilmeAssistido pesquisafilme = new FilmeAssistido();

		pesquisafilme = pesquisafilmeAssistido(filme.idUsuario, filme.idFilme);

		if (pesquisafilme == null) {

			filmeAssistido.setIdUsuario(filme.idUsuario);
			filmeAssistido.setIdFilme(filme.idFilme);
		}
		
		filmeAssistido.setGostou(false);
		
		filmeAssistidoRepository.save(filmeAssistido);
	}

}
