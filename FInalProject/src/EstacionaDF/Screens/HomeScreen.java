package EstacionaDF.Screens;

import javax.swing.*;

import EstacionaDF.SystemApp;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class HomeScreen extends DefaultScreen{
    // attributes
    private JLabel welcome;
    private JButton userButton;
    private JButton admButton;
    private JPanel boxPanel;

    // constructor
    public HomeScreen(SystemApp screenSys) {
        super(screenSys);        
        // Welcome label
        setWelcome("<html><body align='center'><h1>Bem-vindo(a) ao<br>"
        + screenSys.getTitle().toUpperCase() + "!</h1><br><br>"
        + "<p>Cadastre seu veículo em nosso sistema para garantir<br>"
        + "sua vaga no estacionmento X.<br><br></body></html>");
        getWelcome().setAlignmentX(SwingConstants.CENTER);
        placeElementGrid(getWelcome(), 0, 0, 3, 1);
        
        //User btn and ADM btn
        this.boxPanel = new JPanel();
        this.boxPanel.setLayout(new FlowLayout());

        
        setUserButton(new JButton("Usuário"));
        getUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                toUser(UserScreen.USER_HOME);
            }
        });
        setAdmButton(new JButton("Administrador"));
        getAdmButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt2) {
                //toAdm();
            }});
        this.boxPanel.add(this.userButton);
        this.boxPanel.add(this.admButton);
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
    public JButton getUserButton() {
        return userButton;
    } public void setUserButton(JButton userButton) {
        this.userButton = userButton;
    } public JButton getAdmButton() {
        return admButton;
    } public void setAdmButton(JButton admButton) {
        this.admButton = admButton;
    } public JPanel getBoxPanel() {
        return boxPanel;
    } public void setBoxPanel(JPanel boxPanel) {
        this.boxPanel = boxPanel;
    }

}
