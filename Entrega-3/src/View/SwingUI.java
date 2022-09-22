package View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.*;

import Model.Student.AvaliableCourses;


public class SwingUI extends JOptionPane{
    public String title;
    String profileDescription;
    ImageIcon profileIcon;


    public SwingUI(){
        super();
        profileDescription = "Nome: ---\nMatricula: ---\n Email: ---\n Curso: ---";
    }

    public int mainMenu() {
        // return 0 to register and 1 to search
        title = "Estudantes";
        switch (showOptionDialog(null, "O que fazer?", title ,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Cadastrar Estudantes", "Procurar estudante"}, "Cadastrar Estudantes")) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return -1;
        }
    }

    public String[] showRegisterDialogs(Object rootObject,
    Method nameChecker, Method getName,
    Method emailChecker, Method getEmail,
    Method courseChecker, Method getCourse,
    Method idChecker, Method getId,
    AvaliableCourses[] courseOptions) {
        this.title = "Cadastro";
        try{
            nameChecker.invoke(rootObject, showInputDialog(null, profileDescription, this.title, JOptionPane.INFORMATION_MESSAGE));
            String name = (String) getName.invoke(rootObject);
            profileDescription = profileDescription.replaceFirst("---", name);
            
            idChecker.invoke(rootObject, showInputDialog(null, profileDescription, title, JOptionPane.INFORMATION_MESSAGE));
            String id = (String) getId.invoke(rootObject);
            profileDescription = profileDescription.replaceFirst("---", id);
            
            emailChecker.invoke(rootObject, showInputDialog(null, profileDescription, title, JOptionPane.INFORMATION_MESSAGE));
            String email = (String) getEmail.invoke(rootObject);
            profileDescription = profileDescription.replaceFirst("---", email);
            
            courseChecker.invoke(rootObject, courseOptions[showOptionDialog(null, profileDescription, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, courseOptions, courseOptions[0])]);
            AvaliableCourses course = (AvaliableCourses) getCourse.invoke(rootObject);
            profileDescription = "Nome: ---\nMatricula: ---\n Email: ---\n Curso: ---";
            return new String[]{name, id, email, course.toString()};
        }
        
        catch(Exception assignException){
            SwingUI.showErrorMessage(assignException);
            profileDescription = "Nome: ---\nMatricula: ---\n Email: ---\n Curso: ---";
            return showRegisterDialogs(rootObject, nameChecker, getName, emailChecker, getEmail, courseChecker, getCourse, idChecker, getId, courseOptions);
            
        }
    }
    public String showSearchDialog(){
        return showInputDialog(null, "Digite uma Matrícula", "Procurar estudante", JOptionPane.QUESTION_MESSAGE);
    }
    public void showProfileDialog(String name, String id, String email, String course) {
        this.title = "Perfil";
        profileIcon = new ImageIcon(new ImageIcon("./src/View/img/profile.ico").getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_FAST));
        for (String param : new String[]{name, id, email, course}) {
            this.profileDescription = this.profileDescription.replaceFirst("---", param);
        }
        showMessageDialog(null, profileDescription, title, JOptionPane.PLAIN_MESSAGE, profileIcon);
    }

    public static void showErrorMessage(Exception error) {
        showMessageDialog(null, "HOUVE UM ERRO: \n" + error.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
    }
    
}
