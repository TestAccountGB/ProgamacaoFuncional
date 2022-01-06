package explanation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import entities.Car;

public class FilterSupremo {
	public static void main(String[] args) {

		Car corsa = new Car("Corsa", Car.Color.BLACK, 2010);
		Car celta = new Car("Celta", Car.Color.BLACK, 2008);
		Car fusca = new Car("Fusca", Car.Color.BLUE, 2004);

		List<Car> cars = Arrays.asList(corsa, celta, fusca);

		List<Car> carrosPretos = filterListSupremoPredicate(cars, (Car car) -> car.getColor() == Car.Color.BLACK);
		System.out.println(carrosPretos);

		List<Car.Color> coresCarros = filterListSupremoFunction(cars, (Car car) -> car.getColor());
		System.out.println(coresCarros);
	}

	private static <T, R> List<R> filterListSupremoFunction(List<T> list, Function<T, R> function) {
		List<R> result = new ArrayList<>();

		for (T e : list) {
			result.add(function.apply(e));
		}
		return result;
	}

	private static <T> List<T> filterListSupremoPredicate(List<T> list, Predicate<T> predicate) {
		//Esse aqui e basicamente o filter das Stream
		List<T> result = new ArrayList<>();
		for (T e : list) {
			if (predicate.test(e)) {
				result.add(e);
			}
		}
		return result;
	}
}
