package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import exceptions.CaracteresException;
import exceptions.IniciaPorNumerosException;
import main.CamelCase;

class CamelCaseTest {

	@Test
	void VerificaUmaPalavra() {
		Assert.assertEquals("nome", CamelCase.palavraUnicaMinuscula("nome"));
		Assert.assertEquals("nome", CamelCase.palavraUnicaMinuscula("Nome"));
	}
	
	@Test
	void VerificaNomeCompostoEmCamelCase() {
		assertEquals(Arrays.asList("nome","composto"), CamelCase.separaNomeComposto("NomeComposto"));
		assertEquals(Arrays.asList("nome","composto"), CamelCase.separaNomeComposto("nomeComposto"));
	}
	
	@Test
	void VerificaTodosCaracteresMaiusculos() {
		assertEquals("CPF", CamelCase.textoEmMaiusculo("CPF"));
	}
	
	@Test
	void VarificaMaiusculasEMinusculas() {
		assertEquals(Arrays.asList("numero","CPF"), CamelCase.separaMaiusculasDeMinusculas("numeroCPF"));
	}
	
	@Test
	void VerificaMaisDeDuasPalavrasMaiusculasEMinusculasENumeros() {
		assertEquals(Arrays.asList("numero","CPF","Contribuinte"), CamelCase.separaFraseMaiusculasDeMinusculasENumeros("numeroCPFContribuinte"));
		assertEquals(Arrays.asList("recupera","10","primeiros"), CamelCase.separaFraseMaiusculasDeMinusculasENumeros("recupera10primeiros"));
	}

	
	@Test
	void TextoDigitadoTemCaracteresEspeciais() {
		assertThrows(CaracteresException.class, ()->{
			CamelCase.caracteresEspeciais("Mai@ntre");
		});
	}

	@Test
	void TextoDigitadoIniciaPorNumero() {
		assertThrows(IniciaPorNumerosException.class, ()->{
			CamelCase.iniciaPorNumero("10Numero");
		});
	}


	
}
