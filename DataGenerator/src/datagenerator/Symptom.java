package datagenerator;

import java.util.HashSet;
import java.util.Set;

public class Symptom {
	private SymptomType type;
	private FrequencyDefinition frequencyDefinition;
	private Set<Symptom> symptomDependencies;
	private Set<Symptom> excludedSymptoms;
	private AgeGroup ageGroupAffected;
	
	
	public Symptom(SymptomType type, FrequencyDefinition frequencyDefinition, AgeGroup ageGroup) {
		super();
		this.type = type;
		this.frequencyDefinition = frequencyDefinition;
		this.symptomDependencies = new HashSet<Symptom>();
		this.excludedSymptoms = new HashSet<Symptom>();
		this.ageGroupAffected = ageGroup;
	}


	public SymptomType getType() {
		return type;
	}


	public void setType(SymptomType type) {
		this.type = type;
	}


	public AgeGroup getAgeGroupAffected() {
		return ageGroupAffected;
	}


	public void setAgeGroupAffected(AgeGroup ageGroupAffected) {
		this.ageGroupAffected = ageGroupAffected;
	}


	public Set<Symptom> getSymptomDependencies() {
		return symptomDependencies;
	}
	private void addSymptomDependency(Symptom symptomDependency) {
		this.symptomDependencies.add(symptomDependency);
	}
	public Set<Symptom> getExcludedSymptoms() {
		return excludedSymptoms;
	}
	private void addExcludedSymptom(Symptom exludedSymptom) {
		this.excludedSymptoms.add(exludedSymptom);
	}
	public FrequencyDefinition getFrequencyDefinition() {
		return frequencyDefinition;
	}
	public void setFrequencyDefinition(FrequencyDefinition frequencyDefinition) {
		this.frequencyDefinition = frequencyDefinition;
	}
	
	public static void addSymptomExclusion(Symptom symptom1, Symptom symptom2) {
		symptom1.addExcludedSymptom(symptom2);
		symptom2.addExcludedSymptom(symptom1);
	}
	
	public static void addSymptomDependency(Symptom symptom1, Symptom symptom2) {
		symptom1.addSymptomDependency(symptom2);
		symptom2.addSymptomDependency(symptom1);
	}


	@Override
	public String toString() {
		return "Symptom [type=" + type + ", frequencyDefinition=" + frequencyDefinition + ", ageGroupAffected="
				+ ageGroupAffected + "]";
	}
	
	
}
