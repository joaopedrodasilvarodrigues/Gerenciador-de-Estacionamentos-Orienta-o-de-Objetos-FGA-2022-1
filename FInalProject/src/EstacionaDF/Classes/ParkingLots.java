package EstacionaDF.Classes;

public class ParkingLots {

	private String identifier, opening, closure;
	private short capacity;
	private float priceFraction, discountHour, priceDiurnal, taxNocturnal;
	
	public ParkingLots(String identifier, String opening, String closure, short capacity, float priceFraction, float discountHour, float priceDiurnal, float taxNocturnal) {
		this.identifier = identifier;
		this.opening = opening;
		this.closure = closure;
		this.capacity = capacity;
		this.priceFraction = priceFraction;
		this.discountHour = discountHour;
		this.priceDiurnal = priceDiurnal;
		this.taxNocturnal = taxNocturnal;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getOpening() {
		return opening;
	}
	
	public String getlCosure() {
		return closure;
	}
	
	public short getCapacity() {
		return capacity;
	}
	
	public float getPriceFraction() {
		return priceFraction;
	}
	
	public float getDiscountHour() {
		return discountHour;
	}
	
	public float getPriceDiurnal() {
		return priceDiurnal;
	}
	
	public float getTaxNocturnal() {
		return taxNocturnal;
	}
}
