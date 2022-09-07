package EstacionaDF.Screens;
import javax.swing.*;

import EstacionaDF.SystemApp;
import EstacionaDF.EstacionaExceptions.BlankFieldException;
import EstacionaDF.EstacionaExceptions.CSVManagerExceptions;
import EstacionaDF.EstacionaExceptions.InvalidValueException;
import EstacionaDF.EstacionaExceptions.RepeatedValue;
import EstacionaDF.FileManager.CSVManager;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;

public class UserScreen extends DefaultScreen {
    private JButton register, takeBack;
    private JCheckBox mensalist;
    private JLabel allInfoRegister, allInfoTakeBack;
    private Box infoRegister, writeRegister;
    private JTextField vehiclePlate;
    private JLabel priceInfo;
    private int actualSection;
    private CSVManager platesCSV;
    private boolean insertedWrong;
    public static final int USER_HOME = 0;
    public static final int USER_REGISTER = 1;
    public static final int USER_TAKE_BACK = 2;
    
    
    public UserScreen(SystemApp screenSys, int section){
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


    public void toRegister(){
        cleanPage();
        // back btn
        this.createBackBtn(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toUser(UserScreen.USER_HOME);
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

        // Mensalist
        getInfoRegister().add(new JLabel(getScreenSys().getTextContent("mensalist", 0)));
        setMensalist(new JCheckBox());
        getWriteRegister().add(getMensalist());
        
        JPanel bPanel = new JPanel(new FlowLayout());
        bPanel.add(getInfoRegister());
        bPanel.add(getWriteRegister());
        placeElementGrid(bPanel, 0, 2);
        //Red error label
        if (this.insertedWrong) {
            JLabel errorMsg = new JLabel(getScreenSys().getTextContent("invalidInput", 0));
            errorMsg.setForeground(Color.RED);
            placeElementGrid(errorMsg, 0, 3);
            this.insertedWrong = false;
        }

        // Send
        setRegister(new JButton(getScreenSys().getTextContent("send", 0)));
        getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String freshMessage = getVehiclePlate().getText().replaceAll(" ", "");
                try {
                    setPlatesCSV(new CSVManager("plates", getCategoriesPlates()));
                    // check if there is at least one register with the same plate
                    // Eliminate copied or fake plates
                    getPlatesCSV().findUser(freshMessage, 0, false);
                    throw new RepeatedValue(freshMessage, getPlatesCSV().getFilename());
                }
                //error condition to add a line to the database
                catch(NoSuchElementException notFoundValue) {                    
                    try {
                        Integer.valueOf(freshMessage);
                        if (freshMessage.length() != 7) {
                            throw new InvalidValueException(freshMessage, getPlatesCSV().getFilename());
                        }
                        getPlatesCSV().addLine(freshMessage, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), String.valueOf(getMensalist().isSelected()));
                        setInsertedWrong(false);
                    }
                     catch (BlankFieldException | InvalidValueException e1) {
                        setInsertedWrong(true);
                        toRegister();
                    }
                    // Unexpected erros
                    catch(Exception others) {
                        CSVManagerExceptions.errorMessage(others, "message");
                        toHome();
                    }
                    // confirm action
                    if (!isInsertedWrong()) {
                        new Thread(){
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(null, getScreenSys().getTextContent("confirm", 1), getScreenSys().getTextContent("confirm", 0), JOptionPane.WARNING_MESSAGE);                            
                            };
                        }.start();
                        toHome();
                    }
                }
                catch (RepeatedValue similar) {
                    setInsertedWrong(true);
                    toRegister();
                }
                // unexpected
                catch (Exception otherError) {
                    CSVManagerExceptions.errorMessage(otherError, "message");
                    toHome();
                }
                
            }
        });
        placeElementGrid(getRegister(), 1, 4);
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
        setIntro(new JLabel(getScreenSys().getTextContent("intro", 3)));
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
        
        //red error label
        if (this.insertedWrong) {
            JLabel errorMsg = new JLabel(getScreenSys().getTextContent("invalidInput", 0));
            errorMsg.setForeground(Color.RED);
            placeElementGrid(errorMsg, 0, 3);
        }

        // Send
        setTakeBack(new JButton(getScreenSys().getTextContent("takeBackBtn", 0)));
        getTakeBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setPlatesCSV(new CSVManager("plates.csv", getCategoriesPlates()));                    
                    try {
                        if (getVehiclePlate().getText().length() != 7) {
                            throw new Exception("InvalidValueException");
                        }
                        setInsertedWrong(false);
                        getPlatesCSV().deleteLine(getVehiclePlate().getText(), 0);
                    } catch (Exception e1) {
                        setInsertedWrong(true);
                        toTakeBack();;

                    }
                    if (!isInsertedWrong()) {
                        new Thread(){
                            @Override
                            public void run() {
                            JOptionPane.showMessageDialog(null, getScreenSys().getTextContent("confirm", 1), getScreenSys().getTextContent("confirm", 0), JOptionPane.WARNING_MESSAGE);                            
                            };
                        }.start();
                        toHome();
                    }
                }   
                catch (Exception otherError) {
                    //throw Erro
                    otherError.printStackTrace();
                }
                
            }
        });
        placeElementGrid(getTakeBack(), 1, 4);
        getScreenSys().pack();
    
    }

    // getters and setters
    public JTextField getVehiclePlate() {
        return vehiclePlate;
    } public void setVehiclePlate(JTextField vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
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
    } public JCheckBox getMensalist() {
        return mensalist;
    } public void setMensalist(JCheckBox mensalist) {
        this.mensalist = mensalist;
    } public boolean isInsertedWrong() {
        return insertedWrong;
    } public void setInsertedWrong(boolean insertedWrong) {
        this.insertedWrong = insertedWrong;
    }

}
