package EstacionaDF.Classes;

import java.time.*;

import EstacionaDF.EstacionaExceptions.CSVManagerExceptions;
import EstacionaDF.EstacionaExceptions.InvalidValueException;
import EstacionaDF.FileManager.XMLTranslator;
 
public class ParkingLots {
	private String identifier; 
	private LocalTime opening, closure, nightDailyStartsAt, nightDailyEndsAt;
	private short capacity;
	private float priceFraction, discountHour, priceDiurnal, taxNocturnal, valuePerEvent, valuePerMonthlyPayer, contractorsTaxReturn;
	String[] parkingTags = {"name", "startDay", "endDay", "startNight", "endNight", "capacity", "fraction", "hour", "day", "night", "event", "month", "taxReturn"};
	Object[] attributes;

	
	private XMLTranslator parkingLotData;


	public ParkingLots(String identifier, LocalTime opening, LocalTime closure, LocalTime nightDailyStartsAt, LocalTime nightDailyEndsAt, short capacity, float priceFraction, float discountHour, float priceDiurnal, float taxNocturnal, float  valuePerEvent, float valuePerMonthlyPayer, float contractorsTaxReturn) {
		try {
			this.parkingLotData = new XMLTranslator("ParkingLot");
		} catch (Exception e) {
			CSVManagerExceptions.errorMessage(e, e.getStackTrace().toString());
		}
		attributes = new Object[]{this.identifier, this.opening, this.closure, this.nightDailyStartsAt, this.nightDailyStartsAt, this.capacity, this.priceFraction, this.discountHour, this.priceDiurnal, this.taxNocturnal, this.valuePerEvent, this.valuePerMonthlyPayer, this.contractorsTaxReturn};
		Object[] parameters = new Object[]{identifier, opening, closure, nightDailyStartsAt, nightDailyEndsAt, capacity, priceFraction, discountHour, priceDiurnal, taxNocturnal, valuePerEvent, valuePerMonthlyPayer, contractorsTaxReturn};
		for (int i = 0; i < attributes.length; i++) {
			fastCreatingAssign(attributes[i], parameters[i], parkingTags[i]);
		}
	}

	public ParkingLots() {
		try {
			this.parkingLotData = new XMLTranslator("ParkingLot");
			this.identifier = getParkingInfo("name");
			this.opening = fastLocalTimeAssign("startDay");
			this.closure = fastLocalTimeAssign("endDay");
			this.nightDailyStartsAt = fastLocalTimeAssign("startNight"); 
			this.nightDailyEndsAt = fastLocalTimeAssign("endNight");
			this.capacity = Short.valueOf(getParkingInfo("capacity")); 
			this.priceFraction = Float.valueOf(getParkingInfo("fraction"));
			this.discountHour = Float.valueOf(getParkingInfo("hour"));
			this.priceDiurnal = Float.valueOf(getParkingInfo("day"));
			this.taxNocturnal = Float.valueOf(getParkingInfo("night"));
			this.valuePerEvent = Float.valueOf(getParkingInfo("event"));
			this.valuePerMonthlyPayer = Float.valueOf(getParkingInfo("month"));
			this.contractorsTaxReturn = Float.valueOf(getParkingInfo("taxReturn"));
			attributes = new Object[]{this.identifier, this.opening, this.closure, this.nightDailyStartsAt, this.nightDailyStartsAt, this.capacity, this.priceFraction, this.discountHour, this.priceDiurnal, this.taxNocturnal, this.valuePerEvent, this.valuePerMonthlyPayer, this.contractorsTaxReturn};
		} catch(NumberFormatException err){
			//throw new InvalidValueException(value, csvFile);
		} 
		
		catch (Exception e) {
			CSVManagerExceptions.errorMessage(e, "error on ParkingLot.xml or its translation");
		}
		
		
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
	public String getParkingInfo(String tagName) {
        return this.parkingLotData.getRootTag().getElementsByTagName(tagName).item(0).getTextContent();
    } private void setParkingInfo(String tagName, int appearanceIndex, String textContent) {
        this.parkingLotData.getRootTag().getElementsByTagName(tagName).item(appearanceIndex).setTextContent(textContent);
    } 
	private void fastCreatingAssign(Object attribute, Object parameter, String tagName) {
		attribute = parameter;
		setParkingInfo(tagName, 0, attribute.toString());
	}
	private LocalTime fastLocalTimeAssign(String tagName) {
		String[] time = getParkingInfo(tagName).split(":");
		return LocalTime.of(Integer.valueOf(time[0]), Integer.valueOf(time[1]));
	}
	public String[] getParkingTags() {
		return parkingTags;
	}
	public Object[] getAttributes() {
		return attributes;
	}
}