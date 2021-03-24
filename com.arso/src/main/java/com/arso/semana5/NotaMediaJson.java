package com.arso.semana5;

import java.io.InputStreamReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import util.Constantes;

public class NotaMediaJson {

	private final static String ELEMENTO_CALIFICACION = "calificacion";
	private final static String ELEMENTO_NOTA = "nota";
	private static double sumaNotas = 0;

	
	public static void main(String[] args) {
		JsonReader jsonReader = Json.createReader( new InputStreamReader(NotaMediaJson.class.getClassLoader().getResourceAsStream(Constantes.RUTA_JSON)));
		JsonObject obj = jsonReader.readObject();
		JsonObject acta = obj.get(Constantes.ELEMENTO_ACTA).asJsonObject();
		JsonArray calificaciones = acta.getJsonArray(ELEMENTO_CALIFICACION);

		Double notaMedia = calculaNotas(calificaciones);
		System.out.println("Nota media final: "+notaMedia);
		
		 

	}


	private static Double calculaNotas(JsonArray calificaciones) {
        for (JsonObject calificacionActual: calificaciones.getValuesAs(JsonObject.class)) {
        	sumaNotas+=calificacionActual.getJsonNumber(ELEMENTO_NOTA).doubleValue();
        }
        
        if (calificaciones.size() > 0) {
        	sumaNotas= sumaNotas / calificaciones.size();
        } else {
        	sumaNotas= 0;        
        }
        
		return sumaNotas;
	}

}
