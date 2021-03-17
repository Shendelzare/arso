package com.arso.semana4;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.um.acta.Acta;
import es.um.acta.TipoCalificacion;
import es.um.acta.TipoDiligencia;

public class CrearActaJAXB {

	private static final double NOTA_EXTRA = 0.50;

	public static void main(String[] args) throws DatatypeConfigurationException {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance("es.um.acta");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			System.setProperty("javax.xml.accessExternalDTD", "all");

			Acta acta = new Acta();
			acta.setConvocatoria("febrero");
			XMLGregorianCalendar curso;
			XMLGregorianCalendar fechaDiligencia;
			try {
				curso = DatatypeFactory.newInstance().newXMLGregorianCalendar();
				curso.setYear(2020);
				acta.setCurso(curso);

				acta.setAsignatura("1092");
				TipoCalificacion calificacion1 = new TipoCalificacion();
				TipoCalificacion calificacion2= new TipoCalificacion();

				calificacion1.setNif("23322156M");
				calificacion1.setNota(10);
				calificacion2.setNif("13322156M");
				calificacion2.setNota(8);
				calificacion2.setNombre("Pepe");
				TipoDiligencia diligencia = new TipoDiligencia();
				diligencia.setNif("13322156M");
				diligencia.setNota(9);
				fechaDiligencia = DatatypeFactory.newInstance().newXMLGregorianCalendar();
				fechaDiligencia.setYear(2021);
				fechaDiligencia.setMonth(2);
				fechaDiligencia.setDay(12);
				diligencia.setFecha(fechaDiligencia);
				acta.getCalificacion().add(calificacion1);

				acta.getCalificacion().add(calificacion2);
				acta.getDiligencia().add(diligencia);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
				throw e;
			}			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			marshaller.marshal(acta, new File("src/main/resources/xml/actaNnueva.xml"));
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
