package EstacionaDF.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import EstacionaDF.SystemApp;


public class HomeScreen extends DefaultScreen{
    // attributes
    private JLabel welcome;
    private JButton userBtn;
    private JButton admBtn;
    private JPanel boxPanel;

    // constructor
    public HomeScreen(SystemApp screenSys) {
        super(screenSys);        
        // Welcome label
        setWelcome(getScreenSys().getTextContent("welcome", 0)
        + getScreenSys().getTitle()
        + getScreenSys().getTextContent("intro", 0));
        getWelcome().setAlignmentX(SwingConstants.CENTER);
        placeElementGrid(getWelcome(), 0, 0, 3, 1);
        
        //User btn and ADM btn
        this.boxPanel = new JPanel();
        this.boxPanel.setLayout(new FlowLayout());

        
        setUserBtn(new JButton(getScreenSys().getTextContent("userBtn", 0)));
        getUserBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                toUser(UserScreen.USER_HOME);
            }
        });
        setAdmBtn(new JButton(getScreenSys().getTextContent("admBtn", 0)));
        getAdmBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt2) {
                toAdm(AdmScreen.ADM_HOME);
            }});
        this.boxPanel.add(this.userBtn);
        this.boxPanel.add(this.admBtn);
        placeElementGrid(this.boxPanel, 0, 1, 3, 1);
        
        getScreenSys().pack();        
    }

    // getter and setters

    public JLabel getWelcome() {
        return welcome;
    }
    public void setWelcome(String welcome) {
        this.welcome = new JLabel(welcome);
    }
    public JButton getUserBtn() {
        return userBtn;
    } public void setUserBtn(JButton userBtn) {
        this.userBtn = userBtn;
    } public JButton getAdmBtn() {
        return admBtn;
    } public void setAdmBtn(JButton admBtn) {
        this.admBtn = admBtn;
    } public JPanel getBoxPanel() {
        return boxPanel;
    } public void setBoxPanel(JPanel boxPanel) {
        this.boxPanel = boxPanel;
    }

}
