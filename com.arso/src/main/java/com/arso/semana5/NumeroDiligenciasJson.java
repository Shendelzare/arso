package com.arso.semana5;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import util.Constantes;

public class NumeroDiligenciasJson {

	private static final String ELEMENTO_DILIGENCIA = "diligencia";
	private static final String ELEMENTO_FECHA = "fecha";

	public static void main(String[] args) {
		JsonReader jsonReader = Json.createReader( new InputStreamReader(NotaMediaJson.class.getClassLoader().getResourceAsStream(Constantes.RUTA_JSON)));
		JsonObject obj = jsonReader.readObject();
		JsonObject acta = obj.get(Constantes.ELEMENTO_ACTA).asJsonObject();
		JsonArray diligencias = acta.getJsonArray(ELEMENTO_DILIGENCIA);

		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		int total = 0;
		try {
			for (JsonObject estaDiligencia : diligencias.getValuesAs(JsonObject.class)) {

				Date fecha;

				fecha = sdf.parse(estaDiligencia.getString(ELEMENTO_FECHA));


				if (fecha.after(Date.from(
						LocalDate.now().minusDays(30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))) {
					total++;
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Diligencias encontradas en el ultimo mes: "+total);
	}

}
