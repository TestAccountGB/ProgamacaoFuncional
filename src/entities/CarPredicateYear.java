package entities;

public class CarPredicateYear implements Car.CarPredicate {
	
	private int year;

	public CarPredicateYear(int year) {
		this.year = year;
	}

	@Override
	public boolean test(Car car) {
		return car.getYear() == this.year;
	}
}
