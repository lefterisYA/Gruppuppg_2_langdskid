package skiing;

import java.util.Objects;

public class Person {
	private String firstName;
	private String lastName;
	private String gender;
	private int age;

	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public String getName() {return firstName+" "+lastName;}
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, gender, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(lastName, other.lastName);
	}}

