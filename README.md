# StudentRegistry-Part2
This program is an extended version of StudentRegistry-Part1.

*NEW* Added scheduler class that creates a timetable for the various courses. The student registry can now read from a text file. With the scheduler class, there are three more commands in StudentRegistrySimulator:

a. "SCH" : places a course into the timetable. Position in timetable depends on day, time, and duration that was inputted.

b. "CSCH" : clear the schedule of the specified course.

c. "PSCH" : prints the timetable.

StudentRegistrySimulator is the driver class and reads a line of input from the user. Most input lines contain a single word that represents a command. Some lines contain a command word and some parameter string. Here are the commands:

a. “L” : list all the students in the registry.

b. “Q” : quit out of the program.

c. “REG” : register a student.

d. “DEL”: deletes a student from the registry.

e. “ADDC”: adds a student to an active course

f. “DROPC”: drops a student from an active course

g. “PAC” : prints all active course

h. “PCL” : prints class list for an active course

i. “PGR” : prints student id and grade for all students in an active course

j. “PSC” : prints all credit courses for a student

k. “SFG” : Set final grade of a student in a course

l. “SCN” : sort list of students in a course by student name

m. “SCI” : sort list of students in a course by student id
