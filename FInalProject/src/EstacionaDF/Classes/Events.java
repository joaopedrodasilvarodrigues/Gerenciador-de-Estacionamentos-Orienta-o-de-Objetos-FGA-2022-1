package EstacionaDF.Classes;

public class Events {
	
	private String eventName, startingDate, endingDate, startingHour, endingHour;

	public Events(String eventName, String startingDate, String endingDate, String startingHour, String endingHour) {
		this.eventName = eventName;		
		this.startingDate = startingDate;		
		this.endingDate = endingDate;				
		this.startingHour = startingHour;		
		this.endingHour = endingHour;				
	}
	
	public String getEventName() {			
		return eventName;
	}
	
	public String getStartingDate() {
		return startingDate;
	}
	
	public String getEndingDate() {
		return endingDate;
	}
	
	public String getStartingHour() {
		return startingHour;
	}
	
	public String getEndingHour() {
		return endingHour;
	}
}
