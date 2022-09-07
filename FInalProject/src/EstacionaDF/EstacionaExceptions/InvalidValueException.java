package EstacionaDF.EstacionaExceptions;

public class InvalidValueException extends CSVManagerExceptions{
    private String invalidValue;
    
    public InvalidValueException(String value, String csvFile){
        /** value: the invalid value in String format.
         * csvFile: name of the CSV File.
         */
        super(csvFile, false);
        this.invalidValue = value;
    }
    public String getInvalidValue() {
        return invalidValue;
    }
    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }
}
