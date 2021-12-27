package entities;

public class Car {

	private String model;
	private Color color;
	private int year;

	public Car() {
	}

	public Car(String model, int year) {
		this.model = model;
		this.year = year;
	}

	public Car(String model, Color color, int year) {
		this.model = model;
		this.color = color;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Model: " + model + ", Color: " + color + ", Year: " + year;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public enum Color {
		BLACK, WHITE, GREEN, BLUE;
	}

	@FunctionalInterface
	public interface CarPredicate {
		boolean test(Car car);
	}
}
