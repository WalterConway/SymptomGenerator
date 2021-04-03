package datagenerator;

public class AgeGroup {
	int lowerAge;
	int upperAge;
	String name;
	
	
	public AgeGroup(int lowerAge, int upperAge, String name) {
		super();
		this.lowerAge = lowerAge;
		this.upperAge = upperAge;
		this.name = name;
	}
	
	public int getLowerAge() {
		return lowerAge;
	}
	public void setLowerAge(int lowerAge) {
		this.lowerAge = lowerAge;
	}
	public int getUpperAge() {
		return upperAge;
	}
	public void setUpperAge(int upperAge) {
		this.upperAge = upperAge;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AgeGroup [lowerAge=" + lowerAge + ", upperAge=" + upperAge + ", name=" + name + "]";
	}
	
	
	
	
}
