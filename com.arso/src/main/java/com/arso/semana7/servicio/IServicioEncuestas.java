package com.arso.semana7.servicio;

import com.arso.semana7.model.Encuesta;
import com.arso.semana7.model.ListadoEncuestas;

public interface IServicioEncuestas {
	
	public String alta(Encuesta encuesta) throws ServicioException;
	
	public boolean encuestaVotada(String identificador, String mailVotante) throws ServicioException;
	
	public void votar(String identificador, String mailVotante, int indice) throws ServicioException;
	
	public Encuesta obtenerEncuesta(String identificador) throws ServicioException;
	
	public void borrarEncuesta(String identificador) throws ServicioException;
	
	public ListadoEncuestas obtenerEncuestas() throws ServicioException;

}
