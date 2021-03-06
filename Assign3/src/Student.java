
public class Student {
	
	String	lastName;
	String	department;
	String	program;
	int		ID;
	int		year;
	
	public Student(String l, String d, String p, int i, int y) {
		lastName = l;
		department = d;
		program = p;
		ID = i;
		year = y;
	}
	
	public String getLastname() {return lastName;}
	public String getDepartment() {return department;}
	public String getProgram() {return program;}
	public int getID() {return ID;}
	public int getYear() {return year;}
	
	@Override
	public String toString() {
		return String.format(ID + lastName + department + program + year);
	}
	
}
