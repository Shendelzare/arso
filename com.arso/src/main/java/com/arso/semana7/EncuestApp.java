package com.arso.semana7;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.arso.semana7.model.Encuesta;
import com.arso.semana7.model.ListadoEncuestas;
import com.arso.semana7.model.Opcion;
import com.arso.semana7.servicio.IServicioEncuestas;
import com.arso.semana7.servicio.ServicioException;
import com.arso.semana7.servicio.impl.ServicioEncuestas;

public class EncuestApp {

	public static void main(String[] args) throws ServicioException {
		IServicioEncuestas servicioEncuestas = ServicioEncuestas.getInstance();
		
		Encuesta nuevaEncuesta = new Encuesta();
		nuevaEncuesta.setFechaApertura(LocalDateTime.now().minusHours(3));
		nuevaEncuesta.setFechaCierre(LocalDateTime.now().plusHours(5));
		nuevaEncuesta.setInstrucciones("Para votar dale escoge una opcion");
		nuevaEncuesta.setOpciones(new ArrayList<Opcion>());
		nuevaEncuesta.setTitulo("Encuesta Test");
		Opcion nuevaOpcion = new Opcion();
		Opcion nuevaOpcion2 = new Opcion();
		nuevaOpcion.setDescripcion("Opcion1");
		nuevaOpcion2.setDescripcion("Opcion2");
		
		nuevaEncuesta.getOpciones().add(nuevaOpcion);
		nuevaEncuesta.getOpciones().add(nuevaOpcion2);
	
		
		String idPrimeraEncuesta = servicioEncuestas.alta(nuevaEncuesta);
		
		System.out.println("Encuesta : "+nuevaEncuesta.getTitulo()+" - persitsida con id: "+idPrimeraEncuesta);
		servicioEncuestas.votar(idPrimeraEncuesta, "fulanita@fumail.com", 2);
		servicioEncuestas.votar(idPrimeraEncuesta, "fulanito@fumail.com", 1);
		servicioEncuestas.votar(idPrimeraEncuesta, "fulanitinino@fumail.com", 1);
		System.out.println("fulanita ha votado? "+servicioEncuestas.encuestaVotada(idPrimeraEncuesta, "fulanita@fumail.com"));
		System.out.println("pepita ha votado? "+servicioEncuestas.encuestaVotada(idPrimeraEncuesta, "pepita@fumail.com"));

		ListadoEncuestas listado = servicioEncuestas.obtenerEncuestas();
		System.out.println(listado.getEncuestasResumen().size());
		
		servicioEncuestas.borrarEncuesta(idPrimeraEncuesta);
		
		servicioEncuestas.obtenerEncuesta(idPrimeraEncuesta);
	}

}
