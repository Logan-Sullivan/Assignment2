import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/input.txt");
        int linesRead = 0;
        try (Scanner fileReader = new Scanner(file)){
            while (fileReader.hasNextLine()){
                String text = fileReader.nextLine();
                if (linesRead > 0){
                    Boolean StudentIDFound = false;
                    Boolean NameFound = false;
                    Boolean allTestsFound = false;
                    String Student_ID = "";
                    String Student_Name = "";
                    String Temp = "";
                    int[] tests = {0,0,0};
                    int z = 0;
                    for (int i = 0; i < text.length(); i++) {
                        if (!StudentIDFound && (text.charAt(i) != ' ')){
                            Student_ID += text.charAt(i);
                        } else {
                            StudentIDFound = true;
                            if (!NameFound && !Character.isDigit(text.charAt(i))){
                                if (text.charAt(i) != ','){
                                    Student_Name += text.charAt(i);
                                }
                            } else {
                                NameFound = true;
                                    if (!allTestsFound){
                                        if (text.charAt(i) == ' '){
                                            tests[z] = Integer.parseInt(Temp);
                                            z++;
                                            if (z == 2){
                                                allTestsFound = true;
                                            }
                                        } else {
                                            Temp += text.charAt(i);
                                        }
                                    }
                            }
                        }
                    }
                }
                
                linesRead++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        for (int i = 0; i < 1; i++) {

        }
    }
}
class Student{
    String Student_ID;
    String Student_Name;
    int Test1;
    int Test2;
    int Test3;
    double Percent;
    char Grade;

    public Student(String Student_ID, String Student_Name, int Test1, int Test2, int Test3){
        this.Student_ID = Student_ID;
        this.Student_Name = Student_Name;
        this.Test1 = Test1;
        this.Test2 = Test2;
        this.Test3 = Test3;
        this.Percent = ((Test1+Test2+Test3)/3.0);
        if (this.Percent >= 90){
            this.Grade = 'A';
        } else if (this.Percent >= 80) {
            this.Grade = 'B';
        } else if (this.Percent >= 70) {
            this.Grade = 'C';
        } else if (this.Percent >= 60){
            this.Grade = 'D';
        } else {
            this.Grade = 'F';
        }
    }
    public void PrintStudent(){
        System.out.printf("ID: %s\nName: %s\nTest1: %n\nTest2: %n\nTest3: %n\nPercent: %%%.2f%n\nGrade: %C",this.Student_ID, this.Student_Name, this.Test1, this.Test2, this.Test3,this.Percent, this.Grade);
    }
}


/* Driver Program
*
* Load records from input.txt loading each record into a student object
* Calc the %score and grade for that Student Object Then place it into Academic_Class Arraylist
*
* list the records from input including %score and grades
*
* Delete students 42P4 and 45A3
*
* list the records again after they are dropped
*
* add new students to arraylist
*
* calc grades and sort from lower to higher %score
*
* print ArrayList
*
*
* */



/* Req Functions:
* void AddStudent(Arraylist, Academic_Class, Student Obj)
* adds students to/from arraylist
*
* void DeleteStudent(Arraylist Academic_Class, String StudentID)
* Deletes student from arraylist with StudentID
*
* void SortLarge(Arraylist Academic_Class)
* bubble sort arraylist larger to smaller based on %score
*
*
* */