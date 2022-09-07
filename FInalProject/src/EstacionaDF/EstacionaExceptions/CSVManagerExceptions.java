package EstacionaDF.EstacionaExceptions;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import EstacionaDF.Screens.DefaultScreen;

public abstract class CSVManagerExceptions extends Exception{
    private String csvFile;
    private boolean onCSV;

    public CSVManagerExceptions(String csvFile, boolean onCSV) {
        super(csvFile + ", " + onCSV);
        this.csvFile = csvFile;
        this.onCSV = onCSV;
        
    }
    public static JLabel feedback(String message) {
        JLabel msg = new JLabel("ERROR: " + message.toLowerCase());
        msg.setForeground(Color.RED);
        return msg;
    }
    public static void errorMessage(Exception exception, String message) {
        new Thread("Error_Screens") {
            @Override
            public void run() {
                if (JOptionPane.showConfirmDialog(null, feedback(message).getText().substring(7), "ERROR", JOptionPane.YES_NO_OPTION , JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
                    exception.printStackTrace();
                }
            }
        }.start();
    }

    public String getCsvFile() {
        return csvFile;
    }
    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }
    public boolean isOnCSV() {
        return this.onCSV;
    }
    public void setOnCSV(boolean onCSV) {
        this.onCSV = onCSV;
    }

}
