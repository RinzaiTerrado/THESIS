package service;

import java.util.HashMap;

import edu.stanford.smi.protege.exception.OntologyLoadException;

public class AdvandedMarOrgSearch extends AdvancedSearch {

	public AdvandedMarOrgSearch(HashMap<String, String> props)  throws OntologyLoadException {
		super(props);
	}
	
	public AdvandedMarOrgSearch search() {
		
		
		startSearch();
		
		return this;
	}

}
