package com.arso.semana4;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import es.um.acta.Acta;
import es.um.acta.TipoDiligencia;

public class NumeroDiligenciasJAXB {

	public static void main(String[] args) {

			try {

				JAXBContext context = JAXBContext.newInstance("es.um.acta");
				Unmarshaller unmarshaller = context.createUnmarshaller();
				System.setProperty("javax.xml.accessExternalDTD", "all");
				Acta acta = (Acta) unmarshaller.unmarshal(new File("src/main/resources/xml/acta.xml"));
				int total = 0;
				for (TipoDiligencia diligencia : acta.getDiligencia()) {
					
					Date fecha = diligencia.getFecha().toGregorianCalendar().getTime();
					if (fecha.after(Date.from(
							LocalDate.now().minusDays(30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
						total++;
					}
				}
				System.out.println("Diligencias encontradas en el ultimo mes: "+total);
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		}

}
