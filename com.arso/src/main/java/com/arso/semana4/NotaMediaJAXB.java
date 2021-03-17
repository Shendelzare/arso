package com.arso.semana4;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import es.um.acta.Acta;
import es.um.acta.TipoCalificacion;

public class NotaMediaJAXB {

public static void main(String[] args) {
	JAXBContext context;
	try {
		context = JAXBContext.newInstance("es.um.acta");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		System.setProperty("javax.xml.accessExternalDTD", "all");
		double notaMedia=0;
		Acta acta = (Acta) unmarshaller.unmarshal(new File("src/main/resources/xml/acta.xml"));
		List<TipoCalificacion> tiposCalificaciones = acta.getCalificacion();
		for(TipoCalificacion calificacion : tiposCalificaciones) {
			notaMedia += calificacion.getNota();
		}
		notaMedia = notaMedia/tiposCalificaciones.size();
		
		System.out.println("nota media:"+notaMedia+" de un total de "+tiposCalificaciones.size()+" calificaciones");
	} catch (JAXBException e) {
		e.printStackTrace();
	}
	
}	
		
	
}
