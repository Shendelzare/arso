package com.arso.semana4;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.example.poema.Poema;

public class GenerarPoemaJAXB {
	public static void main(String[] args) {

		try {

			JAXBContext context = JAXBContext.newInstance(Poema.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);

			Poema salida = new Poema();
			salida.setFecha("Abril de 1915");
			salida.setLugar("Granada");
			salida.setTitulo("Alba");
			salida.getVerso().add("Mi corazón oprimido");
			salida.getVerso().add("siente junto a la alborada");
			salida.getVerso().add("el dolor de sus amores");
			salida.getVerso().add("y el sueño de las distancias.");

			marshaller.marshal(salida, new File("src/main/resources/xml/poema.xml"));

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
