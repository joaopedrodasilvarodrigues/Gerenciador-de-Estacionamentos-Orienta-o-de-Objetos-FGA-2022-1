package EstacionaDF.Classes;

import EstacionaDF.FileManager.CSVManager;

public class Events {
	
	private String eventName, startingDate, endingDate, startingHour, endingHour;
	private CSVManager eventsData;
	private String[] attributes;

	public Events(String eventName, String startingDate, String endingDate, String startingHour, String endingHour) {
		this.eventName = eventName;		
		this.startingDate = startingDate;		
		this.endingDate = endingDate;				
		this.startingHour = startingHour;		
		this.endingHour = endingHour;
/* 		for (String attribute : attributes{this.eventName, startingDate, }) {
			
		}	 */			
	}
/* 	public Events() {
		
	} */

	
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
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	} public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	} public void setStartingHour(String startingHour) {
		this.startingHour = startingHour;
	} public void setEndingHour(String endingHour) {
		this.endingHour = endingHour;
	} 
}
