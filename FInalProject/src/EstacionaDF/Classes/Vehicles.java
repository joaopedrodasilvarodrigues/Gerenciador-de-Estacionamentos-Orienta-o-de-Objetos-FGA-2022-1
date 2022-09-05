package EstacionaDF.Classes;

public class Vehicles {

	private String driver, plate, entrance, exit;
	private boolean monthly;
	
	public Vehicles(String driver, String plate, String entrance, String exit, boolean monthly) {
		this.driver = driver;
		this.plate = plate;
		this.entrance = entrance;
		this.exit = exit;
		this.monthly = monthly;
	}
	
	public String getDriver() {
		return driver;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public String getEntrance() {
		return entrance;
	}
	
	public String getExit() {
		return exit;
	}
	
	public boolean getMonthly() {
		return monthly;
	}
}
