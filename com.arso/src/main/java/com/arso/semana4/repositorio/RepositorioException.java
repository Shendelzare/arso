package com.arso.semana4.repositorio;

@SuppressWarnings("serial")
public class RepositorioException extends Exception {

	public RepositorioException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public RepositorioException(String msg) {
		super(msg);		
	}
	
		
}
