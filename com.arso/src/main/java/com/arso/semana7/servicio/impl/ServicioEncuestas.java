package com.arso.semana7.servicio.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.arso.semana4.repositorio.EntidadNoEncontrada;
import com.arso.semana4.repositorio.RepositorioException;
import com.arso.semana7.model.Encuesta;
import com.arso.semana7.model.ListadoEncuestas;
import com.arso.semana7.model.Opcion;
import com.arso.semana7.repositorio.FactoriaRepositorioEncuestas;
import com.arso.semana7.repositorio.RepositorioEncuesta;
import com.arso.semana7.repositorio.impl.RepositorioEncuestaMongoDB;
import com.arso.semana7.servicio.IServicioEncuestas;
import com.arso.semana7.servicio.ServicioException;

public class ServicioEncuestas implements IServicioEncuestas {

	private RepositorioEncuesta repositorio = FactoriaRepositorioEncuestas.getRepositorio();
	
	private static ServicioEncuestas instance = null;
	
	private ServicioEncuestas() {

	}
	
	public static ServicioEncuestas getInstance() {
		if(instance == null) {
			instance = new ServicioEncuestas();
		}
		return instance;
	}

	@Override
	public String alta(Encuesta encuesta) throws ServicioException {
		if(encuesta == null) {
			throw new ServicioException("Encuesta es null");
		}
		if(encuesta.getNumeroOpciones()<2) {
			throw new ServicioException("Encuesta debe contener un minimo de 2 opciones");
		}
		String identificador = null;
		try {
			identificador = repositorio.add(encuesta);
		} catch (RepositorioException e) {
			throw new ServicioException("Ocurrio un error persistiendo la encuesta ",e);
		}
		
		return identificador;
	}

	@Override
	public boolean encuestaVotada(String identificador, String mailVotante) throws ServicioException {
		Encuesta encuesta;
		try {
			encuesta = repositorio.getById(identificador);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			throw new ServicioException("Ocurrio un error recuperando la encuesta ",e);
		}
		if(encuesta==null) {
			throw new ServicioException("Encuesta es null");
		}
		List<Opcion> opciones = encuesta.getOpciones();
		for(Opcion estaOpcion : opciones) {
			if(estaOpcion.getVotos().contains(mailVotante)) {
				return true;
			}
		}
		return false;
		
	}

	@Override
	public void votar(String identificador, String mailVotante, int indice) throws ServicioException {
		Encuesta encuesta;
		try {
			encuesta = repositorio.getById(identificador);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			throw new ServicioException("Ocurrio un error recuperando la encuesta ",e);
		}
		if(encuesta.getFechaApertura().isBefore(LocalDateTime.now()) 
				&& encuesta.getFechaCierre().isAfter(LocalDateTime.now())
				&& !encuestaVotada(identificador, mailVotante)) {
			encuesta.getOpciones().get(indice-1).getVotos().add(mailVotante);
			try {
				repositorio.update(encuesta);
			} catch (RepositorioException | EntidadNoEncontrada e) {
				throw new ServicioException("No se pudo realizar actualizar la encuesta ",e);

			}
		}else {
			throw new ServicioException("No se pudo realizar la votación");
		}
	}

	@Override
	public Encuesta obtenerEncuesta(String identificador) throws ServicioException {
		Encuesta encuesta;
		try {
			encuesta = repositorio.getById(identificador);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			throw new ServicioException("Ocurrio un error recuperando la encuesta ",e);
		}
		if(encuesta==null) {
			throw new ServicioException("Encuesta es null");
		}
		return encuesta;
	}

	@Override
	public void borrarEncuesta(String identificador) throws ServicioException {
		Encuesta encuesta;
		try {
			encuesta = repositorio.getById(identificador);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			throw new ServicioException("Ocurrio un error recuperando la encuesta ",e);
		}
		if(encuesta==null) {
			throw new ServicioException("Encuesta es null");
		}
		try {
			repositorio.delete(encuesta);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			throw new ServicioException("Ocurrio un error elimniando la encuesta ",e);

		}
		
	}

	@Override
	public ListadoEncuestas obtenerEncuestas() throws ServicioException {
		ListadoEncuestas listadoEncuestas = new ListadoEncuestas();
		List<Encuesta> encuestas;
		listadoEncuestas.setEncuestasResumen(new HashMap<String,String>());
		try {
			encuestas = repositorio.getAll();
		} catch (RepositorioException e) {
			throw new ServicioException("Ocurrio un error recuperando el listado de encuestas ",e);
		}
		if(null!=encuestas && !encuestas.isEmpty()){
			encuestas.forEach(v->{listadoEncuestas.getEncuestasResumen().put(v.getTitulo(), v.getIdentificador());});
		}
		
		return listadoEncuestas;
	}
	
	public RepositorioEncuesta getInstanceLibros() {
		if (repositorio != null) {
			return repositorio;
		}
		return new RepositorioEncuestaMongoDB();
	}

}
