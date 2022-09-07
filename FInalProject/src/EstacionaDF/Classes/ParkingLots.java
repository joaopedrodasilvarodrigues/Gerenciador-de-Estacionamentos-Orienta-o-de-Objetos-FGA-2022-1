package EstacionaDF.Classes;

import java.time.*;
 
public class ParkingLots {
	private String identifier; 
	private LocalTime opening, closure, nightDailyStartsAt, nightDailyEndsAt;
	private short capacity;
	private float priceFraction, discountHour, priceDiurnal, taxNocturnal, valuePerEvent, valuePerMonthlyPayer, contractorsTaxReturn;
	
	public ParkingLots(String identifier, LocalTime opening, LocalTime closure, LocalTime nightDailyStartsAt, LocalTime nightDailyEndsAt, short capacity, float priceFraction, float discountHour, float priceDiurnal, float taxNocturnal, float  valuePerEvent, float valuePerMonthlyPayer, float contractorsTaxReturn) {
		this.identifier = identifier;
		this.opening = opening;
		this.closure = closure;
		this.nightDailyStartsAt = nightDailyStartsAt;
		this.nightDailyEndsAt = nightDailyEndsAt;
		this.capacity = capacity;
		this.priceFraction = priceFraction;
		this.discountHour = discountHour;
		this.priceDiurnal = priceDiurnal;
		this.taxNocturnal = taxNocturnal;
		this.valuePerEvent = valuePerEvent;
		this.valuePerMonthlyPayer = valuePerMonthlyPayer;
		this.contractorsTaxReturn = contractorsTaxReturn;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public LocalTime getOpening() {
		return opening;
	}
	
	public LocalTime getlCosure() {
		return closure;
	}
	
	public LocalTime getNightDailyStartsAt() {
		return nightDailyStartsAt;
	}
	public LocalTime getNightDailyEndsAt() {
		return nightDailyEndsAt;
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
	public float getValuePerEvent() {
		return valuePerEvent;
	}
	public float getValuePerMonthlyPayer() {
		return valuePerMonthlyPayer;
	}
	public float getContractorsTaxReturn() {
		return contractorsTaxReturn;
	}
}
