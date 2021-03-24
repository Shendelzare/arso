package com.arso.semana5;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import util.DomUtils;
import util.XpathUtils;

public class ConsultaXPath {

	private static final String RUTA_BOOKLE = "src/main/resources/Bookle.xml";

	private static final String EXPRESION_FECHA = "//fecha";
	private static final String EXPRESION_HORARIO_TURNOS = "//fecha[text() = '2001-01-01']/..//horario";
	private static final String EXPRESION_EMAIL_RESERVA = "//reserva[@email]";
	private static final String EXPRESION_FECHA_MES = "//fecha[contains(text(),'-01-')]";
	private static final String EXPRESION_PRIMER_TURNO = "//fecha[text() = '2001-01-01']/..//horario";
	private static final String EXPRESION_ULTIMO_TURNO = "//fecha[text() = '2001-01-01']/..//horario";

	public static void main(String[] args) {


		Document documento = DomUtils.construyeAnalizador(RUTA_BOOKLE);

		NodeList fechas = XpathUtils.realizaConsultaXpath(EXPRESION_FECHA, documento);
		List<String> fechasConsultadas = consultaFechas(fechas);
		fechasConsultadas.forEach(c -> System.out.println("Fecha actual: "+c));
		
		NodeList horarioTurnos =  XpathUtils.realizaConsultaXpath(EXPRESION_HORARIO_TURNOS, documento);
		List<String> turnosConsultados = consultaHorarioTurnos(horarioTurnos);
		turnosConsultados.forEach(c -> System.out.println("Turnos obtenidos: "+c));

		NodeList reservas =  XpathUtils.realizaConsultaXpath(EXPRESION_EMAIL_RESERVA, documento);
		List<String> emailsReserva = consultaEmailReserva(reservas);
		emailsReserva.forEach(c -> System.out.println("Email recuperado: "+c));

		NodeList fechasMes =  XpathUtils.realizaConsultaXpath(EXPRESION_FECHA_MES, documento);
		List<String> fechasMesResultado = consultaFechaMes(fechasMes);
		fechasMesResultado.forEach(c -> System.out.println("Fecha actual: "+c));
	}
	
	private static List<String> consultaFechaMes(NodeList fechasMes) {
		List<String> fechas = new ArrayList<String>();
		
		for (int i = 0; i < fechasMes.getLength(); i++) {
			fechas.add(fechasMes.item(i).getTextContent());
		}
		return fechas;
	}
	
	private static List<String> consultaEmailReserva(NodeList reservas) {
		List<String> emails = new ArrayList<String>();
		
		for (int i = 0; i < reservas.getLength(); i++) {
			emails.add(reservas.item(i).getTextContent());
		}
		return emails;
	}
	
	
	private static List<String> consultaHorarioTurnoReservas(NodeList horarioTurnos) {
		List<String> turnos = new ArrayList<String>();
		
		for (int i = 0; i < horarioTurnos.getLength(); i++) {
			turnos.add(horarioTurnos.item(i).getTextContent());
		}
		return turnos;
	}
	
	
	private static List<String> consultaHorarioTurnos(NodeList horarioTurnos) {
		List<String> turnos = new ArrayList<String>();
		
		for (int i = 0; i < horarioTurnos.getLength(); i++) {
			turnos.add(horarioTurnos.item(i).getTextContent());
		}
		return turnos;
	}
	private static List<String> consultaFechas(NodeList fechas) {
		List<String> fechasConsultadas = new ArrayList<String>();
		
		for (int i = 0; i < fechas.getLength(); i++) {
			fechasConsultadas.add(fechas.item(i).getTextContent());
		}
		return fechasConsultadas;
		
	}

}
