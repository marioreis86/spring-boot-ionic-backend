package com.mariopimenta.cursomc.enums;

import java.util.function.BiFunction;

public class Validacoes {
	
	public static final String REQUIRED = "Preenchimento Obrigatório";
	public static final BiFunction<Integer, Integer, String> SIZE_BETWEEN = (min, max) -> String
			.format("O tamanho deve ser entre %d e %d caracteres", min, max);
}
