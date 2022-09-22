package EstacionaDF.Classes;

import EstacionaDF.FileManager.CSVManager;

public class Vehicles {

	private String plate, entrance, exit;
	private boolean monthly;
	private final static String[] VehicleTableCategories = {"Plates", "Entrance", "Mensalist"};
	
	public Vehicles(String plate, String entrance, String exit, boolean monthly) {
		this.plate = plate;
		this.entrance = entrance;
		this.exit = exit;
		this.monthly = monthly;

	}
	public Vehicles(String plates){
		this.entrance = entrance;
		this.monthly = monthly;
	}
	public static CSVManager platesTable() throws Exception {
		return new CSVManager("plates", Vehicles.VehicleTableCategories);
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
