// Justin Montoya - 500885035

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
public class ActiveCourse extends Course {
   private ArrayList<Student> students;
   private String semester;
   private int lectureStart;
   private int lectureDuration;
   private String lectureDay;

   // Add a constructor method with appropriate parameters
   // see class Registry to see how an ActiveCourse object is created and used
   public ActiveCourse(String name, String code, String descr, String fmt, String semester,
         ArrayList<Student> students) {
      // should call super class constructor to initialize inherited variables
      super(name, code, descr, fmt);
      this.semester = semester;
      // make sure to *copy* students array list being passed in into new arraylist of
      // students
      this.students = new ArrayList<Student>(students);
      this.lectureStart = 0;
      this.lectureDuration = 0;
      this.lectureDay = "";
   }

   //sets day
   public void setDay(String day){
      this.lectureDay = day;
   }

   //sets start time
   public void setStart(int start){
      this.lectureStart = start;
   }

   //sets duration
   public void setDuration(int duration){
      this.lectureDuration = duration;
   }

   //gets day
   public String getDay(){
      return lectureDay;
   }

   //gets start time
   public int getStart(){
      return lectureStart;
   }

   //gets duration
   public int getDuration(){
      return lectureDuration;
   }

   // getter method for the ArrayList of students
   public ArrayList<Student> getStudent() {
      return students;
   }

   public String getSemester() {
      return semester;
   }

   // adds student to ArrayList students
   public void addStudent(Student student) {
      students.add(student);
   }

   // removes student from ArrrayList students
   public void removeStudent(Student student) {
      students.remove(student);
   }

   // Prints the students in this course (name and student id)
   public void printClassList() {
      for (int i = 0; i < students.size(); i++) {
         System.out.println("Student ID: " + students.get(i).getId() + " Name: " + students.get(i).getName());
      }
   }

   // Prints the grade of each student in this course (along with name and
   // studentid)
   public void printGrades() {
      for (int i = 0; i < students.size(); i++) {
         System.out.println(
               students.get(i).getId() + " " + students.get(i).getName() + " " + students.get(i).getGrade(getCode()));
      }
   }

   // Returns the numeric grade in this course for this student
   public double getGrade(String studentId) {
      // Search the student's list of credit courses to find the course code that
      // matches this active course
      for (int i = 0; i < students.size(); i++) {
         if (students.get(i).getId().equalsIgnoreCase(studentId)) {
            // return the grade stored in the credit course object
            return students.get(i).courses.get(i).grade;
         }
      }
      // If student not found in course, return 0
      return 0;
   }

   // Returns a String containing the course information as well as the semester
   // and the number of students enrolled in the course
   public String getDescription() {
      // must override method in the superclass Course and use super class method
      // getDescription()
      return super.getDescription() + " " + semester + " Enrolment: " + students.size() + "\n";
   }

   // Sort the students in the course by name using the Collections.sort() method
   // with appropriate arguments
   // Make use of a private Comparator class below
   public void sortByName() {
      Collections.sort(students, new NameComparator());
   }

   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator<Student> {
      public int compare(Student student1, Student student2) {
         return student1.getName().compareTo(student2.getName());
      }
   }

   // Sort the students in the course by student id using the Collections.sort()
   // method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortById() {
      Collections.sort(students, new IdComparator());
   }

   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator<Student> {
      public int compare(Student student1, Student student2) {
         return student1.getId().compareTo(student2.getId());
      }
   }
}