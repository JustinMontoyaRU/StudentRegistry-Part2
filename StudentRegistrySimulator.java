// Justin Montoya - 500885035

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentRegistrySimulator {
	public static void main(String[] args){
		Registry registry = null;
		Scheduler scheduler = null;
		try{
			registry = new Registry();
			scheduler = new Scheduler(registry.getCourses());
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
			return;
		} catch (IOException e){
			System.out.println("Bad File. Please fix error in txt file.");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine()) {
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals(""))
				continue;

			Scanner commandLine = new Scanner(inputLine);
			String command = commandLine.next();

			if (command == null || command.equals(""))
				continue;

			else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST")) {
				registry.printAllStudents();
			} else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
				return;

			// [REG] AddStudent
			else if (command.equalsIgnoreCase("REG")) {
				// register a new student in registry
				try {
					// get name and student id string
					// e.g. reg JohnBoy 74345
					String studentName = commandLine.next();
					String studentID = commandLine.next();
					// Checks:
					// ensure name is all alphabetic characters
					if (isStringOnlyAlphabet(studentName) == true) {
						// ensure id string is all numeric characters
						if (isNumeric(studentID) == true) {
							registry.addNewStudent(studentName, studentID);
						} else {
							System.out.println("Error: ID not valid");
						}
					} else {
						System.out.println("Error: Name not valid");
					}
				} catch (Exception e) {

				}
			}

			// [DEL] DeleteStudent
			else if (command.equalsIgnoreCase("DEL")) {
				// delete a student from registry
				try {
					// get student id
					String deleteID = commandLine.next();
					// ensure numeric
					if (isNumeric(deleteID) == true) {
						// remove student from registry
						registry.removeStudent(deleteID);
					} else {
						System.out.println("Error: ID not valid");
					}
				} catch (Exception e) {

				}
			}

			// [ADDC] AddCourse
			else if (command.equalsIgnoreCase("ADDC")) {
				// add a student to an active course
				try {
					// get student id and course code strings
					String addID = commandLine.next();
					String addCode = commandLine.next();
					if (isNumeric(addID) == true) {
						// add student to course (see class Registry)
						registry.addCourse(addID, addCode);
					} else {
						System.out.println("Error: ID not valid");
					}
				} catch (Exception e) {

				}
			}

			// [DROPC] DropCourse
			else if (command.equalsIgnoreCase("DROPC")) {
				try {
					// get student id and course code strings
					String addID = commandLine.next();
					String addCode = commandLine.next();
					if (isNumeric(addID) == true) {
						// drop student from course (see class Registry)
						registry.dropCourse(addID, addCode);
					} else {
						System.out.println("Error: ID not valid");
					}
				} catch (Exception e) {

				}
			}

			// [PAC] PrintActiveCourses
			else if (command.equalsIgnoreCase("PAC")) {
				try {
					// print all active courses
					registry.printActiveCourses();
				} catch (Exception e) {

				}
			}

			// [PCL] PrintClassList
			else if (command.equalsIgnoreCase("PCL")) {
				try {
					// get course code string
					// print class list (i.e. students) for this course
					String printCodeList = commandLine.next();
					registry.printClassList(printCodeList);
				} catch (Exception e) {

				}

			}

			// [PGR] PrintGradeReport
			else if (command.equalsIgnoreCase("PGR")) {
				try {
					// get course code string
					String codeList = commandLine.next();
					// print name, id and grade of all students in active course
					registry.printGrades(codeList);
				} catch (Exception e) {

				}
			}

			// [PSC] PrintStudentCourses
			else if (command.equalsIgnoreCase("PSC")) {
				try {
					// get student id string
					String printCourse = commandLine.next();
					if (isNumeric(printCourse) == true) {
						// print all credit courses of student
						registry.printStudentCourses(printCourse);
					} else {
						System.out.println("Error: ID not valid");
					}
				} catch (Exception e) {

				}
			}

			// [PST] PrintStudentTranscript
			else if (command.equalsIgnoreCase("PST")) {
				try {
					// get student id string
					String printTranscript = commandLine.next();
					if (isNumeric(printTranscript) == true) {
						// print student transcript
						registry.printStudentTranscript(printTranscript);
					} else {
						System.out.println("Error: ID not valid");
					}
				} catch (Exception e) {

				}
			}

			// [SFG] SetFinalGrade
			else if (command.equalsIgnoreCase("SFG")) {
				// set final grade of student
				try {
					// get course code, student id, numeric grade
					String courseCode = commandLine.next();
					String studentID = commandLine.next();
					double gradeNum = commandLine.nextDouble();
					// use registry to set final grade of this student (see class Registry)
					registry.setFinalGrade(courseCode, studentID, gradeNum);
				} catch (Exception e) {

				}
			}

			// [SCH] set time and day
			else if(command.equalsIgnoreCase("SCH")){
				try{
					String courseCode = commandLine.next();
					String day = commandLine.next();
					int start = commandLine.nextInt();
					int duration = commandLine.nextInt();
					scheduler.setDayAndTime(courseCode, day, start, duration);
				} catch(Exception e){

				}
				
				
			}

			// [CSCH]  clear schedule
			else if(command.equalsIgnoreCase("CSCH")){
				try{
					String courseCode = commandLine.next();
					scheduler.clearSchedule(courseCode);
				} catch(Exception e){

				}
			}

			// [PSCH] print schedule
			else if(command.equalsIgnoreCase("PSCH")){
				try{
					scheduler.printSchedule();
				} catch(Exception e){

				}
				
			}

			else if (command.equalsIgnoreCase("SCN")) {
				// get course code
				String codeName = commandLine.next();
				// sort list of students in course by name (i.e. alphabetically)
				// see class Registry
				registry.sortCourseByName(codeName);
			} else if (command.equalsIgnoreCase("SCI")) {
				try {
					// get course code
					String codeID = commandLine.next();
					// sort list of students in course by student id
					// see class Registry
					registry.sortCourseById(codeID);
				} catch (Exception e) {

				}
			}
			System.out.print("\n>");
		}
	}

	private static boolean isStringOnlyAlphabet(String str) {
		// write method to check if string str contains only alphabetic characters
		return str.matches("[a-zA-Z]+");
	}

	public static boolean isNumeric(String str) {
		// write method to check if string str contains only numeric characters
		return str.matches("[0-9]+");
	}
} 