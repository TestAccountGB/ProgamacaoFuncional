package explanation;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import entities.Car;
import entities.CarMethodReference;
import entities.CarTest;

public class MethodReferences {
	public static void main(String[] args) {

		// O que e method references? E basicamente um jeito de "simplificar" uma lambda e tambem conseguir referenciar
		//Um metodo. Primeiramente vamos falar dos 4 tipos de Method References que existem
		
		//1 - Reference to a static method

		Car corsa = new Car("Corsa", Car.Color.BLACK, 2010);
		Car celta = new Car("Celta", Car.Color.BLACK, 2008);
		Car fusca = new Car("Fusca", Car.Color.BLUE, 2004);
		List<Car> cars = Arrays.asList(fusca, celta, corsa);
		
		System.out.println("Antes");
		System.out.println(cars);
		cars.sort(CarMethodReference::ordenarPorNome);
		System.out.println();
		System.out.println("Depois");
		System.out.println(cars);
		
		//Como isso funcionou? Porque quando usamos o "::" ele esta vendo se o metodo "ordenarPorNome" da classe
		//"CarMethodReference" pode ser transformada em lambda de um Comparator. Mas qual e uma lambda de uma
		//Interface Comparator?
		
//		cars.sort(new Comparator<Car>() {
//			@Override
//			public int compare(Car car, Car otherCar) {
//				return car.getModel().compareTo(otherCar.getModel());
//			}
//		});
		
		//Em lambda: 
		//cars.sort((Car car, Car otherCar) -> car.getModel().compareTo(otherCar.getModel()));
		
		//Quando a gente usa um sort de uma List de "Car", logo o compare dela vai pedir dois objetos "Car", e como regra
		//Todo metodo "compare" tem que retornar um int, entao quando a gente chama:
		//CarMethodReference::ordenarPorNome, ele vai ver se esse metodo e compativo
		//Metodo: public static int ordenarPorNome(Car car, Car otherCar); Como podemos ver ele retorna um int e tem
		//Dois parametros objeto da classe "Car", ou seja, e compativo.
		
		//2 - Reference to an instance method of a particular object
		//Basicamente igual ao primeiro so que dessa vez o mesmo nao precisa ser estatico pois e alcancado atraves de um
		//Objeto
		
		CarMethodReference methodReference = new CarMethodReference();
		cars.sort(methodReference::ordenarPorCor);
		
		//3 - Reference to an instance method of an arbitrary object of a particular type
		//Esse e um pouco mais complicado, quando a gente usa o metodo de uma classe wrapper, como o comparaTo do
		//Integer e usamos ele na sua forma padrao: objeto1.compareTo(objeto2). Podemos fazer o uso do MethodReference
		//Assim:
		
		List<Integer> numeros = Arrays.asList(1, 4, 3, 2, 5, 6, 7, 8, 9, 10);
		
		numeros.sort((Integer o1, Integer o2) -> o1.compareTo(o2));
		//Esse compareTo que a gente fez e exatamente igual ao compare padrao do Integer, ou seja, podemos fazer isso:
		numeros.sort(Integer::compareTo);
		//Mas caso a gente queira algo diferente, como o2.compareTo(o1), nao podemos usar o MethodReference, pois
		//Assim nao esta na forma padrao, esta inverso!
		System.out.println(numeros);
		
		//Mesma coisa com String
		List<String> string = Arrays.asList("Ola", "Salve", "Hello");
		string.sort(String::compareTo);
		System.out.println(string);
		
		//E funciona basicamente com qualquer metodo que usamos em sua forma padrao
		
		@SuppressWarnings("unused")
		Function<String, Integer> function = (String s) -> Integer.parseInt(s);
		//Como voce sabe, a interface Function em seus diamantes o primeiro significa o tipo do parametro
		//E o segundo o tipo do retorno
		
		//Usando lambda desse jeito esta na forma padrao, podemos fazer isso:
		function = Integer::parseInt;
		
		@SuppressWarnings("unused")
		BiPredicate<List<String>, String> predicate = (List<String> list, String s) -> list.contains(s);
		//Assim tambem esta na forma padrao
		//Basicamente o padrao e apenas um metodo. Que esta assim:
		//Se tem dois parametros:
		//primeiroParametro.metodo(segundoParametro);
		//Se tem apenas um parametro:
		//classe.metodoEstatico(primeiroParametro);
		
		predicate = List::contains;
		
		//4 - Reference to a constructor
		//Quando e um construtor sem parametros podemos fazer isso:
		@SuppressWarnings("unused")
		CarTest<Car> test = () -> new Car();
		@SuppressWarnings("unused")
		CarTest<Car> test2 = Car::new;
		
		//Quando temo parametros no construtor:
		@SuppressWarnings("unused")
		BiFunction<String, Integer, Car> carroBiFunction = (String s, Integer i) -> new Car(s, i);
		//COmo podemos ver, isso e um construtor padrao, logo:
		BiFunction<String, Integer, Car> carroBiFunction2 = Car::new;
		//E basicamente como se ele estivisse injetando os parametros no construto
		
		//Obs.: Os objetos nao foram instanciados, e so quando a gente chama o metodo da interface funcional que eles sao
		//Instaciados:
		System.out.println(carroBiFunction2.apply("Corsa", 2015));
		
		//Obs.: O uso do MethodReference e apenas para substituir as lambdas quando suas implementacoes sao padrao
	}
}
