package EstacionaDF.Screens;
import javax.swing.*;

import EstacionaDF.SystemApp;
import EstacionaDF.FileManager.CSVManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;

public class UserScreen extends DefaultScreen {
    private JLabel intro;
    private JButton register, takeBack;
    private JLabel allInfoRegister, allInfoTakeBack;
    private Box infoRegister, writeRegister;
    private JTextField vehiclePlate;
    private JLabel priceInfo;
    private int actualSection;
    private CSVManager platesCSV;
    public static final int USER_HOME = 0;
    public static final int USER_REGISTER = 1;
    public static final int USER_TAKE_BACK = 2;
    
    
    public UserScreen(SystemApp screenSys, int section) {
        super(screenSys);
        this.actualSection = section;
        // section manager
        toUserHome();
        switch (this.actualSection) {
            case USER_HOME:
                toUserHome();
                break;
            case USER_REGISTER:
                toRegister();
                break;
            case USER_TAKE_BACK:
                toTakeBack();
                break;
        }
    }

    // Home sections
    public void toUserHome() {
        cleanPage();
        // Back Btn
        createBackBtn(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toHome();
            }
        });
        // Intro text
        setIntro(new JLabel(getScreenSys().getTextContent("intro", 1)));
        placeElementGrid(getIntro(), 0, 1, 3, 1);

        // register and takeback btns.
        JPanel boxPanel = new JPanel(new FlowLayout());
        setRegister(new JButton(getScreenSys().getTextContent("registerBtn", 0)));
        getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                toUser(UserScreen.USER_REGISTER);
            }
        });
        setTakeBack(new JButton(getScreenSys().getTextContent("takeBackBtn", 0)));
        getTakeBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toUser(UserScreen.USER_TAKE_BACK);
            }
        });
        boxPanel.add(getRegister());
        boxPanel.add(getTakeBack());
        
        placeElementGrid(boxPanel, 0, 2, 3, 1);
        
        getScreenSys().pack();

        
    }


    public void toRegister() {
        cleanPage();
        // back btn
        this.createBackBtn(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toUser(UserScreen.USER_HOME);;
            }
        });

        //intro
        setIntro(new JLabel(getScreenSys().getTextContent("intro", 2)));
        placeElementGrid(getIntro(), 0, 1, 3, 1);


        setInfoRegister(Box.createVerticalBox());
        getInfoRegister().add(Box.createVerticalStrut(25));
        setWriteRegister(Box.createVerticalBox());
        getWriteRegister().add(Box.createVerticalStrut(25));
        
    
        // plate
        getInfoRegister().add(new JLabel(getScreenSys().getTextContent("plates", 0)));
        setVehiclePlate(new JTextField(20));
        getWriteRegister().add(getVehiclePlate());
        
        JPanel bPanel = new JPanel(new FlowLayout());
        bPanel.add(getInfoRegister());
        bPanel.add(getWriteRegister());
        placeElementGrid(bPanel, 0, 2);
        
        
        // Send
        setRegister(new JButton(getScreenSys().getTextContent("send", 0)));
        placeElementGrid(getRegister(), 1, 3);

        getScreenSys().pack();                
    }

    public void toTakeBack() {
        cleanPage();
        // back btn
        createBackBtn(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toUser(UserScreen.USER_HOME);
            }
        });
        //intro
        setIntro(new JLabel(
            "<html><h3>Retire seu Veículo</h3>"
            + "<p>Digite a placa do veículo abaixo e pressione<br>"
            + "o botão \"Retirar\" no fim da página.<br><br></p></html>"));
        placeElementGrid(getIntro(), 0, 1, 3, 1);

        /*
        box vertical panels on screen
         __   __
        |  | |  |
        |__| |__|

         */
        setInfoRegister(Box.createVerticalBox());
        getInfoRegister().add(Box.createVerticalStrut(25));
        setWriteRegister(Box.createVerticalBox());
        getWriteRegister().add(Box.createVerticalStrut(25));
        
        // plate
        getInfoRegister().add(new JLabel(getScreenSys().getTextContent("plates", 0)));
        setVehiclePlate(new JTextField(20));
        getWriteRegister().add(getVehiclePlate());

        JPanel bPanel = new JPanel(new FlowLayout());
        bPanel.add(getInfoRegister());
        bPanel.add(getWriteRegister());
        placeElementGrid(bPanel, 0, 2);
        // Send
        setTakeBack(new JButton(getScreenSys().getTextContent("takeBackBtn", 0)));
        /* getTakeBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg2) {
                String[] categoriesPlates = {"Placas", "Entrada", "Mensalista"};
                setPlatesCSV(new CSVManager("placas.csv", categoriesPlates));
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                String[] results = getPlatesCSV().findUser(getVehiclePlate().getText(), 0, true).split(",");
                                for (String result : results) {
                                    int popAnswer = JOptionPane.showConfirmDialog(null, "Este é o seu veículo?" + result, "Veículo", JOptionPane.YES_NO_OPTION);
                                    if (popAnswer == JOptionPane.YES_OPTION) {
                                        // show the value to pay and confirm.
                                    } else if(result != results[results.length - 1]) {
                                        continue;
                                    } else {
                                        // throw "No such vehicle" Exception. Name it as you want.
                                    }
                                }   
                            } catch (Exception e) {
                                JLabel errorMsg = new JLabel("Erro: insira um valor válido");
                                errorMsg.setForeground(Color.RED);
                                getInfoRegister().add(errorMsg);
                            }
                        };
                    };
            }
        }); */
        placeElementGrid(getTakeBack(), 1, 3);
        getScreenSys().pack();
    
    }

    // getters and setters
    public JTextField getVehiclePlate() {
        return vehiclePlate;
    } public void setVehiclePlate(JTextField vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    } public JLabel getIntro() {
        return intro;
    } public void setIntro(JLabel intro) {
        this.intro = intro;
    } public JButton getRegister() {
        return register;
    } public void setRegister(JButton register) {
        this.register = register;
    } public JButton getTakeBack() {
        return takeBack;
    } public void setTakeBack(JButton takeBack) {
        this.takeBack = takeBack;
    } public JLabel getAllInfoRegister() {
        return allInfoRegister;
    } public void setAllInfoRegister(JLabel allInfoRegister) {
        this.allInfoRegister = allInfoRegister;
    } public JLabel getAllInfoTakeBack() {
        return allInfoTakeBack;
    } public void setAllInfoTakeBack(JLabel allInfoTakeBack) {
        this.allInfoTakeBack = allInfoTakeBack;
    } public JLabel getPriceInfo() {
        return priceInfo;
    } public void setPriceInfo(JLabel priceInfo) {
        this.priceInfo = priceInfo;
    } public Box getInfoRegister() {
        return infoRegister;
    } public void setInfoRegister(Box infoRegister) {
        this.infoRegister = infoRegister;
    } public Box getWriteRegister() {
        return writeRegister;
    } public void setWriteRegister(Box writeRegister) {
        this.writeRegister = writeRegister;
    } public CSVManager getPlatesCSV() {
        return platesCSV;
    } public void setPlatesCSV(CSVManager platesCSV) {
        this.platesCSV = platesCSV;
    }

}
