package com.arso.semana4.repositorio.bookle;


public class FactoriaRepositorioActividades {

	private static RepositorioActividades repository = null;
	
	public static RepositorioActividades getRepositorio() {
		if (repository == null) {
			repository = new RepositorioActividadesXML();
		}
		return repository;
	}
	
	// TODO: configuración de la clase con propiedades
	
}
