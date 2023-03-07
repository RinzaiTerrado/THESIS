package service;

import java.util.HashMap;

import edu.stanford.smi.protege.exception.OntologyLoadException;

public class AdvandedHabitatSearch extends AdvancedSearch {

	public AdvandedHabitatSearch(HashMap<String, String> props)  throws OntologyLoadException {
		super(props); 
	}
	
	public AdvandedHabitatSearch search() {
		
		
		startSearch();
		
		return this;
	}

}
