package entities;

public class CarMethodReference {
	
	public int ordenarPorCor(Car car, Car otherCar) {
		return car.getColor().compareTo(otherCar.getColor());
	}
	
	public static int ordenarPorNome(Car car, Car otherCar) {
		return car.getModel().compareTo(otherCar.getModel());
	}
}
