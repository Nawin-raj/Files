package assignment_17dec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author acer
 *
 */
public class Employee {
	String name;
	int age;
	LocalDate dob;
	int salary;
	String city;

	public Employee(String name, int age, LocalDate yourDate, int salary, String city) {

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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
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

	/**
	 * @param csv file This method reads data from csv file and returns Employee
	 *            list
	 * @return list of Employees
	 */
	public static List<Employee> readcsvfile(String csvFile) throws ParseException {
		String line = "";
		String cvsSplitBy = ",";
		List<Employee> list1 = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] s = line.split(cvsSplitBy);
				// Converting String to LocalDate
				String dateobj = s[2];

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate yourDate = LocalDate.parse(dateobj, formatter);

				// Parsing age from String to Integer
				int ageobe = Integer.parseInt(s[1]);
				// Parsing salary from String to Integer
				int salaryobj = Integer.parseInt(s[3]);
				// constructing Employee list
				list1.add(new Employee(s[0], ageobe, yourDate, salaryobj, s[4]));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list1;
	}

	/**
	 * @param list of employee This method finds the Lowest paid employee details
	 * @return emp obj
	 *
	 */
	public static Employee lowpaidemp(List<Employee> emplist) {
		Optional<Employee> maxSalaryEmp = emplist.stream()
				.collect(Collectors.minBy(Comparator.comparing(Employee::getSalary)));
		return maxSalaryEmp.get();
	}
	
	/**
	 * @param list of employees
	 * @This method sort the list based on dob in decending order
	 *
	 */
	public static void sortempindec(List<Employee> emplist) {
		emplist.sort((Employee o1, Employee o2) -> o2.getDob().compareTo(o1.getDob()));

		for (Employee emp : emplist)
			System.out.println(emp + "\n");

	}

	/**
	 * @param list of employees
	 * This method print avg salary of emp in city wise
	 *
	 */

	private static void avgsalary(List<Employee> emplist) {
		double avgsal=emplist.stream().mapToInt(Employee::getSalary).average().getAsDouble();
		System.out.println(avgsal);

	}

	public static void main(String[] args) throws ParseException {
		String csvFile = "details.csv";
		// reading data from csv file and constructing employee object
		List<Employee> emplist = readcsvfile(csvFile);
		// low paid employee
		Employee empobj = lowpaidemp(emplist);
		//System.out.println("Employee with min salary--->" + empobj);
		// sorting emloyees based on dob in decending order
		//sortempindec(emplist);
		// Print average salary of employee sity wise
		avgsalary(emplist);
	}

}
