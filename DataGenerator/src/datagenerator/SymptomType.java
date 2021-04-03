package datagenerator;

public enum SymptomType {
	COUGH("COUGH"),
	MUSCLE_ACHES("MUSCLE_ACHES"),
	TIREDNESS("TIREDNESS"),
	SORE_THROAT("SORE_THROAT"),
	RUNNY_NOSE("RUNNY_NOSE"),
	STUFFY_NOSE("STUFFY_NOSE"),
	FEVER("FEVER"),
	NAUSEA("NAUSEA"),
	VOMITING("VOMITING"),
	DIARRHEA("DIARRHEA"),
	SHORTNESS_OF_BREATH("SHORTNESS_OF_BREATH"),
	DIFFICULTY_BREATHING("DIFFICULTY_BREATHING"),
	LOSS_OF_TASTE("LOSS_OF_TASTE"),
	LOSS_OF_SMELL("LOSS_OF_SMELL"),
	ITCHY_NOSE("ITCHY_NOSE"),
	ITCHY_EYES("ITCHY_EYES"),
	ITCHY_MOUTH("ITCHY_MOUTH"),
	ITCHY_INNER_EAR("ITCHY_INNER_EAR"),
	SNEEZING("SNEEZING"),
	PINK_EYE("PINK_EYE");

	String name;

	SymptomType(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
