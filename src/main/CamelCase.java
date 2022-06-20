package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CaracteresException;
import exceptions.IniciaPorNumerosException;

public class CamelCase {
private static final String REGEX_MAIUSCULAS = "[A-Z]";
private static final String REGEX_MINUSCULAS_MINUSCULAS = "(?<!(^|[A-Z0-9]))(?=[A-Z0-9])|(?<!(^|[^A-Z]))(?=[0-9])|(?<!(^|[^0-9]))(?=[A-Za-z])|(?<!^)(?=[A-Z][a-z])";

public static List<String> converterCamelCase(String original){
	caracteresEspeciais(original);
	iniciaPorNumero(original);
	palavraUnicaMinuscula(original);
	separaNomeComposto(original);
	textoEmMaiusculo(original);
	separaMaiusculasDeMinusculas(original);
	separaFraseMaiusculasDeMinusculasENumeros(original);
	List<String> list = new ArrayList<>();
	list.add(original);
	return list;
	}
	
public static String palavraUnicaMinuscula(String texto) {
	for (char c : texto.toCharArray()) {
		if (Character.isUpperCase(c)) {
			return texto.toLowerCase();
		}
	}
	return texto;
}
	
public static List<String> separaNomeComposto(String texto) {
	String[] words = texto.split(REGEX_MINUSCULAS_MINUSCULAS);
	for (int i = 0; i < words.length; i++) {
		words[i] = palavraUnicaMinuscula(words[i]);
	}
	List<String> list = Arrays.asList(words);
	return list;
}

public static String textoEmMaiusculo(String texto) {
	Pattern pattern = Pattern.compile(REGEX_MAIUSCULAS);
	Matcher matcher = pattern.matcher(texto);
	if((matcher.find()) && (matcher.regionEnd() == texto.length()) ) {
		return texto;
	}
	return texto;	
}

public static List<String> separaMaiusculasDeMinusculas(String texto) {
	String letraMaiusculas = "";
	String letrasMinusculas = "";
	List<String> list= new ArrayList<>();
	for (char c : texto.toCharArray()) {
		if (Character.isUpperCase(c)) letraMaiusculas += String.valueOf(c);
		else if(Character.isLowerCase(c)) letrasMinusculas += String.valueOf(c);
	}
	list.addAll(Arrays.asList(letrasMinusculas, letraMaiusculas));
	return list;
}

public static List<String> separaFraseMaiusculasDeMinusculasENumeros(String texto) {
	String[] words = texto.split(REGEX_MINUSCULAS_MINUSCULAS);
	for (int i = 0; i < words.length; i++) {
		words[i] = words[i];
	}
	List<String> list = Arrays.asList(words);
	return list;
	
}
	
public static String caracteresEspeciais(String texto) {
	for (char c : texto.toCharArray()) {
		if (!Character.isDigit(c) && (!Character.isLetter(c))) {
			throw new CaracteresException("caracteres não são permitidos");
		}
	}
	return texto;
}

public static String iniciaPorNumero(String texto) {
	char caracter = texto.charAt(0);
	if (Character.isDigit(caracter)) {
		throw new IniciaPorNumerosException("Não pode iniciar por numeros");
	}
	return texto;
}



	
}
