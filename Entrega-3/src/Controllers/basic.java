package Controllers;

import View.*;

import java.util.Set;

import javax.swing.JOptionPane;

import Model.*;
import Model.Student.AvaliableCourses;

public class basic {
    public static void main(String[] args) {
        while(true){
            try {
                SwingUI screen = new SwingUI();
                Student student = new Student();
                switch (screen.mainMenu()) {
                    case 0:
                        String[] freshUser = screen.showRegisterDialogs(student,
                        Student.class.getMethod("setName", String.class),
                        Student.class.getMethod("getName"),
                        Student.class.getMethod("setEmail", String.class),
                        Student.class.getMethod("getEmail"),
                        Student.class.getMethod("setCourse", AvaliableCourses.class),
                        Student.class.getMethod("getCourse"),
                        Student.class.getMethod("setId", String.class),
                        Student.class.getMethod("getId"),
                        AvaliableCourses.values());
                        screen.showProfileDialog(freshUser[0], freshUser[1], freshUser[2], freshUser[3]);
                        Student.setStudentInfo(freshUser[0], freshUser[1], freshUser[2], AvaliableCourses.valueOf(freshUser[3]));
                        break;
                    case 1:
                        String[] studentInfo = Student.getStudentInfo(screen.showSearchDialog());
                        screen.showProfileDialog(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);
                        break;
                    default:
                        System.exit(-1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    
        }
    }
}
