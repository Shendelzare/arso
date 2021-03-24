package com.arso.semana5;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import util.DomUtils;
import util.XpathUtils;

public class ConsultaXPath {

	private static final String RUTA_BOOKLE = "src/main/resources/Bookle.xml";

	private static final String EXPRESION_FECHA = "//fecha";
	private static final String EXPRESION_HORARIO_TURNOS = "//horario";
	private static final String EXPRESION_EMAIL_RESERVA = "//email";
	private static final String EXPRESION_FECHA_MES = "//fecha[contains(text(),'-04-')]";
	private static final String EXPRESION_PRIMER_TURNO = "//turno[1]";
	private static final String EXPRESION_ULTIMO_TURNO = "//turno[last()]";

	public static void main(String[] args) {


		Document documento = DomUtils.construyeAnalizador(RUTA_BOOKLE);

		NodeList fechas = XpathUtils.realizaConsultaXpath(EXPRESION_FECHA, documento);
		List<String> fechasConsultadas = recuperaListaString(fechas);
		fechasConsultadas.forEach(c -> System.out.println("Fecha actual: "+c));
		
		NodeList horarioTurnos =  XpathUtils.realizaConsultaXpath(EXPRESION_HORARIO_TURNOS, documento);
		List<String> turnosConsultados = recuperaListaString(horarioTurnos);
		turnosConsultados.forEach(c -> System.out.println("Turnos obtenidos: "+c));

		NodeList reservas =  XpathUtils.realizaConsultaXpath(EXPRESION_EMAIL_RESERVA, documento);
		List<String> emailsReserva = recuperaListaString(reservas);
		emailsReserva.forEach(c -> System.out.println("Email recuperado: "+c));

		NodeList fechasMes =  XpathUtils.realizaConsultaXpath(EXPRESION_FECHA_MES, documento);
		List<String> fechasMesResultado = recuperaListaString(fechasMes);
		fechasMesResultado.forEach(c -> System.out.println("Fechas para el mes 4: "+c));
		
		NodeList primerTurno =  XpathUtils.realizaConsultaXpath(EXPRESION_PRIMER_TURNO, documento);
		System.out.println("Primer turno: "+primerTurno.item(0).getTextContent());
		
		NodeList ultimoTurno =  XpathUtils.realizaConsultaXpath(EXPRESION_ULTIMO_TURNO, documento);
		System.out.println("Ultimo turno:"+ultimoTurno.item(0).getTextContent());
	}
	
	
	private static List<String> recuperaListaString(NodeList nodelist) {
		List<String> strings = new ArrayList<String>();
		
		for (int i = 0; i < nodelist.getLength(); i++) {
			strings.add(nodelist.item(i).getTextContent());
		}
		return strings;
		
	}
	

}
