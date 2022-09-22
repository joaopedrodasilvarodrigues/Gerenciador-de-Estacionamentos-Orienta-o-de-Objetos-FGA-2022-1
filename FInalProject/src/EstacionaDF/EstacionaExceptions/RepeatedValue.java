package EstacionaDF.EstacionaExceptions;

public class RepeatedValue extends CSVManagerExceptions{
    private String duplicatedValue;

    public RepeatedValue(String duplicated, String csvFile) {
        super(csvFile, true);
        this.duplicatedValue = duplicated;
    }
    public String getDuplicatedValue() {
        return duplicatedValue;
    }
    public void setDuplicatedValue(String duplicatedValue) {
        this.duplicatedValue = duplicatedValue;
    }
}
