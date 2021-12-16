package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import common.Utils;

public class ClassList extends SkierList {
	List<Skier> classList = new LinkedList<Skier>();
	String skiingClass;
	int[] firstStart = new int[3];
	int startInterval;
	int firstPlayerNumber;

	public String getSkiingClass() {return skiingClass;}
	public int[] getFirstStart() {return firstStart;}
	public void setFirstStart(int[] firstStart) {this.firstStart = firstStart;}
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
		if(skier.skiingClass==this.skiingClass) {
			classList.add(skier);
		}
		else
			System.out.println("Wrong class");
	}
	
	public void addAllToClassList(SkierList skierlist) {
		Skier[] skiers = skierlist.getSkierList();
		for (int i = 0; i < skiers.length; i++) {
			if(skiers[i].skiingClass==this.skiingClass)
				classList.add(skiers[i]);
		}
	}
	public void setPlayerNumber(ClassList classlist) {
		for (int i = 0; i < classList.size(); i++) {
			Skier skiski = classList.get(i);
			skiski.playerNumber = classlist.firstPlayerNumber + i;
			skiski.startingTime = Utils.timeAdder(classlist.firstStart, Utils.timeConverter(classlist.startInterval * i));
			classList.set(i, skiski);
		}
	}
	@Override
	public String toString() {
		String skierString = "";
		for (int i = 0; i < classList.size(); i++) {
			skierString = skierString+"Skier number " + i+1 +" - Name: "+classList.get(i).name+", age: "+classList.get(i).age+", gender: "+classList.get(i).gender+", playernumber: "+classList.get(i).playerNumber+"\n";
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

	}