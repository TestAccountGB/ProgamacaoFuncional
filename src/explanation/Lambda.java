package explanation;

import entities.Car;

public class Lambda {
	public static void main(String[] args) {
		
		//O que e lambda? Bom, e um maneira de simplificar quando a gente quer passar um comportamento dentro de
		//Um metedo atraves de uma interface funcional (@FunctionalInterface), mas o que e um comportamento ou uma
		//interface funcional?
		
		//Interfaces funcionais sao interfaces que apenas tem UM metodo abstrato, mesmo se uma interface tem um
		//Metodo abstrato e outro metodo default, ela e considerada uma Inteface funcional
		
		//Comportamentos sao basicamente o corpo do metodo/funcao, pois classes anonimas e lambdas tem funcoes e
		//Nao metodos, porque elas nao estao diretamente ligadas a uma classe normal
		
		//Agora vou falar o porque das lambadas terem sido criadas, que foi basicamente criada para substituir as classes
		//As classes anonimas quando a mesma so tem um metodo, porque assim fica muito verboso (Quando tem muitas
		//Palavras para fazer tal coisa), observe:
		Car corsa = new Car("Corsinha", Car.Color.GREEN, 2014);
		Car.CarPredicate carPredicate = new Car.CarPredicate() {//Classe anonima
			@Override
			public boolean test(Car car) {
				return car.getYear() == 2014;
			}
		};
		
		System.out.println(carPredicate.test(corsa));
		
		//Como podemos ver aqui, tivemos que fazer a classe anonima apenas para sobreescrever UM metodo, que e o
		//Cenario perfeito para se usar lambda

		Car.CarPredicate carPredicateLambda = car -> car.getYear() == 2014;
		//Car.CarPredicate carPredicateLambda2 = car -> {return car.getYear() == 2014;};
		//Podemos dar um return explicito como esta acima, mas nao e necessario.
		
		System.out.println(carPredicateLambda.test(corsa));
		
		//Mesmo resultado. Mas agora vamos entender o corpo de uma lambda
		
		//Antes da setinha (->), temos os parametros, isso mesmo, PARAMETROS, para ficar mais visivel podemos colocar
		//Assim...
		//(Car car) -> ...
		//Mas e opcional, por isso apenas colocamos "car", depois da setinha temos o "corpo" de um metodo, sim, um
		//Metodo, podemos colocar uma logica gigante dentro dele como um metodo normal, por exemplo:
		
		LambdaInterface<String, Integer> lambda =  (String s, Integer i) -> s + ", tem " + i + " anos";
		//Como disse, colocar o tipo dos parametros e opcinal, podemos apenas colocar assim:  (s, i) -> ...;
		
		System.out.println(lambda.test("Arthur", 20));
		
		//Para provar que realmente lambda e apenas um metodo normal, vou fazer um metodo que retorna void
		LambdaVoid lambdaVoid = () -> System.out.println("Dentro de um metodo void e sem parametro :0");
		
		lambdaVoid.test();
		//Imagine lambda como apenas uma sobreescrita de um metodo que fica mais facil, mas na verdade ela e chamada
		//De funcao, e nao metodo :>
		//Obs.: Lambda so funciona em Interface Funcionais
		
		
	}
	
}

@FunctionalInterface
interface LambdaInterface<T, Y> {
	String test(T t, Y y);
}

@FunctionalInterface
interface LambdaVoid {
	void test();
}
