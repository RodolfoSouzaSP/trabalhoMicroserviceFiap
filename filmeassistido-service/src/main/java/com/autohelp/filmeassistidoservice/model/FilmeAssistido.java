package com.autohelp.filmeassistidoservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_filme_assistido")
public class FilmeAssistido  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private long idUsuario;
	
	private long idFilme;
	
	private boolean gostou;
	
	private String categoria;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(long idFilme) {
		this.idFilme = idFilme;
	}

	public boolean isGostou() {
		return gostou;
	}

	public void setGostou(boolean gostou) {
		this.gostou = gostou;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
