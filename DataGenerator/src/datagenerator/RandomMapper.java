package datagenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomMapper {

	Map<String, Random> randomMap = new HashMap<>();
	
	
	private Random getRandomizer(SymptomType st) {
		Random r = randomMap.get(st.toString());
		if(r != null) {
			return r;
		} else {
			r = new Random();
			randomMap.put(st.toString(), r);
			return r;
		}
	}
	
	
	
	public String getValue(Symptom symptom, double percentage) {
		
		return Integer.toString(boolToInt(isWithInRange(symptom.getType(),percentage)));
	}
	
	private int boolToInt(boolean bool) {
		if(bool) {
			return 1;
		}
		return 0;
	}
	
	private boolean isWithInRange(SymptomType st,double percentage) {
		double randomSample = getRandomNumber(st);
		if(percentage == 0) {
			return false;
		} else if(randomSample <= percentage) {
			return true;
		}
		return false;
	}
	
	private double getRandomNumber(SymptomType st) {
		return getRandomizer(st).nextDouble();
	}
}
