package com.arso.semana5.model;

import java.util.ArrayList;
import java.util.List;

public class Acta {

	private String convocatoria;
	private String curso;
	private String asignatura;
	
	private List<Calificacion> calificacion = new ArrayList<>();
	private List<Diligencia> diligencia = new ArrayList<>();

	public String getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public List<Calificacion> getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(List<Calificacion> calificaciones) {
		this.calificacion = calificaciones;
	}

	public List<Diligencia> getDiligencia() {
		return diligencia;
	}

	public void setDiligencia(List<Diligencia> diligencias) {
		this.diligencia = diligencias;
	}

}
