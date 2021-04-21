package com.arso.semana7.servicio;

public class ServicioException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6878373593863024372L;

	public ServicioException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public ServicioException(String msg) {
		super(msg);		
	}
}
