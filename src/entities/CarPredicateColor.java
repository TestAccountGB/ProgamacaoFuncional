package entities;

public class CarPredicateColor implements Car.CarPredicate {
	
	private Car.Color color;
	
	public CarPredicateColor(Car.Color color) {
		this.color = color;
	}
	
	
	@Override
	public boolean test(Car car) {
		return car.getColor() == this.color;
	}

}
