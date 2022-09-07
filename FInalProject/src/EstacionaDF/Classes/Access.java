package trabalho2;

import java.time.*;

public class Access {
	
	public enum AccessType {
		MONTHLY_PAYER, EVENT, OTHER;
		}
	
	private ParkingLots parking;
	private String licensePlate;
	private AccessType accessType;
	private LocalDateTime entryAt, exitAt;
	private float accessValue, contractorValue;
	
	public Access(ParkingLots parking, String licensePlate,AccessType accessType,LocalDateTime entryAt, LocalDateTime exitAt){
		this.setLicensePlate(licensePlate);
		this.setAccessType(accessType);
		this.setEntryAt(entryAt);
		this.setExitAt(exitAt);
		this.setParking(parking);
		this.setAccessValue(this.calculateAccessValue());
	}
	
	public float calculateAccessValue() {
		if (this.accessType.equals(AccessType.MONTHLY_PAYER)) {
			return this.parking.getValuePerMonthlyPayer();
		} else if (this.accessType.equals(AccessType.EVENT)){
			return this.parking.getValuePerEvent();
		} else {
			float accessValueResult = (float) 0.0;
			Duration timeDiff = Duration.between(this.parking.getNightDailyStartsAt(), this.parking.getNightDailyEndsAt());
	        long secTimeDiff = timeDiff.getSeconds() > 0 ? timeDiff.getSeconds() : timeDiff.getSeconds() + 24 * 60 * 60; //diferença de segundos no periodo noturno
	        long minTimeDiff = timeDiff.getSeconds() > 0 ? timeDiff.getSeconds()/(60) : (timeDiff.getSeconds()+24*60*60)/(60);
	        long hourTimeDiff = timeDiff.getSeconds() > 0 ? timeDiff.getSeconds()/(60*60) : (timeDiff.getSeconds()+24*60*60)/(60*60);
	        LocalDateTime dtParkingStart = LocalDateTime.of(this.entryAt.toLocalDate(), this.parking.getNightDailyStartsAt()); //cria novo horario p/concatenando horario de entrada e saída
	        LocalDateTime dtParkingEnd   = dtParkingStart.plusSeconds(secTimeDiff); 
	        
	        if(hourTimeDiff >= 9){
	        	accessValueResult = this.parking.getPriceDiurnal();
	        } else if(hourTimeDiff > 0){
	        	float payablePercentage = (100-this.parking.getDiscountHour())/100;
	        	accessValueResult = ((hourTimeDiff * 60) / 15) * this.parking.getPriceFraction() * payablePercentage + ((minTimeDiff - (hourTimeDiff*60))/15) * this.parking.getPriceFraction();
	        } else{
	        	accessValueResult = (minTimeDiff/15) * this.parking.getPriceFraction();
	        }
	        
	        if (this.entryAt.isAfter(dtParkingStart) && this.exitAt.isBefore(dtParkingEnd)) {
	        	float payablePercentage = (100 - this.parking.getTaxNocturnal())/100;
	        	accessValueResult = accessValueResult * payablePercentage; 
	        }
	        
	        return accessValueResult; 
		}
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public AccessType getAccessType() {
		return accessType;
	}

	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}

	public LocalDateTime getExitAt() {
		return exitAt;
	}

	public void setExitAt(LocalDateTime exitAt) {
		this.exitAt = exitAt;
	}

	public LocalDateTime getEntryAt() {
		return entryAt;
	}

	public void setEntryAt(LocalDateTime entryAt) {
		this.entryAt = entryAt;
	}

	public float getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(float accessValue) {
		this.accessValue = accessValue;
	}

	public float getContractorValue() {
		return contractorValue;
	}

	public void setContractorValue(float contractorValue) {
		this.contractorValue = contractorValue;
	}

	public ParkingLots getParking() {
		return parking;
	}

	public void setParking(ParkingLots parking) {
		this.parking = parking;
	}
}
