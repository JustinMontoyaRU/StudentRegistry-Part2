// Justin Montoya - 500885035

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Registry{
	private TreeMap<String, Student> students = new TreeMap<String, Student>();
	private TreeMap<String, ActiveCourse> courses = new TreeMap<String, ActiveCourse>();

	public Registry() throws FileNotFoundException, IOException{
		// Add some students
		// in A2 we will read from a file
		File file = new File("students.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()){
			String name = scanner.next();
			String id = scanner.next();
			if(!name.matches("[a-zA-Z]+")){
				throw new IOException("Bad File");
			}
			if(!(id.matches("[0-9]+") && id.length() == 5)){
				throw new IOException("Bad File");
			}
			students.put(id, new Student(name, id));
		}
		// sort the students alphabetically - see class Student

		ArrayList<Student> list = new ArrayList<Student>();

		// CPS209
     	String courseName = "Computer Science II";
     	String courseCode = "CPS209";
     	String descr = "Learn how to write complex programs!";
     	String format = "3Lec 2Lab";
    	courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
     	// CPS511
     	courseName = "Computer Graphics";
     	courseCode = "CPS511";
     	descr = "Learn how to write cool graphics programs";
     	format = "3Lec";
     	courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
     	// CPS643
     	courseName = "Virtual Reality";
     	courseCode = "CPS643";
     	descr = "Learn how to write extremely cool virtual reality programs";
     	format = "3Lec 2Lab";
     	courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
     	// CPS706
     	courseName = "Computer Networks";
     	courseCode = "CPS706";
     	descr = "Learn about Computer Networking";
     	format = "3Lec 1Lab";
     	courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
     	// CPS616
     	courseName = "Algorithms";
     	courseCode = "CPS616";
     	descr = "Learn about Algorithms";
     	format = "3Lec 1Lab";
		 courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	}

	public TreeMap<String, ActiveCourse> getCourses(){
		return courses;
	}

	// Add new student to the registry (students arraylist above)
	public boolean addNewStudent(String name, String id) {
		// Create a new student object
		Student newStudent = new Student(name, id);
		for (Map.Entry<String, Student> entry : students.entrySet()) {
			// check to ensure student is not already in registry
			// make use of equals method in class Student
			if (entry.getKey().equals(id)) {
				// otherwise return false
				System.out.println("Student " + name + " already registered");
				return false;
			}
		}
		// if not, add them and return true,
		students.put(id, newStudent);
		return true;
	}

	// Remove student from registry
	public boolean removeStudent(String studentId) {
		// Find student in students arraylist
		// If found, remove this student and return true
		for (Map.Entry<String, Student> entry : students.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(studentId)) {
				students.remove(studentId);
				return true;
			}
		}
		return false;
	}

	// Print all registered students
	public void printAllStudents() {
		for (Map.Entry<String, Student> entry : students.entrySet()) {
			System.out.println("ID: " + entry.getKey() + " Name: " + entry.getValue().getName());
		}
	}

	// Given a studentId and a course code, add student to the active course
	public void addCourse(String studentId, String courseCode) {
		// Find student object in registry (i.e. students arraylist)
		for (Map.Entry<String, Student> entry : students.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(studentId)) {
				for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
					// Check if student has already taken this course in the past Hint: look at
					// their credit course list
					// If not, then find the active course in courses array list using course code
					// If active course found then check to see if student already enrolled in this
					// course
					if (entry2.getKey().equalsIgnoreCase(courseCode)) {
						if (entry.getValue().getActive() == false) {
							// If not already enrolled
							// add student to the active course
							entry2.getValue().addStudent(entry.getValue());
							// add course to student list of credit courses with initial grade of 0
							entry.getValue().addCourse(entry2.getValue().getName(), courseCode, entry2.getValue().getDesc(),
									entry2.getValue().getFormat(), entry2.getValue().getSemester(), 0);
						}
					}
				}
			}
		}
	}

	// Given a studentId and a course code, drop student from the active course
	public void dropCourse(String studentId, String courseCode) {
		for (Map.Entry<String, Student> entry : students.entrySet()) {
			// Find the student in the list of students for this course
			if (entry.getKey().equalsIgnoreCase(studentId)) {
				for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
					// Find the active course
					if (entry2.getKey().equalsIgnoreCase(courseCode)) {
						if (entry.getValue().getActive() == false) {
							// If student found:
							// remove the student from the active course
							entry2.getValue().removeStudent(entry.getValue());
							// remove the credit course from the student's list of credit courses
							entry.getValue().removeActiveCourse(courseCode);
						}
					}
				}
			}
		}
	}

	// Print all active courses
	public void printActiveCourses() {
		for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
			ActiveCourse ac = entry2.getValue();
			System.out.println(ac.getDescription());
		}
	}

	// Print the list of students in an active course
	public void printClassList(String courseCode) {
		for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
			if (entry2.getKey().equalsIgnoreCase(courseCode)) {
				entry2.getValue().printClassList();
			}
		}
	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseByName(String courseCode) {
		for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
			if (entry2.getKey().equalsIgnoreCase(courseCode)) {
				entry2.getValue().sortByName();
			}
		}
	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseById(String courseCode) {
		for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
			if (entry2.getKey().equalsIgnoreCase(courseCode)) {
				entry2.getValue().sortById();
			}
		}
	}

	// Given a course code, find course and print student names and grades
	public void printGrades(String courseCode) {
		for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
			if (entry2.getKey().equalsIgnoreCase(courseCode)) {
				entry2.getValue().printGrades();
			}
		}
	}

	// Given a studentId, print all active courses of student
	public void printStudentCourses(String studentId) {
		for (Map.Entry<String, Student> entry : students.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(studentId)) {
				for (int j = 0; j < entry.getValue().courses.size(); j++) {
					System.out.println(entry.getValue().courses.get(j).getDescription()); //maybe broken
				}
			}
		}
	}

	// Given a studentId, print all completed courses and grades of student
	public void printStudentTranscript(String studentId) {
		for (Map.Entry<String, Student> entry : students.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(studentId)) {
				entry.getValue().printTranscript();
			}
		}
	}

	// Given a course code, student id and numeric grade
	public void setFinalGrade(String courseCode, String studentId, double grade) {
		// find the active course
		for (Map.Entry<String, ActiveCourse> entry2 : courses.entrySet()) {
			if (entry2.getKey().equalsIgnoreCase(courseCode)) {
				// If found, find the student in class list
				for (int j = 0; j < entry2.getValue().getStudent().size(); j++) {
					if (entry2.getValue().getStudent().get(j).getId().equalsIgnoreCase(studentId)) {
						// then search student credit course list in student object and find course
						for (int k = 0; k < entry2.getValue().getStudent().get(j).courses.size(); k++) {
							if (entry2.getValue().getStudent().get(j).courses.get(k).getCode()
									.equalsIgnoreCase(courseCode)) {
								// set the final grade of the student
								entry2.getValue().getStudent().get(j).courses.get(k).grade = grade;
								// set the grade in credit course and set credit course inactive
								entry2.getValue().getStudent().get(j).courses.get(k).setInactive();
							}
						}
					}
				}
			}
		}
	}
}