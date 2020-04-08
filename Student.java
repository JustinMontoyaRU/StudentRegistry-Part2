// Justin Montoya - 500885035

import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student {
  private String name;
  private String id;
  public ArrayList<CreditCourse> courses;
  public boolean active;

  public Student(String name, String id) {
    this.name = name;
    this.id = id;
    courses = new ArrayList<CreditCourse>();
  }

  // getter method for double grade from Course class
  public double getGrade(String courseCode) {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
        return courses.get(i).grade;
      }
    }
    return 0;
  }

  // getter method for boolean active
  public boolean getActive() {
    return active;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  // add a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format, String sem, double grade) {
    // create a CreditCourse object
    CreditCourse studentCourse = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
    // set course active
    studentCourse.setActive();
    // add to courses array list
    courses.add(studentCourse);
  }

  // Prints a student transcript
  // see class CreditCourse for useful methods
  public void printTranscript() {
    // Prints all completed (i.e. non active) courses for this student (course code,
    // course name,
    // semester, letter grade
    for (int i = 0; i < courses.size(); i++) {
      System.out.println(courses.get(i).displayGrade());
    }
  }

  // Prints all active courses this student is enrolled in
  // see variable active in class CreditCourse
  public void printActiveCourses() {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getActive()) {
        System.out.println(courses.get(i).getInfo());
      }
    }
  }

  // Drop a course (given by courseCode)
  public void removeActiveCourse(String courseCode) {
    for (int i = 0; i < courses.size(); i++) {
      // only remove it if it is an active course
      if (courses.get(i).getActive()) {
        // Find the credit course in courses arraylist above and remove it
        if (courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
          courses.remove(i);
        }
      }
    }
  }

  public String toString() {
    return "Student ID: " + id + " Name: " + name;
  }

  // override equals method inherited from superclass Object
  public boolean equals(Object other) {
    // Hint: you will need to cast other parameter to a local Student reference
    // variable
    var oth = (Student) other;
    // if student names are equal *and* student ids are equal (of "this" student
    // and "other" student) then return true
    if (this.name.equals(oth.name) && this.id.equals(oth.id)) {
      return true;
    }
    // otherwise return false
    return false;
  }
}