package com.arso.semana7.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonIgnore;

public class Opcion {

	private String descripcion;
	private List<String> votos = new ArrayList<String>();

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String> getVotos() {
		return votos;
	}

	public void setVotos(List<String> votos) {
		this.votos = votos;
	}
	@BsonIgnore
	public int getNumeroVotos() {
		return this.votos.size();
	}
}
