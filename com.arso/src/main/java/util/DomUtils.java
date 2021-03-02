package util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DomUtils {

public static Document construyeAnalizador(String nombreXml) {
		
		if(null!=nombreXml && !"".equals(nombreXml)) {
			
		
			// 1. Obtener una factoría
			DocumentBuilderFactory factoria =
			DocumentBuilderFactory.newInstance();
			// 2. Pedir a la factoría la construcción del analizador
			DocumentBuilder analizador;
			Document documento=null;
			try {
				analizador = factoria.newDocumentBuilder();
				// 3. Analizar el documento
		
				documento = analizador.parse(nombreXml);
				
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return documento;
		}else {
			throw new IllegalArgumentException("Documento xml no informado");
		}
	}	

public static void construyeTransformador(Document documento, String nombreFicheroXmlModificado) {
	
	
		
	try {
		// 1. Construye la factoría de transformación y obtiene un
		// transformador
		TransformerFactory tFactoria = TransformerFactory.newInstance();
		Transformer transformacion = tFactoria.newTransformer();
		// 2. Establece la entrada, como un árbol DOM
		Source input = new DOMSource(documento);
		// 3. Establece la salida, un fichero en disco
		Result output = new StreamResult(nombreFicheroXmlModificado);
		// 4. Aplica la transformación
		transformacion.transform(input, output);
		
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	

