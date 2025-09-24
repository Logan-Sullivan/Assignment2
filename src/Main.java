/* Class Structures

 * class Students
    * Data:
       String Student_ID;
       String Student_Name;
       int Test1;
       int Test2;
       int Test3;
       int Total_Hr;
       int Class_GP;
       double GPA;
       double Cumulative_GPA;
       String Year_Rank;
       double Percent;
       char Grade;
    * Functions:
       public void PrintStudent();
       public int compareTo(Students obj);
 * end of Students

 * class ArrayListManager
    * Functions:
       void addStudent(ArrayList<Students> Academic_Class, Students Obj)
       void deleteStudent(ArrayList<Students> Academic_Class, String Student_ID)
       void printClass(ArrayList<Students> Academic_Class)
       void SortLarge(ArrayList<Students> x)
 * end of ArrayListManager
 * */

/* New Additions for Part 2
*  Edited the file reader to get the GPA and Total Hr from new input file and change the
*  indexes of tests and end of name
*
* Edited added students to include Tota_Hr and GPA
*
* Added new variables (Total_Hr, Class_GP, GPA, Cumulative_GPA, and Year_Rank) to Students class and constructor
* Added Year rank based on Total_Hr to Students class
* Added calculation of new gpa to Students class
*
* Finally edit the printout to include new vars
* */

/* Opening Statement

 * This program utilizes ArrayLists to arrange data for a class of students
 * the goals this program accomplishes is the following
 *
 * Loading records from input.txt loading each record into a student object
 *
 * Calc the %score and grade for that Student Object and place it into Academic_Class Arraylist
 *
 * list the records from input including %score and grades
 *
 * Delete students 42P4 and 45A3
 *
 * list the records again after they are dropped
 *
 * add new students to the Academic_Class arraylist
 *
 * calc grades and sort from lower to higher %score
 *
 * print the ArrayList one final time
 *
 * To complete these goals I created 2 classes being Students and ArrayListManager
 *
 * The Students class creates objects that store each individual students data
 * it also includes a function for printing a students data (PrintStudent)
 * and a function for comparing students %scores (compareTo)
 *
 * The ArrayListManager assists in all ArrayList tasks
 *
 * First the function addStudents adds students to the ArrayList
 *
 * then similarly the deleteStudent function removes students from the ArrayList using a Student_ID to find
 * the student
 *
 * I also added a printClass function to loop through and print all students in the class
 *
 * Then the SortLarge function uses a bubble sort algorithm with the Students compareTo function to sort the
 * class from highest to lowest %grade
 *
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //create student ArrayList
        ArrayList<Students> Academic_Class = new ArrayList<>();
        //create arrayListManager
        ArrayListManager arrayListManager = new ArrayListManager();

        //regex that finds all commas and spaces for splitting later
        String regex = "[, \\s]";
        //Create File object
        File file = new File("src/input.txt");
        // initialize iterator
        int linesRead = 0;
        //open input file with scanner
        try (Scanner fileReader = new Scanner(file)){
            while (fileReader.hasNextLine()){
                //scan a line and put string into text
                String text = fileReader.nextLine();
                //skip header line
                if (linesRead > 0){
                    //Create an array of words splitting at every comma and space
                    String[] formattedText = text.split(regex);
                    //first in the array is always the id
                    String ID = formattedText[0];
                    //the last 5 elements are always the GPA, Total_Hr, and then the test scores
                    double GPA = Double.parseDouble(formattedText[formattedText.length-1]);
                    int Total_Hr = Integer.parseInt(formattedText[formattedText.length-2]);
                    int test1 = Integer.parseInt(formattedText[formattedText.length-3]);
                    int test2 = Integer.parseInt(formattedText[formattedText.length-4]);
                    int test3 = Integer.parseInt(formattedText[formattedText.length-5]);

                    //initialize name var with first name and a space for formatting
                    String name = formattedText[1] + " ";

                    //start at 2nd in array cause first is id and end before test scores start at -5
                    for (int i = 2; i < formattedText.length-5; i++){
                        //add text to name var
                        name += formattedText[i];
                    }// end string reading loop

                    //add new Students object to Academic_Class with data from read line
                    arrayListManager.addStudent(Academic_Class, new Students(ID, name, test1, test2, test3,Total_Hr,GPA)); // add newStudent to Academic_Class ArrayList with Manager
                }//end linesRead if statement

                //iterate lines read
                linesRead++;
            } //end file reader loop

            //check that file actually exists
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }//end try catch

        System.out.println("\nbefore any changes");
        //print class
        arrayListManager.printClass(Academic_Class);

        //remove students 42P4 and 45A3
        arrayListManager.deleteStudent(Academic_Class, "42P4");
        arrayListManager.deleteStudent(Academic_Class, "45A3");

        System.out.println("\nAfter removing students");
        //print class again
        arrayListManager.printClass(Academic_Class);

        //add new students
        arrayListManager.addStudent(Academic_Class, new Students("67T4", "Clouse B", 80,75,98,102,3.65));
        arrayListManager.addStudent(Academic_Class, new Students("45P5", "Garrison J", 75,78,72,39,1.85));
        arrayListManager.addStudent(Academic_Class, new Students("89P0", "Singer A", 85,95,99,130,3.87));

        System.out.println("\nAfter adding students");
        //print again
        arrayListManager.printClass(Academic_Class);

        //sort by %score
        arrayListManager.SortLarge(Academic_Class);

        System.out.println("\nAfter sorting students");
        //print again
        arrayListManager.printClass(Academic_Class);
    }
}//end of main
class Students{
    //initialize vars
    String Student_ID;
    String Student_Name;
    int Test1;
    int Test2;
    int Test3;
    int Total_Hr;
    int Class_GP;
    double GPA;
    double Cumulative_GPA;
    String Year_Rank;
    double Percent;
    char Grade;

    //constructor
    public Students(String Student_ID, String Student_Name, int Test1, int Test2, int Test3,int Total_Hr, double GPA){
        //apply assigned vars
        this.Student_ID = Student_ID;
        this.Student_Name = Student_Name;
        this.Test1 = Test1;
        this.Test2 = Test2;
        this.Test3 = Test3;
        this.Total_Hr = Total_Hr;
        this.GPA = GPA;

        //calc Percent
        this.Percent = ((Test1+Test2+Test3)/3.0);

        //find year rank
        if (this.Total_Hr <= 30){
            this.Year_Rank = "FR";
        } else if (this.Total_Hr <= 60){
            this.Year_Rank = "SO";
        } else if (this.Total_Hr <= 90){
            this.Year_Rank = "JR";
        } else {
            this.Year_Rank = "SR";
        }

        //find letter grade and get Class_GP
        if (this.Percent >= 90){
            this.Grade = 'A';
            this.Class_GP = 4;
        } else if (this.Percent >= 80) {
            this.Grade = 'B';
            this.Class_GP = 3;
        } else if (this.Percent >= 70) {
            this.Grade = 'C';
            this.Class_GP = 2;
        } else if (this.Percent >= 60){
            this.Grade = 'D';
            this.Class_GP = 1;
        } else {
            this.Grade = 'F';
            this.Class_GP = 0;
        }

        //calc Cumulative_GPA with found Class_GP
        this.Cumulative_GPA = ((this.GPA*this.Total_Hr)+(2)*this.Class_GP)/(this.Total_Hr+2);
    }

    //default print statement using printf for formatting
    public void PrintStudent(){
        System.out.printf("%s, %s, %s, %d, %d, %d, %.2f%%, %C, %d, %.2f, %.2f\n",this.Student_ID, this.Student_Name,this.Year_Rank, this.Test1, this.Test2, this.Test3,this.Percent, this.Grade,this.Total_Hr,this.GPA,this.Cumulative_GPA);
    }//end of PrintStudent

    public int compareTo(Students obj){
        //if Student calling method is smaller return 1 else return 0
        if(this.Percent < obj.Percent){
            return 1;
        }
        return 0;
    }//end of compareTo

}//end of Students

class ArrayListManager{
    //adds student object to ArrayList
    void addStudent(ArrayList<Students> Academic_Class, Students Obj){
        Academic_Class.add(Obj);
    }//end of addStudent
    //removes student object from ArrayList
    void deleteStudent(ArrayList<Students> Academic_Class, String Student_ID){
        for (int i = 0; i < Academic_Class.size(); i++){
            if (Student_ID.equals(Academic_Class.get(i).Student_ID)){
                Academic_Class.remove(i);
            }
        }//end of for loop
    }//end of deleteStudent
    //Prints a header and loops through ArrayList to print all students within
    void printClass(ArrayList<Students> Academic_Class){
        //print a header
        System.out.println("\nStudent_ID Student_Name Year_Rank Test1 Test2 Test3 Avg Grade Total_Hours Old_GPA Cumulative_GPA");
        //for each element in Academic_Class print the student
        for (Students academicClass : Academic_Class) {
            academicClass.PrintStudent();
        }//end of for loop
    }//end of PrintClass

    // bubble sorts students in ArrayList from highest to lowest %grade
    void SortLarge(ArrayList<Students> x){
        //default that something has switched
        boolean switched = true;
        //initialize temp var
        Students temp;

        while (switched){
            //make switched false so that if nothing switches while loop ends
            switched = false;

            //iterate through ArrayList and use compareTo to compare students %scores
            for (int i = 0; i < x.size()-1; i++){
                //if student i in ArrayList's %score is less than student i+1 swap the students in the ArrayList and set switched to true
                if (x.get(i).compareTo(x.get(i + 1)) == 1) {
                    temp = x.get(i);
                    x.set(i, x.get(i + 1));
                    x.set(i + 1, temp);
                    switched = true;
                }
            }//end of for loop
        }//end of while loop
    }//end of SortLarge
}//end of ArrayListManager