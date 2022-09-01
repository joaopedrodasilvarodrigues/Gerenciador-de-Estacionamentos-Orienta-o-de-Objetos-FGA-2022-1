package EstacionaDF;
import javax.swing.JOptionPane;
import EstacionaDF.FileManager.CSVManager;

public class init {
    public static void main(String[] args) {
        /* SystemApp app;
        // General error treatment
        try {
            app = new SystemApp();
        } catch (Exception e) {
             new Thread() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, 
                    "<html><h3>O que fazer:</h3><br>" + e.getMessage() + "</html>"
                    , "ERROR", JOptionPane.ERROR_MESSAGE);
                };
            }.start();
        }
 */
        // tests with csv manager
         try {
            String[] list = {"um", "dois", "tres", "quatro"};
            CSVManager placas = new CSVManager("placas.csv", list);
            placas.addLine("Oswaldo", "1029384", "07/07/2007", String.valueOf(true));
            placas.showContent();
            //System.out.println(placas.findUser("7654321", 1, true));
            placas.showSearch("1029384", 1);
            placas.deleteLine("7654321", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
