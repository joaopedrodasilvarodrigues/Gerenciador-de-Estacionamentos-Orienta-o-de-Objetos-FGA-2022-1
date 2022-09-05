package EstacionaDF.Screens;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import EstacionaDF.SystemApp;
import java.awt.Component;

public abstract class DefaultScreen {
    private SystemApp screenSys;
    private GridBagLayout localLayout;
    private GridBagConstraints localLayoutProperties;
    private JButton backBtn;
    private String[] categoriesPlates;


    DefaultScreen(SystemApp screenSys) {
        setScreenSys(screenSys); // to access and modify the App's System attributes and methods. 
        setLocalLayout(new GridBagLayout());
        getScreenSys().setLayout(getLocalLayout()); // definido o layout da página local como GridBag.
        setLocalLayoutProperties(new GridBagConstraints()); // create a GridBag customization API.
        this.categoriesPlates = new String[] {"Placas", "Entrada", "Mensalista"};
    }

    // getters and setters
    public SystemApp getScreenSys() {
        return screenSys;
    } public void setScreenSys(SystemApp screenSys) {
        this.screenSys = screenSys;
    } public GridBagLayout getLocalLayout() {
        return localLayout;
    } public void setLocalLayout(GridBagLayout localLayout) {
        this.localLayout = localLayout;
    } public GridBagConstraints getLocalLayoutProperties() {
        return localLayoutProperties;
    } public void setLocalLayoutProperties(GridBagConstraints localLayoutProperties) {
        this.localLayoutProperties = localLayoutProperties;
    } public JButton getBackBtn() {
        return backBtn;
    } public void setBackBtn(JButton backBtn) {
        this.backBtn = backBtn;
    } public String[] getCategoriesPlates() {
        return categoriesPlates;
    }
    
    public void createBackBtn(ActionListener backto) {
        setBackBtn(new JButton(getScreenSys().getTextContent("back", 0)));
        placeElementGrid(getBackBtn(), 0, 0, 1, 1);
        getBackBtn().addActionListener(backto);   
    }


    protected void placeElementGrid(Component item, int gridx, int gridy) {
        getLocalLayoutProperties().gridx = gridx;
        getLocalLayoutProperties().gridy = gridy;
        getLocalLayout().setConstraints(item, getLocalLayoutProperties());
        getScreenSys().getContentPane().add(item);


    }
    protected void placeElementGrid(Component item, int gridx, int gridy, int gridwidth, int gridheight) {
        getLocalLayoutProperties().gridx = gridx;
        getLocalLayoutProperties().gridy = gridy;
        getLocalLayoutProperties().gridwidth = gridwidth;
        getLocalLayoutProperties().gridheight = gridheight;
        getLocalLayout().setConstraints(item, getLocalLayoutProperties());
        getScreenSys().getContentPane().add(item);
    }

    protected void cleanPage() {
        getScreenSys().getContentPane().removeAll();
    }
    public void toHome() {
        cleanPage();
        getScreenSys().setPage(new HomeScreen(screenSys));
    }
    public void toUser(int section) {
        cleanPage();
        getScreenSys().setPage(new UserScreen(screenSys, section));
    }
    public void toAdm(int section) {
        cleanPage();
        getScreenSys().setPage(new AdmScreen(screenSys, section));
    }
}
