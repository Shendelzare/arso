package com.arso.semana4;



import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.arso.semana4.repositorio.EntidadNoEncontrada;
import com.arso.semana4.repositorio.RepositorioException;
import com.arso.semana4.repositorio.bookle.FactoriaRepositorioActividades;
import com.arso.semana4.repositorio.bookle.RepositorioActividades;

import es.um.bookle.Actividad;
import es.um.bookle.DiaActividad;
import es.um.bookle.Reserva;
import es.um.bookle.Turno;

public class BookleApp {
	public static void main(String[] args) {
	RepositorioActividades repositorioActividades = FactoriaRepositorioActividades.getRepositorio();
	
	Actividad actividad = new Actividad();
	DiaActividad diaActividad = new DiaActividad();
	 GregorianCalendar c = new GregorianCalendar();
	 c.set(2021, 03, 17);
	    XMLGregorianCalendar fecha;
		try {
			fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			diaActividad.setFecha(fecha);
		} catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	Reserva reserva = new Reserva();
	actividad.setProfesor("Aang");
	reserva.setAlumno("Korra");
	Turno turno = new Turno();
	turno.setHorario("Mañana");
	turno.setReserva(reserva);
	
	actividad.setProfesor("Einstein");
	actividad.getAgenda().add(diaActividad);
	
	
	
	try {
		String id = repositorioActividades.add(actividad);
		System.out.println(id);
		Actividad actividadRecuperada = repositorioActividades.getById(id);
		
		System.out.println(actividadRecuperada.getId());
	} catch (RepositorioException e) {
		e.printStackTrace();
	} catch (EntidadNoEncontrada e) {
		e.printStackTrace();
	}
	}
}
