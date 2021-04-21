package com.arso.semana7.repositorio;

import com.arso.semana7.repositorio.impl.RepositorioEncuestaMongoDB;

public class FactoriaRepositorioEncuestas {

	private static RepositorioEncuesta repository = null;
	
	public static RepositorioEncuesta getRepositorio() {
		if (repository == null) {
			repository = new RepositorioEncuestaMongoDB();
		}
		return repository;
	}
	
	// TODO: configuracion de la clase con propiedades
	
}
