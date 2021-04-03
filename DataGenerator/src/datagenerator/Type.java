package datagenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Type {
	
	private String name;
	private Map<String, Symptom> symptoms = new HashMap<String,Symptom>();
	
	
	public Type(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Symptom> getSymptoms(){
		return symptoms.values();
	}
	
	public Symptom getSymptom(SymptomType symptomType) {
		return this.symptoms.get(symptomType.toString());
	}
	
	public void addSymptom(Symptom symptom) {
		this.symptoms.put(symptom.getType().toString(), symptom);
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", symptoms=" + symptoms + "]";
	}
	
	
}
