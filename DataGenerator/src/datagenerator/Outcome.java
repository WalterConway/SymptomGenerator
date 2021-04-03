package datagenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Outcome {
	
	private Type type;
	private Map<String, String> symptomValues = new HashMap<String, String>();
	
	private static List<SymptomType> symptomTypeOrder = new ArrayList<SymptomType>();
	

	public void addSymptomValues(Symptom symptom, String value) {
		
		symptomValues.put(symptom.getType().toString(), value);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	
	public boolean isSymtomValueSet(SymptomType st) {
		String v = symptomValues.get(st.toString());
		
		return "1".equals(v);
		
	}
	
	public static List<SymptomType> getSymptomTypeOrder() {
		return symptomTypeOrder;
	}




	public static void setSymptomTypeOrder(List<SymptomType> symptomTypeOrder) {
		Outcome.symptomTypeOrder = symptomTypeOrder;
	}




	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for(SymptomType symptomType : symptomTypeOrder) {
			String value = symptomValues.getOrDefault(symptomType.toString(), "0");
			sb.append(value).append(",");
		}
		if(sb.length() >0) {
			sb.append(type.getName());
		}
		
		return sb.toString();
	}
	
	
}
