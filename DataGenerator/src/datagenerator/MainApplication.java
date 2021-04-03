package datagenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainApplication {
	public static AgeGroup YO_0_9 = new AgeGroup(0,9,"yo0_9");
	public static AgeGroup YO_10_19 = new AgeGroup(10,19,"yo10_19");
	public static AgeGroup YO_20_29 = new AgeGroup(20,29,"yo20_29");
	public static AgeGroup YO_30_39 = new AgeGroup(30,39,"yo30_39");
	public static AgeGroup YO_40_49 = new AgeGroup(40,49,"yo40_49");
	public static AgeGroup YO_50_59 = new AgeGroup(50,59,"yo50_59");
	public static AgeGroup YO_60_plus = new AgeGroup(60,200,"yo60_plus");
	public static AgeGroup ALL = new AgeGroup(0,200, "ALL");

	public static FrequencyDefinition USUALLY = new FrequencyDefinition(Frequency.USUALLY, .7);
	public static FrequencyDefinition LESS_THAN_USUALLY = new FrequencyDefinition(Frequency.LESS_THAN_USUALLY, .6);
	public static FrequencyDefinition RARELY = new FrequencyDefinition(Frequency.RARELY, .1);
	public static FrequencyDefinition NEVER = new FrequencyDefinition(Frequency.NEVER, 0);
	public static FrequencyDefinition SOMETIMES = new FrequencyDefinition(Frequency.USUALLY, .5);
	public static RandomMapper rm = new RandomMapper(); 
	public static void main(String[] args) {

		int samplesPerTypeToGenerate = 100000;
		DataRepository repo = new DataRepository();
		Outcome.setSymptomTypeOrder(Arrays.asList(SymptomType.values()));
		List<Type> profiles = new ArrayList<Type>();
		profiles.add(getCovidProfile());
		profiles.add(getFluProfile());
		profiles.add(getAllergyProfile());
		profiles.add(getColdProfile());
		System.out.println("size:" +profiles.size());
		for(Type profile : profiles) {
			int currentSampleCount =0;

			while(currentSampleCount < samplesPerTypeToGenerate) {

				Outcome o = new Outcome();
				o.setType(profile);
				Set<Symptom> exclude = new HashSet<Symptom>();
				Set<Symptom> include = new HashSet<Symptom>();
				Set<Symptom> includeAlreadyChecked = new HashSet<Symptom>();

				for(Symptom s : profile.getSymptoms()) {
					if(!s.getSymptomDependencies().isEmpty()) {
						include.addAll(s.getSymptomDependencies());
					}

					if(!s.getExcludedSymptoms().isEmpty()) {
						exclude.addAll(s.getExcludedSymptoms());
					}
				}


				for(Symptom s : profile.getSymptoms()) {
					if(!exclude.contains(s)) {
						o.addSymptomValues(s, rm.getValue(s, s.getFrequencyDefinition().getPercentage()));
					}
				}

				for(Symptom s : include) {
					if(!includeAlreadyChecked.contains(s)) {
						if(!o.isSymtomValueSet(s.getType())) {
							for(Symptom is: s.getSymptomDependencies() ) {
								if(o.isSymtomValueSet(s.getType())) {
									o.addSymptomValues(s, rm.getValue(s, s.getFrequencyDefinition().getPercentage()));
								}
								includeAlreadyChecked.add(is);
							}
						}
					}
				}

				repo.addOutCome(o);
				currentSampleCount++;
			}
		}




		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("data.csv"));
			writer.write(getHeader()+System.lineSeparator());
			for(String out : repo.getRepository()) {
				System.out.println(out);
				writer.write(out+System.lineSeparator());
			}
			writer.close();	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null){
					writer.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getHeader() {
		StringBuilder sb = new StringBuilder();
		List<SymptomType> types = Arrays.asList(SymptomType.values());
		for(SymptomType st : types) {
			sb.append(st.toString()).append(",");
		}
		sb.append("TYPE");
		
		return sb.toString();
	}


	public static Type getCovidProfile() {
		Type covid = new Type("COVID");
		Symptom cough = new Symptom(SymptomType.COUGH,USUALLY,ALL);
		Symptom muscleAches = new Symptom(SymptomType.MUSCLE_ACHES,USUALLY,ALL);
		Symptom tiredness = new Symptom(SymptomType.TIREDNESS,USUALLY,ALL);
		Symptom sneezing = new Symptom(SymptomType.SNEEZING,USUALLY,ALL);
		Symptom soreThroat = new Symptom(SymptomType.SORE_THROAT,USUALLY,ALL);
		Symptom fever = new Symptom(SymptomType.FEVER,USUALLY,ALL);
		Symptom diarrhea = new Symptom(SymptomType.DIARRHEA,USUALLY,ALL);
		Symptom nausea = new Symptom(SymptomType.NAUSEA,USUALLY,ALL);
		Symptom vomiting = new Symptom(SymptomType.VOMITING,USUALLY,ALL);
		Symptom runnyNose = new Symptom(SymptomType.RUNNY_NOSE,USUALLY,ALL);
		Symptom stuffyNose = new Symptom(SymptomType.STUFFY_NOSE,USUALLY,ALL);

		Symptom lossOfTaste = new Symptom(SymptomType.LOSS_OF_TASTE,USUALLY,ALL);
		Symptom lossOfSmell = new Symptom(SymptomType.LOSS_OF_SMELL,USUALLY,ALL);

		Symptom.addSymptomExclusion(lossOfTaste, runnyNose);
		Symptom.addSymptomExclusion(lossOfTaste, stuffyNose);
		Symptom.addSymptomExclusion(lossOfSmell, runnyNose);
		Symptom.addSymptomExclusion(lossOfSmell, stuffyNose);

		covid.addSymptom(cough);
		covid.addSymptom(muscleAches);
		covid.addSymptom(tiredness);
		covid.addSymptom(sneezing);
		covid.addSymptom(soreThroat);
		covid.addSymptom(runnyNose);
		covid.addSymptom(stuffyNose);
		covid.addSymptom(diarrhea);
		covid.addSymptom(nausea);
		covid.addSymptom(fever);
		covid.addSymptom(vomiting);
		covid.addSymptom(lossOfTaste);
		covid.addSymptom(lossOfSmell);

		return covid;
	}

	public static Type getFluProfile() {
		Type flu = new Type("FLU");
		Symptom cough = new Symptom(SymptomType.COUGH,USUALLY,ALL);
		Symptom muscleAches = new Symptom(SymptomType.MUSCLE_ACHES,USUALLY,ALL);
		Symptom tiredness = new Symptom(SymptomType.TIREDNESS,USUALLY,ALL);
		Symptom sneezing = new Symptom(SymptomType.SNEEZING,USUALLY,ALL);
		Symptom soreThroat = new Symptom(SymptomType.SORE_THROAT,USUALLY,ALL);
		Symptom fever = new Symptom(SymptomType.FEVER,LESS_THAN_USUALLY,ALL);
		Symptom diarrhea = new Symptom(SymptomType.DIARRHEA,USUALLY,ALL);
		Symptom nausea = new Symptom(SymptomType.NAUSEA,USUALLY,YO_0_9);
		Symptom vomiting = new Symptom(SymptomType.VOMITING,USUALLY,YO_0_9);
		Symptom runnyNose = new Symptom(SymptomType.RUNNY_NOSE,USUALLY,ALL);
		Symptom stuffyNose = new Symptom(SymptomType.STUFFY_NOSE,USUALLY,ALL);

		Symptom lossOfTaste = new Symptom(SymptomType.LOSS_OF_TASTE,RARELY,ALL);
		Symptom lossOfSmell = new Symptom(SymptomType.LOSS_OF_SMELL,RARELY,ALL);

		flu.addSymptom(cough);
		flu.addSymptom(muscleAches);
		flu.addSymptom(tiredness);
		flu.addSymptom(sneezing);
		flu.addSymptom(soreThroat);
		flu.addSymptom(runnyNose);
		flu.addSymptom(stuffyNose);
		flu.addSymptom(diarrhea);
		flu.addSymptom(nausea);
		flu.addSymptom(fever);
		flu.addSymptom(vomiting);
		flu.addSymptom(lossOfTaste);
		flu.addSymptom(lossOfSmell);

		return flu;
	}

	public static Type getColdProfile() {
		Type cold = new Type("COLD");
		Symptom cough = new Symptom(SymptomType.COUGH,USUALLY,ALL);
		Symptom muscleAches = new Symptom(SymptomType.MUSCLE_ACHES,SOMETIMES,ALL);
		Symptom tiredness = new Symptom(SymptomType.TIREDNESS,SOMETIMES,ALL);
		Symptom sneezing = new Symptom(SymptomType.SNEEZING,SOMETIMES,ALL);
		Symptom soreThroat = new Symptom(SymptomType.SORE_THROAT,USUALLY,ALL);
		Symptom fever = new Symptom(SymptomType.FEVER,SOMETIMES,ALL);
		Symptom diarrhea = new Symptom(SymptomType.DIARRHEA,NEVER,ALL);
		Symptom nausea = new Symptom(SymptomType.NAUSEA,NEVER,ALL);
		Symptom vomiting = new Symptom(SymptomType.VOMITING,NEVER,ALL);
		Symptom runnyNose = new Symptom(SymptomType.RUNNY_NOSE,USUALLY,ALL);
		Symptom stuffyNose = new Symptom(SymptomType.STUFFY_NOSE,USUALLY,ALL);

		Symptom lossOfTaste = new Symptom(SymptomType.LOSS_OF_TASTE,SOMETIMES,ALL);
		Symptom lossOfSmell = new Symptom(SymptomType.LOSS_OF_SMELL,SOMETIMES,ALL);

		Symptom.addSymptomDependency(lossOfTaste, stuffyNose);
		Symptom.addSymptomDependency(lossOfSmell, stuffyNose);

		cold.addSymptom(cough);
		cold.addSymptom(muscleAches);
		cold.addSymptom(tiredness);
		cold.addSymptom(sneezing);
		cold.addSymptom(soreThroat);
		cold.addSymptom(runnyNose);
		cold.addSymptom(stuffyNose);
		cold.addSymptom(diarrhea);
		cold.addSymptom(nausea);
		cold.addSymptom(fever);
		cold.addSymptom(vomiting);
		cold.addSymptom(lossOfTaste);
		cold.addSymptom(lossOfSmell);

		return cold;
	}

	public static Type getAllergyProfile() {
		Type allergy = new Type("ALLERGY");
		Symptom cough = new Symptom(SymptomType.COUGH,SOMETIMES,ALL);
		Symptom muscleAches = new Symptom(SymptomType.MUSCLE_ACHES,SOMETIMES,ALL);
		Symptom tiredness = new Symptom(SymptomType.TIREDNESS,SOMETIMES,ALL);
		Symptom sneezing = new Symptom(SymptomType.SNEEZING,SOMETIMES,ALL);
		Symptom soreThroat = new Symptom(SymptomType.SORE_THROAT,RARELY,ALL);
		Symptom fever = new Symptom(SymptomType.FEVER,NEVER,ALL);
		Symptom diarrhea = new Symptom(SymptomType.DIARRHEA,NEVER,ALL);
		Symptom nausea = new Symptom(SymptomType.NAUSEA,NEVER,ALL);
		Symptom vomiting = new Symptom(SymptomType.VOMITING,NEVER,ALL);
		Symptom runnyNose = new Symptom(SymptomType.RUNNY_NOSE,USUALLY,ALL);
		Symptom stuffyNose = new Symptom(SymptomType.STUFFY_NOSE,USUALLY,ALL);

		Symptom lossOfTaste = new Symptom(SymptomType.LOSS_OF_TASTE,SOMETIMES,ALL);
		Symptom lossOfSmell = new Symptom(SymptomType.LOSS_OF_SMELL,SOMETIMES,ALL);


		Symptom pinkEye = new Symptom(SymptomType.PINK_EYE, SOMETIMES, ALL);
		Symptom itchNose = new Symptom(SymptomType.ITCHY_NOSE, USUALLY, ALL);
		Symptom itchEyes = new Symptom(SymptomType.ITCHY_EYES, USUALLY, ALL);
		Symptom itchMouth = new Symptom(SymptomType.ITCHY_MOUTH, USUALLY, ALL);
		Symptom itchEar = new Symptom(SymptomType.ITCHY_INNER_EAR, USUALLY, ALL);


		allergy.addSymptom(cough);
		allergy.addSymptom(muscleAches);
		allergy.addSymptom(tiredness);
		allergy.addSymptom(sneezing);
		allergy.addSymptom(soreThroat);
		allergy.addSymptom(runnyNose);
		allergy.addSymptom(stuffyNose);
		allergy.addSymptom(diarrhea);
		allergy.addSymptom(nausea);
		allergy.addSymptom(fever);
		allergy.addSymptom(vomiting);
		allergy.addSymptom(lossOfTaste);
		allergy.addSymptom(lossOfSmell);
		allergy.addSymptom(pinkEye);
		allergy.addSymptom(itchNose);
		allergy.addSymptom(itchEyes);
		allergy.addSymptom(itchMouth);
		allergy.addSymptom(itchEar);

		return allergy;
	}

}
