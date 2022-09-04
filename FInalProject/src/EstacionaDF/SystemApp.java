package EstacionaDF;

import javax.swing.*;

import EstacionaDF.Screens.DefaultScreen;
import EstacionaDF.Screens.HomeScreen;

public class SystemApp extends JFrame{
    private DefaultScreen page;

    public SystemApp() {
        // Essencial
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        setVisible(true);
        setTitle("EstacionaDF");
        setSize(500, 500);
        // setIconImage(image);
        page = new HomeScreen(this);

    }
    public DefaultScreen getPage() {
        return page;
    } public void setPage(DefaultScreen page) {
        this.page = page;
    }

}
