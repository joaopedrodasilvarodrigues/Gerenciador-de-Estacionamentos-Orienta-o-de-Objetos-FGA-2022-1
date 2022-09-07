package EstacionaDF;

import EstacionaDF.EstacionaExceptions.CSVManagerExceptions;

public class init {
    public static void main(String[] args) {
         SystemApp app;
        // General error treatment
        try {
            app = new SystemApp();
        } catch (Exception e) {
            CSVManagerExceptions.errorMessage(e, "FATAL ERROR!!!");
            app = null;
        }
        
    }
}
