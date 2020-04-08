// Justin Montoya - 500885035

public class CreditCourse extends Course {
	private String semester;
	public double grade;
	public boolean active;

	// add a constructor method with appropriate parameters
	public CreditCourse(String name, String code, String descr, String fmt, String semester, double grade) {
		// should call the super class constructor
		super(name, code, descr, fmt);
		// add code
		this.semester = semester;
		this.grade = grade;
		this.active = true;

	}

	// getter method for boolean active
	public boolean getActive() {
		// add code and remove line below
		return active;
	}

	// sets course to active
	public void setActive() {
		// add code
		this.active = true;
	}

	// set course to inactive
	public void setInactive() {
		// add code
		this.active = false;
	}

	// returns course code and name, semester, and numeric grade
	public String displayGrade() {
		// Change line below and print out info about this course plus which semester
		// and the grade achieved
		// make use of inherited method in super class
		return getInfo() + " " + semester + " Grade " + convertNumericGrade(grade);
	}
}