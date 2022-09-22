package EstacionaDF.Screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import EstacionaDF.SystemApp;
import EstacionaDF.Classes.ParkingLots;
import EstacionaDF.Classes.Vehicles;
import EstacionaDF.Classes.Access.AccessType;
import EstacionaDF.EstacionaExceptions.CSVManagerExceptions;

public class AdmScreen extends DefaultScreen {
    private int actualSection;
    private JButton platesBtn, parkingLotBtn, eventBtn, editPlate;
    private JTextField editField, changeField;
    private ParkingLots parkingLot;
    private JRadioButton plateOption, entranceOption, mensalistOption;
    private HashMap<String, JTextField> parkingLotFields;
    private HashMap<String, JComponent> eventsFields;

    
    public static final int ADM_HOME = 0;
    public static final int ADM_PLATES = 1;
    public static final int ADM_PARKINGLOT = 2;
    public static final int ADM_EVENT = 3;
    

    public AdmScreen(SystemApp screenSys, int section) {
        super(screenSys);
        this.actualSection = section;
        switch (this.actualSection) {
            case ADM_HOME:
                toAdmHome();
                break;
            case ADM_PLATES:
                toPlatesManager();
                break;
            case ADM_EVENT:
                toEventManager();
                break;
            case ADM_PARKINGLOT:
                toParkingManager();
                break;
        }

    }

    public void toAdmHome() {
        cleanPage();
        createBackBtn(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                toHome();
            };
        });
        setIntro(new JLabel(getScreenSys().getTextContent("intro", 4)));
        placeElementGrid(getIntro(), 0, 1, 3, 1);

        JPanel btns = new JPanel(new FlowLayout());

        setPlatesBtn(new JButton(getScreenSys().getTextContent("plates", 1)));
        getPlatesBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAdm(AdmScreen.ADM_PLATES);
            }
        });

        setParkingLotBtn(new JButton(getScreenSys().getTextContent("parking", 0)));
        getParkingLotBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAdm(AdmScreen.ADM_PARKINGLOT);
            }
        });
        btns.add(getPlatesBtn());
        btns.add(getParkingLotBtn());

        placeElementGrid(btns, 0, 2);
        
        setEventBtn(new JButton(getScreenSys().getTextContent("events", 0)));
        getEventBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAdm(AdmScreen.ADM_EVENT);
            }
        });
        placeElementGrid(getEventBtn(), 0, 3);
        getScreenSys().pack();
        
    }

    public void toPlatesManager() {
        cleanPage();
        createBackBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAdm(AdmScreen.ADM_HOME);
                
            }
        });
        setIntro(new JLabel(getScreenSys().getTextContent("intro", 5)));
        placeElementGrid(getIntro(), 0, 1, 3, 1);
        setPlatesBtn(new JButton(getScreenSys().getTextContent("seeBtn", 0)));
        getPlatesBtn().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vehicles.platesTable().showContent();
                } catch (Exception e1) {
                    CSVManagerExceptions.errorMessage(e1, e1.getStackTrace().toString());
                    toAdm(AdmScreen.ADM_HOME);
                }
                
            }
        });
        placeElementGrid(getPlatesBtn(), 0, 2);
        

        setEditPlate(new JButton(getScreenSys().getTextContent("editBtn", 0)));
        getEditPlate().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                
            }
        });
        getScreenSys().pack();
    }
    public void toEventManager() {
        cleanPage();
        createBackBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAdm(AdmScreen.ADM_HOME);
                
            }
        });
        setIntro(new JLabel(getScreenSys().getTextContent("intro", 6)));
        placeElementGrid(getIntro(), 0, 1);
        this.eventsFields = new HashMap<>(3);
    
        for (String eventAttribute : new String[]{"name", "openingDate", "closureDate"}) {
            this.eventsFields.put(eventAttribute, new JTextField(eventAttribute, 25));
            placeElementGrid(this.eventsFields.get(eventAttribute), 0, eventsFields.size() + 1);
        }        
        setEventBtn(new JButton(getScreenSys().getTextContent("send", 0)));
        placeElementGrid(getEventBtn(), 1, 14);
        getScreenSys().pack();
    }
    public void toParkingManager() {
        cleanPage();
        createBackBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toAdm(AdmScreen.ADM_HOME);
            }
        });

        setIntro(new JLabel(getScreenSys().getTextContent("intro", 7)));
        placeElementGrid(getIntro(), 0, 1, 4, 1);
        this.parkingLot = new ParkingLots();
        this.parkingLotFields = new HashMap<>(12);
        for (int i = 0; i < parkingLot.getAttributes().length; i++) {
            JPanel fieldsPanel = new JPanel(new FlowLayout());
            this.parkingLotFields.put(parkingLot.getParkingTags()[i], new JTextField(parkingLot.getAttributes()[i].toString(), 10));
            fieldsPanel.add(new JLabel(parkingLot.getParkingTags()[i]));
            fieldsPanel.add(this.parkingLotFields.get(parkingLot.getParkingTags()[i]));
            placeElementGrid(fieldsPanel, 1, i + 2, 2, 1);
        }
        setEventBtn(new JButton(getScreenSys().getTextContent("send", 0)));
        placeElementGrid(getEventBtn(), 1, 15);
        getScreenSys().pack();
    }

    public JButton getPlatesBtn() {
        return platesBtn;
    }
    public void setPlatesBtn(JButton platesBtn) {
        this.platesBtn = platesBtn;
    }
    public JButton getParkingLotBtn() {
        return parkingLotBtn;
    }
    public void setParkingLotBtn(JButton parkingLotBtn) {
        this.parkingLotBtn = parkingLotBtn;
    }
    public JButton getEventBtn() {
        return eventBtn;
    }
    public void setEventBtn(JButton eventBtn) {
        this.eventBtn = eventBtn;
    }
    public JButton getEditPlate() {
        return editPlate;
    }
    public void setEditPlate(JButton editPlate) {
        this.editPlate = editPlate;
    }
    public JTextField getEditField() {
        return editField;
    }
    public void setEditField(JTextField editField) {
        this.editField = editField;
    }

}


