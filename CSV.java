package something;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Employee {
	String name;
	int age;
	String dob;
	int salary;
	String city;

	public Employee(String name, int age, String yourDate, int salary, String city) {

		this.name = name;
		this.age = age;
		this.dob = yourDate;
		this.salary = salary;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "name: " + name + " " + "age: " + age + " DOB: " + dob + " salary: " + salary + " city " + city;
	}
}

public class CSV {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "name,age,dob,salary,city";

	public static void main(String[] args) throws IOException {
		List<Employee> list = new ArrayList<>();
		list.add(new Employee("naveen", 23,"1998-12-12", 20000, "pune"));
		list.add(new Employee("pranay", 23,"1998-12-12", 20000, "pune"));
		list.add(new Employee("praveen", 23,"1998-12-12", 20000, "pune"));
		list.add(new Employee("punit", 23,"1998-12-12", 20000, "pune"));
		list.add(new Employee("sujeet", 23,"1998-12-12", 20000, "pune"));
		list.add(new Employee("prabakar", 23,"1998-12-12", 20000, "pune"));
		
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter("empdetails.csv");

//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

//Write a new student object list to the CSV file
			for (Employee student : list) {
				fileWriter.append(student.getName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(student.getAge()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(student.getDob());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(student.getSalary()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(student.getCity());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}
}
