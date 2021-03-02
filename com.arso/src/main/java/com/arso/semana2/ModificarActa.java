package com.arso.semana2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.DomUtils;

public class ModificarActa {
	private final static String ELEMENTO_CALIFICACION = "calificacion";
	private final static String ELEMENTO_NOTA = "nota";
	
	public static void main(String[] args) {
		Document documento = DomUtils.construyeAnalizador("src/main/resources/acta.xml");
		aumentaNota(documento);
		DomUtils.construyeTransformador(documento, "src/main/resources/actaModificado.xml");
	}

	private static void aumentaNota(Document documento) {
		NodeList listaCalificaciones = documento.getElementsByTagName(ELEMENTO_CALIFICACION);
		for (int i = 0; i < listaCalificaciones.getLength() ; i++) {
			Element calificacion = (Element) listaCalificaciones.item(i);
			if(calificacion.getChildNodes()!=null) {
				NodeList hijosCalificacion = calificacion.getChildNodes();
				for (int j = 0; j < hijosCalificacion.getLength() ; j++) {
					Node hijoCalificacion = (Node) hijosCalificacion.item(j);
					if(hijoCalificacion.getNodeName().equals(ELEMENTO_NOTA)) {
						try {
							Double nota=Double.parseDouble(hijoCalificacion.getTextContent());
							System.out.println("nota sin modificar "+nota);
							if(nota<=9.5) {
								nota+=0.5;
								System.out.println("nota modificada "+nota);

							}
							
							hijoCalificacion.setTextContent(nota.toString());
						}catch(NumberFormatException nfe) {
							nfe.printStackTrace();
							
						}
					}
				}
			}
		}
		
	}
	
	
	

	

}
