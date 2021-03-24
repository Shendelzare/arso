package com.arso.semana5;

import java.io.InputStreamReader;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbException;
import javax.json.bind.spi.JsonbProvider;

import com.arso.semana5.model.ActaVO;

import util.Constantes;


public class CrearActaJson {

	public static void main(String[] args) {
		Jsonb contexto = JsonbProvider.provider().create().build();
		ActaVO nuevaActa = null;
		try {
			nuevaActa = contexto.fromJson( new InputStreamReader(NotaMediaJson.class.getClassLoader().getResourceAsStream(Constantes.RUTA_JSON)), ActaVO.class);
		} catch (JsonbException e) {
			e.printStackTrace();
		}
		
		System.out.println(contexto.toJson(nuevaActa));

	}

}
