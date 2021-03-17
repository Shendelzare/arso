package com.arso.semana4;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import es.um.acta.Acta;
import es.um.acta.TipoCalificacion;
import es.um.acta.TipoDiligencia;

public class ModificarActaJAXB {

private static final double NOTA_EXTRA = 0.50;

public static void main(String[] args) {
	JAXBContext context;
	try {
		context = JAXBContext.newInstance("es.um.acta");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		System.setProperty("javax.xml.accessExternalDTD", "all");
	
		Acta acta = (Acta) unmarshaller.unmarshal(new File("src/main/resources/xml/acta.xml"));
		List<TipoCalificacion> tiposCalificaciones = acta.getCalificacion();
		for(TipoCalificacion calificacion : tiposCalificaciones) {
			double notaActual = calificacion.getNota();
			calificacion.setNota(sumaExtra(notaActual));

		}
		List<TipoDiligencia> tiposDiligencias = acta.getDiligencia();
		for(TipoDiligencia tiposDiligencia : tiposDiligencias) {
			double notaActual = tiposDiligencia.getNota();
			tiposDiligencia.setNota(sumaExtra(notaActual));
		}
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.marshal(acta, new File("src/main/resources/xml/actaModificada.xml"));
	} catch (JAXBException e) {
		e.printStackTrace();
	}
	
}	
	private static double sumaExtra(double nota) {
		System.out.println("nota sin modificar "+nota);

		if(nota<=9.5) {
			nota+=NOTA_EXTRA;
			System.out.println("nota modificada "+nota);

		}
		return nota;
	}
	
}
