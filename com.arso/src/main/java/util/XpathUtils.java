package util;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XpathUtils {


	public static NodeList realizaConsultaXpath(String expresion, Document documento) {
		// 1. Obtener la factoría
		XPathFactory factoria = XPathFactory.newInstance();
		// 2. Construir el evaluador XPath
		XPath xpath = factoria.newXPath();
		// 3. Realizar una consulta
		try {
			XPathExpression consulta = xpath.compile(expresion);
		
			return (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			System.out.println("Ocurrio un error durante la evaluacion de la expresionXPATH : "+expresion);			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
