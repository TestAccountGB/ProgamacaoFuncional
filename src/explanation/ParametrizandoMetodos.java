package explanation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import entities.Car;
import entities.CarPredicateColor;
import entities.CarPredicateYear;

public class ParametrizandoMetodos {
	
	private static List<Car> filterByColor(List<Car> carList, Car.Color color) {
		List<Car> result = new ArrayList<>();
		
		for(Car car : carList) {
			if(car.getColor() == color) {
				result.add(car);
			}
		}
		return result;
	}
	
	private static List<Car> filterByYear(List<Car> carList, int year) {
		List<Car> result = new ArrayList<>();
		
		for(Car car : carList) {
			if(car.getYear() == year) {
				result.add(car);
			}
		}
		return result;
	}
	
	private static List<Car> filterCar(List<Car> carList, Car.CarPredicate predicate) {
		List<Car> result = new ArrayList<>();
		
		for(Car car : carList) {
			if(predicate.test(car)) {
				result.add(car);
			}
		}
		return result;
	}
	
	private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		//O que e a interface Predicate? Ela e basicamente nosso Car.CarPredicate so que ela usa generics e tem mais
		//Alguns metodos interessantes, se voce entrar nela vai achar o metodo "test" que serve para o mesmo proprosito
		//Que a nossa interface
		List<T> result = new ArrayList<>();
		
		for(T type : list) {
			if(predicate.test(type)) {
				result.add(type);
			}
		}
		return result;
		//Basicamente com esse metodo totalmente generico vou poder fazer qualquer verificacao com listas
	}
	
	public static void main(String[] args) {
		//Ja se perguntou a importancia de fazer um metodo flexivel e o mais generico possivel? Pse, imagine que o cliente
		//Quer separar um carro pela sua cor e iremos fazer um metodo para isso...
		
		List<Car> cars = Arrays.asList(new Car("Corsa", Car.Color.BLACK, 2014), new Car("Fusca", Car.Color.BLUE, 1997));
		System.out.println(filterByColor(cars, Car.Color.BLACK));
		
		//Como podemos ver funcionou, mas se agora o cliente pedir um jeito de filtrar por ano?
		//Iriamos fazer outro metodo...
		
		System.out.println(filterByYear(cars, 1997));
		
		//E assim por diante, iriamos ter mil metodos que fazem quase a mesma coisa, nao tem um jeito melhor
		//Melhor de fazer isso?
		
		System.out.println("----------------------------------------------------");
		
		System.out.println(filterCar(cars, new CarPredicateColor(Car.Color.BLUE)));
		System.out.println(filterCar(cars, new CarPredicateYear(2014)));
		
		//Bacana ne? Conseguimos "melhorar" nosso metodo, agora podemos usar o mesmo metodo para muitas coisas.
		//Mas po, criar uma classe para cada verificacao que a gente quer fazer ainda sim vai ficar poluido, como
		//Melhoramos isso?
		
		//Podemos usar uma classe anonima...
		System.out.println("----------------------------------------------------");
		
		System.out.println(filterCar(cars, new Car.CarPredicate() {
			@Override
			public boolean test(Car car) {
				return car.getColor() == Car.Color.BLACK;
			}
		}));
		
		//Mas ainda ta muito poluido e com coisas desnecessarias, como melhoramos isso? Com lambda...
		System.out.println("----------------------------------------------------");
		
		System.out.println(filterCar(cars, car -> car.getColor() == Car.Color.BLACK));
		//O que esta acontecendo aqui? Olhe a classe "Lambda" e descubra :0
		
		//MAASS, ainda tem como melhorar esse metodo, fazendo um metodo ainda mais generico que vai funcionar com
		//MUITA COISA.
		System.out.println("----------------------------------------------------");
		
		System.out.println(filter(cars, new Predicate<Car>() {//Aqui passamos o tipo dentro de Predicate
			@Override
			public boolean test(Car car) {
				//E o tipo que passamos no diamante do Predicate acima usamos na sobrecarga do metodo, e no metodo
				//Podemos fazer o que quiser
				return car.getYear() == 2014;
			}
		}));
		
		//Podemos usar para outros propositos...
		System.out.println("----------------------------------------------------");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		System.out.println(filter(numbers, new Predicate<Integer>() {
			@Override
			public boolean test(Integer number) {
				return number % 2 == 0;
				//So vai retornar true, se o numero for par
			}
		}));
		
		//E podemos fazer tudo isso em lambda...
		System.out.println("----------------------------------------------------");
		System.out.println(filter(numbers, (Integer number) -> number % 2 == 0));
		
		//E agora com apenas um metodo e sem precisar criar classes/interfaces vou conseguir fazer basicamente qualquer
		//Tipo de verificacao usando Listas :)
		
		//Com o poder dos generics e lambda unidos, nada me parara :>
	}
}
