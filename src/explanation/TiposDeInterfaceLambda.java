package explanation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class TiposDeInterfaceLambda {
	public static void main(String[] args) {
		
		//Como o java e incrivel nao precisamos criar interfaces apenas para fazer nossas lambdas, o java ja disponibiliza elas
		//E com mais alguns metodos adicionais, entre essas interfaces temos:
		
		//Predicate - Tipo mais simples que recebe um parametro generico e retorna um boolean, possivelmente a interface
		//Mais usada.
		System.out.println("---Predicate---");
		System.out.println();
		
		List<String> string = Arrays.asList("Hello", "Ola", "Oi");
		List<String> result = new ArrayList<>();
		Predicate<String> predicate = (String s) -> s.length() == 5;
		
		for(String s : string) {
			if(predicate.test(s)) {
				result.add(s);
			}
		}
		
		System.out.println(result);
		result.clear();
		
		//Metodos adicionais...
		Predicate<String> predicate2 = (String s) -> s.length() == 3;
		
		for(String s : string) {
			if(predicate.or(predicate2).test(s)) {
				result.add(s);
			}
		}
		
		System.out.println(result);
		result.clear();
		
		//Esse metodo or ele retorna um Predicate testando os dois tipos de Predicate com o simbolo ||, ou seja, podemos
		//Colocar uma variavel para guardar esse metodo
		
		@SuppressWarnings("unused")
		Predicate<String> predicate3 = predicate.or(predicate2);
		
		//E com esse metodos temos outros metodos que fazem os papeis dos simbolos de comparacao, como o &&, que e
		//O metodo "and"
		
		//|| = or
		//&& = and
		//! = negate
		
		//Consumer - Recebe um parametro e retorna void
		System.out.println();
		System.out.println("---Consumer---");
		System.out.println();
		
		Consumer<String> consumer = (String s) -> System.out.println(s.charAt(0));
		
		for(String s : string) {
			consumer.accept(s);
		}
		
		//Function - Recebe um parametro e retorna um tipo que a gente escolhe
		System.out.println();
		System.out.println("---Function---");
		System.out.println();
		
		Function<String, Integer> function = (String s) -> s.length();//Estou retornando o tamanho das Strings, que na
		//Verdade isso retorna um int, mas converte automaticamente para integer por causa do autoboxing
		//String como parametro e Integer vai ser o retorno
		
		List<Integer> tamanho = new ArrayList<>();
		for(String s : string) {
			tamanho.add(function.apply(s));
		}
		
		System.out.println(tamanho);
		
		//Classes bi
		//No java temos interfaces Bi dessas interfaces acima, ou seja, que recebem dois parametros.
		
		System.out.println();
		System.out.println("---BiTest---");
		System.out.println();
		
		BiFunction<String, Integer, Integer> biFunction = (String s, Integer i) -> s.length() * i;//Isso retorna um Integer
		//Os dois primeiros sao os parametros do metodo e o ultimo e o tipo do retorno
		
		for(String s : string) {
			System.out.println(biFunction.apply(s, 3));
		}
		
		//Tambem temos interfaces com tipo primitivos caso seu objetivo seja performance, pois assim nao ocorre o
		//Autoboxing e etc
		IntPredicate intPredicate = (int i) -> i > 10;
		
		System.out.println();
		System.out.println(intPredicate.test(13));
		
	}
}
