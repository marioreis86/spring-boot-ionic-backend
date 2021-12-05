package com.mariopimenta.cursomc.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.ExpressionException;
import org.springframework.test.context.ContextConfiguration;

import com.mariopimenta.cursomc.CursomcApplication;

@SpringBootTest(classes = CursomcApplication.class)
class CursomcApplicationTests {

	@Test
	void contextLoads() {
		
		assertThat("Mario")
			.hasSize(5)
			.startsWith("M")
			.endsWith("o")
			.as("Testando se meu nome está correto");
		
		assertThatThrownBy(() ->{ throw new ExpressionException("A expressão estava incorreta"); })
				.hasMessage("A expressão estava incorreta")
				.as("Verificando mensagem de erro");
	}

}
