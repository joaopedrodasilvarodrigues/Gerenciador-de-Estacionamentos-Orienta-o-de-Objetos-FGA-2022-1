package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private String name, email, id;
    public enum AvaliableCourses{
        SOFTWARE(0), ELETRÔNICA(1), AUTOMOTIVA(2), ENERGIA(3);
        private int courseId;
    
        private AvaliableCourses(final int courseId){
            this.courseId = courseId;
        }
    

        public int getCourseId() {
            return courseId;
        }
        public boolean compareId(int courseId){
            return (getCourseId() == courseId) ? true: false;
        }

    };
    private AvaliableCourses course;
    

    public String getName() {
        return name;
    }public String getId() {
        return id;
    }public String getEmail() {
        return email;
    }public AvaliableCourses getCourse() {
        return course;
    }

    public void setName(String name) throws Exception {
        // delete unnecessary blank spaces.
        String[] sepName = name.strip().split(" "); name = "";
        for (String namePart : sepName) {
            if (namePart.isBlank() | namePart.isEmpty()) {
                continue;
            }
            else{
                name += namePart + " ";
            }
        }
        name = name.stripTrailing();
        // check if there are at least two words
        if (name.split(" ").length < 2) {
            throw new Exception("insira o nome completo");
        }
        String invalidSymbols = "1234567890";
        for (int index = 0; index < invalidSymbols.length(); index++) {
            if (name.contains(Character.toString(invalidSymbols.charAt(index)))) {
                throw new Exception("números não são aceitos");
            }
        }
        this.name = name;
    }
    public void setId(String id) throws Exception {
        id = id.replaceAll(" ", "");
        if (id.length() != 9){
            throw new Exception("Quantidade de números inválida");
        }
        try {
            for (int index = 0; index < id.length(); index++) {
                Integer.valueOf(Character.toString(id.charAt(index)));
            }
        } catch (NumberFormatException noNum) {
            throw new Exception("Só números são aceitos na matrícula");
        }
        this.id = id;
    }
    public boolean setCourse(AvaliableCourses course) throws Exception {
        AvaliableCourses[] courses = AvaliableCourses.values();
        for (AvaliableCourses option : courses) {
            if (course.equals(option)) {
                this.course = course;
                return true;
            }
        }
        throw new Exception("Não existe um curso assim");

    }
    public void setEmail(String email) throws Exception {
        String beginning = email.substring(0, email.indexOf("@") + 1);
        if(email.indexOf(" ") != -1 |
        email.indexOf("@") == -1 |
        email.indexOf("@") != email.lastIndexOf("@")
        ){
            throw new Exception("email esrito errado");
        }
        else if(!email.endsWith(".com") &
        !email.endsWith(".br") &
        !email.endsWith(".org")){
            throw new Exception("email escrito errado");
        }
        else if (beginning.indexOf(".") != -1 |
        beginning.indexOf(",") != -1 |
        beginning.indexOf(" ") != -1) {
            throw new Exception("email escrito errado");
        } else {
            this.email = email;
        }
    }
    public static String[] getStudentInfo(String id) throws Exception {
        Scanner scan = new Scanner(new File("src/Model/students.csv"));
        String[] info;
        ArrayList<String> students = new ArrayList<String>();
        while (scan.hasNextLine()) {
            students.add(scan.nextLine());
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).contains(id)){
                info = students.get(i).split(", ");
                scan.close();
                return info;
            }
        }
        throw new Exception("Matrícula não encontrada");

    }
    public static void setStudentInfo(String name, String id, String email, AvaliableCourses course) throws IOException{
        FileWriter writer = new FileWriter(new File("./src/Model/students.csv"), true);
        writer.write(name + ", " + id + ", " + email + ", " + course.toString() + "\n");
        writer.flush();
        writer.close();
    }
}
