package com.autohelp.chamadotecnicoservice.controller;

import java.sql.Date;
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

import com.autohelp.chamadotecnicoservice.model.AberturaChamadoTecnicoData;
import com.autohelp.chamadotecnicoservice.model.AlteracaoChamadoTecnicoData;
import com.autohelp.chamadotecnicoservice.model.ChamadoTecnico;
import com.autohelp.chamadotecnicoservice.repository.ChamadoTecnicoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/chamadotecnico")
@Api(value = "Serviço de Chamados Técnicos")
@CrossOrigin(origins = "*")
public class ChamadoTecnicoServiceController {

	@Autowired
	ChamadoTecnicoRepository chamadotecnicorepository;

	@PostMapping("/abrirChamado")
	@ApiOperation(value = "Método para efetuar a abertura de um chamado")
	public void abrirChamado(@RequestBody AberturaChamadoTecnicoData chamado) {

		ChamadoTecnico chamadotecnico = new ChamadoTecnico();

		java.util.Date dataAtual = new java.util.Date();

		Date date = new Date(dataAtual.getTime());

		chamadotecnico.setIdUsuario(chamado.idUsuario);
		chamadotecnico.setDescricao(chamado.descricao);
		chamadotecnico.setDataRegistro(date);
		chamadotecnico.setStatus("aberto");

		chamadotecnicorepository.save(chamadotecnico);
	}

	@PostMapping("/atualizarChamado")
	@ApiOperation(value = "Método para efetuar a atualização de um chamado")
	public void atualizarChamado(@RequestBody AlteracaoChamadoTecnicoData chamado) {

		ChamadoTecnico chamadotecnico = new ChamadoTecnico();

		chamadotecnico = chamadotecnicorepository.findById(chamado.idChamado);

		if (chamadotecnico != null) {

			chamadotecnico.setResponsavel(chamado.responsavel);
			chamadotecnico.setStatus(chamado.status);

			if (chamado.resolucao != null)
				chamadotecnico.setResolucao(chamado.resolucao);

			if (chamado.status.toLowerCase().equals("fechado")) {

				java.util.Date dataAtual = new java.util.Date();

				Date date = new Date(dataAtual.getTime());

				chamadotecnico.setDataFechamento(date);
			}

			chamadotecnicorepository.save(chamadotecnico);
		}

	}

	@RequestMapping(value = "/consultarporStatus/{status}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para buscar os chamados por status")
	public List<ChamadoTecnico> consultarporStatus(@PathVariable("status") String status) {

		List<ChamadoTecnico> listaChamadoTecnicoStatus = new ArrayList<ChamadoTecnico>();

		listaChamadoTecnicoStatus = chamadotecnicorepository.findByStatus(status);

		return listaChamadoTecnicoStatus;
	}

	@RequestMapping(value = "/consultarChamadoTecnico/{idChamado}", method = RequestMethod.GET)
	@ApiOperation(value = "Método para buscar os chamados por id")
	public ChamadoTecnico consultarChamadoTecnico(@PathVariable("idChamado") int idChamado) {

		ChamadoTecnico chamadoTecnico = new ChamadoTecnico();

		chamadoTecnico = chamadotecnicorepository.findById(idChamado);

		return chamadoTecnico;
	}

}
