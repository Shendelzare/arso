package com.arso.semana2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.DomUtils;

public class NotaMedia {
	
	private final static String ELEMENTO_CALIFICACION = "calificacion";
	private final static String ELEMENTO_NOTA = "nota";
	private static double sumaNotas = 0;
	private static int numeroCalificaciones;
	public static void main(String[] args) {
		Document documento = DomUtils.construyeAnalizador("src/main/resources/acta.xml");
		
		Double notaMedia = calculaNotas(documento);
		System.out.println("Nota media final: "+notaMedia);
	}
	
	
	private static Double calculaNotas(Document documento) {
		NodeList listaCalificaciones = documento.getElementsByTagName(ELEMENTO_CALIFICACION);
		for (int i = 0; i < listaCalificaciones.getLength() ; i++) {
			Element calificacion = (Element) listaCalificaciones.item(i);
			numeroCalificaciones=i+1;
			if(calificacion.getChildNodes()!=null) {
				NodeList hijosCalificacion = calificacion.getChildNodes();
				for (int j = 0; j < hijosCalificacion.getLength() ; j++) {
					Node hijoCalificacion = (Node) hijosCalificacion.item(j);
					if(hijoCalificacion.getNodeName().equals(ELEMENTO_NOTA)) {
						try {
							sumaNotas+=Double.parseDouble(hijoCalificacion.getTextContent());
						}catch(NumberFormatException nfe) {
							nfe.printStackTrace();
							
						}
					}
				}
			}
		}
		

		if(sumaNotas>0) {
			return sumaNotas/numeroCalificaciones;
		}else{
			return sumaNotas;
		}
	}


	
}
