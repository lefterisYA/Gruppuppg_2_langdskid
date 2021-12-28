package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import common.Utils;

public class ClassList extends SkierList {
	private List<Skier> classList = new LinkedList<Skier>();
	private String skiingClass;
	private int[] firstStart = new int[3];
	private int startInterval;
	private int firstPlayerNumber;

	public String getSkiingClass() {return skiingClass;}
	public int[] getFirstStart() {return firstStart;}
	public void setFirstStart(int index, int firstStart) {this.firstStart[index] = firstStart;}
	public int getStartInterval() {return startInterval;}
	public void setStartInterval(int startInterval) {this.startInterval = startInterval;}
	public int getFirstPlayerNumber() {return firstPlayerNumber;}
	public void setFirstPlayerNumber(int firstPlayerNumber) {this.firstPlayerNumber = firstPlayerNumber;}
	public void setSkiingClass (String skiingClass){this.skiingClass=skiingClass;}
	public Skier getSkier(int skierNumber) {return classList.get(skierNumber);}
	public void setSkier(Skier skier, int indexnumber) {classList.set(indexnumber, skier);}
	public void setSkierList(Skier[] skierlist) {classList = Arrays.asList(skierlist);}
	
	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[classList.size()];
		for (int i = 0; i < classList.size(); i++) {
			skierList[i] = classList.get(i);
		}
		return skierList;
	}
	
	public void addToClassList(Skier skier) {
		if(skier.getSkiingClass()==this.skiingClass) {
			classList.add(skier);
		}
		else
			System.out.println("Wrong class");
	}
	
	public void addAllToClassList(SkierList skierlist) {
		Skier[] skiers = skierlist.getSkierList();
		for (int i = 0; i < skiers.length; i++) {
			if(skiers[i].getSkiingClass()==this.skiingClass)
				classList.add(skiers[i]);
		}
	}
	public void setPlayerNumber(ClassList classlist) {
		for (int i = 0; i < classList.size(); i++) {
			Skier skiski = classList.get(i);
			skiski.setPlayerNumber(classlist.firstPlayerNumber + i);
			skiski.setStartingTime(Utils.timeAdder(classlist.firstStart, Utils.timeConverter(classlist.startInterval * i)));
			classList.set(i, skiski);
		}
	}
	@Override
	public String toString() {
		String skierString = "";
		for (int i = 0; i < classList.size(); i++) {
			skierString = skierString+"Skier number " + i+1 +" - Name: "+classList.get(i).getName()+", age: "+classList.get(i).getAge()+", gender: "+classList.get(i).getGender()+", playernumber: "+classList.get(i).getPlayerNumber()+"\n";
		}
		return "This classlist contains: "+skierString+"And their first start is "+Arrays.toString(firstStart)+", their startinterval is "+startInterval+" and their starting number is "+firstPlayerNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(firstStart);
		result = prime * result + Objects.hash(classList, firstPlayerNumber, skiingClass, startInterval);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassList other = (ClassList) obj;
		return Objects.equals(classList, other.classList) && firstPlayerNumber == other.firstPlayerNumber
				&& Arrays.equals(firstStart, other.firstStart) && Objects.equals(skiingClass, other.skiingClass)
				&& startInterval == other.startInterval;
	}
	
	public void timeConfigInput(ClassList classlist) {
		Scanner scan1 = new Scanner(System.in);
		System.out.println("När ska den första starttiden vara? input timme: ");
				setFirstStart(0, scanner);
		System.out.println("input minut: ");
				setFirstStart(1, scanner);
		System.out.println("input sekund: ");
				setFirstStart(2, scanner);
		System.out.println("Ange startinteravll i sekunder: ");
				setStartInterval(scanner);
		System.out.println("Ange första spelarens nummer, resterande spelares nummer kommer baseras på det"
				+ "här. Så om du väljer t.ex 101, kommer spelare två vara 102, tre 103, osv.");
				setFirstPlayerNumber(scanner);
	}
	}