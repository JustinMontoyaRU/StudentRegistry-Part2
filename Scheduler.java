// Justin Montoya - 500885035

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;

public class Scheduler 
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	TreeMap<String,ActiveCourse> schedule;
	String[][] table = new String[9][6];
		
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
		 this.schedule = courses;
	}

	//sets time and day
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) 
	{
		//creates empty table
		table[0][0] = "0800";
		table[1][0] = "0900";
		table[2][0] = "1000";
		table[3][0] = "1100";
		table[4][0] = "1200";
		table[5][0] = "1300";
		table[6][0] = "1400";
		table[7][0] = "1500";
		table[8][0] = "1600";

		//prints table
        for (int i = 0; i < table.length; i++) {
            for (int k = 0; k < table[i].length; k++) {
				if(table[i][k] == null){
					table[i][k] = "";
				}
            }
		}
		

		// see assignment doc
		//touppercase so commandline works with lower and upper case
		courseCode = courseCode.toUpperCase();
		boolean overlap = false;
		try{
			//throw exception if course does not exist
			if(!schedule.containsKey(courseCode)){
				System.out.println("The course cannot be found.");
				throw new IllegalArgumentException("The course cannot be found.");
			}
			//throw exception if day is invalid
			if(!(day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed") 
			|| day.equalsIgnoreCase("Thur") || day.equalsIgnoreCase("Fri"))){
				System.out.println("Day can only be a weekday. Invalid Day: " + day);
				throw new IllegalArgumentException("Day can only be a weekday. Invalid Day: " + day);
			}

			//throw exception if start time is over 1600
			if((startTime < 800) || (startTime) > 1700){
				System.out.println("Invalid Start Time " + startTime);
				throw new IllegalArgumentException("Invalid Start Time " + startTime);
			}
			//throw exception if lecture runs after 1600
			if((startTime + duration*100) > 1700){
				System.out.println("Lecture runs after 1600: " + startTime + " to " + (startTime+duration*100));
				throw new IllegalArgumentException("Lecture runs after 1600: " + startTime + " to " + (startTime+duration*100));
			}
			//throw exception if duration is not between 1-3
			if((duration > 3) || (duration < 1)){
				System.out.println("Lecture can only be 1-3 hours long. Invalid Duration: " + duration);
				throw new IllegalArgumentException("Lecture can only be 1-3 hours long. Invalid Duration:" + duration);
			}

			//throw exception if lecture overlaps another
			int iCoordinate = (startTime - 800) / 100;
			int jCoordinate = 0;
			if(day.equalsIgnoreCase("Mon")){
				jCoordinate = 1;
			}
			else if(day.equalsIgnoreCase("Tue")){
				jCoordinate = 2;
			}
			else if(day.equalsIgnoreCase("Wed")){
				jCoordinate = 3;
			}
			else if(day.equalsIgnoreCase("Thur")){
				jCoordinate = 4;
			}
			else if(day.equalsIgnoreCase("Fri")){
				jCoordinate = 5;
			}
			for (int i = 0; i < duration; i++) {
				if(!(table[iCoordinate+i][jCoordinate].equalsIgnoreCase("") || table[iCoordinate+i][jCoordinate] == null)){
					System.out.println("Lecture overlaps with another lecture. Lecture Time Collision");
					overlap = true;
					throw new IllegalArgumentException("Lecture overlaps with another lecture. Lecture Time Collision");
				}
			}
		} catch (IllegalArgumentException e){
			return;
		}

		//sets variables
		if(overlap == false){
			schedule.get(courseCode).setDay(day);
			schedule.get(courseCode).setStart(startTime);
			schedule.get(courseCode).setDuration(duration);
		}

		//adds course to schedule
		for (int i = 0; i < table.length; i++) {
            int currentTime = 800 + i*100;
            for (int j = 0; j < table[i].length; j++) {
                if(j == 1){
					for (Map.Entry<String, ActiveCourse> entry : schedule.entrySet()) {
						if(entry.getValue().getDay().equalsIgnoreCase("Mon")){
							if(entry.getValue().getStart() == currentTime){
								for (int j2 = 0; j2 < entry.getValue().getDuration(); j2++) {
									table[i+j2][j] = entry.getKey();
								}
							}
						}
					}
				}
				else if(j == 2){
					for (Map.Entry<String, ActiveCourse> entry : schedule.entrySet()) {
						if(entry.getValue().getDay().equalsIgnoreCase("Tue")){
							if(entry.getValue().getStart() == currentTime){
								for (int j2 = 0; j2 < entry.getValue().getDuration(); j2++) {
									table[i+j2][j] = entry.getKey();
								}
							}
						}
					}
				}  
				else if(j == 3){
					for (Map.Entry<String, ActiveCourse> entry : schedule.entrySet()) {
						if(entry.getValue().getDay().equalsIgnoreCase("Wed")){
							if(entry.getValue().getStart() == currentTime){
								for (int j2 = 0; j2 < entry.getValue().getDuration(); j2++) {
									table[i+j2][j] = entry.getKey();
								}
							}
						}
					}
				} 
				else if(j == 4){
					for (Map.Entry<String, ActiveCourse> entry : schedule.entrySet()) {
						if(entry.getValue().getDay().equalsIgnoreCase("Thur")){
							if(entry.getValue().getStart() == currentTime){
								for (int j2 = 0; j2 < entry.getValue().getDuration(); j2++) {
									table[i+j2][j] = entry.getKey();
								}
							}
						}
					}
				} 
				else if(j == 5){
					for (Map.Entry<String, ActiveCourse> entry : schedule.entrySet()) {
						if(entry.getValue().getDay().equalsIgnoreCase("Fri")){
							if(entry.getValue().getStart() == currentTime){
								for (int j2 = 0; j2 < entry.getValue().getDuration(); j2++) {
									table[i+j2][j] = entry.getKey();
								}
							}
						}
					}
				} 
            }
        }
	}
	
	
	//clears schedule
	public void clearSchedule(String courseCode)
	{
		// see Assignment doc
		courseCode = courseCode.toUpperCase();
		for(Map.Entry<String, ActiveCourse> entry : schedule.entrySet()){
			if(entry.getKey().equals(courseCode)){
				ActiveCourse ac = schedule.get(courseCode);
				ac.setDay("");
				ac.setStart(0);
				ac.setDuration(0);
			}
		}

		//updates schedule
		for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
               if(table[i][j].equalsIgnoreCase(courseCode)){
					table[i][j] = "";
			   }
				
            }
        }
	}
		
	//prints schedule
	public void printSchedule()
	{
		//sets up table

		// sets values of table
        System.out.print("	Mon	Tue	Wed	Thu	Fri\n");
		table[0][0] = "0800";
		table[1][0] = "0900";
		table[2][0] = "1000";
		table[3][0] = "1100";
		table[4][0] = "1200";
		table[5][0] = "1300";
		table[6][0] = "1400";
		table[7][0] = "1500";
		table[8][0] = "1600";

		//prints table
        for (int i = 0; i < table.length; i++) {
            for (int k = 0; k < table[i].length; k++) {
				if(table[i][k] == null){
					table[i][k] = "";
				}
                if(k == 0){
                    System.out.print(table[i][k]);
                }
                else{
                    System.out.printf("%8s", table[i][k]);
                }
            }
            System.out.println();
        }
		
	}
	
}

