package datagenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataRepository {
	private Map<String, Set<String>> outcomeMapping = new HashMap<String, Set<String>>();



	public List<String> getRepository(){
		List<String> repo = new ArrayList<String>();
		
		Collection<Set<String>> sets = outcomeMapping.values();
		
		for(Set<String> set : sets) {
			repo.addAll(set);
		}
		
		return repo;
	}

	public boolean addOutCome(Outcome outcome) {
		String type = outcome.getType().toString();
		Set<String> outcomeSet = outcomeMapping.get(type);
		if(outcomeSet != null) {
			return outcomeSet.add(outcome.toString());
		} else {
			outcomeSet = new HashSet<String>();
			outcomeSet.add(outcome.toString());
			outcomeMapping.put(type, outcomeSet);
			return true;
		}
		

	}

}
