package service;

import edu.stanford.smi.protege.exception.OntologyLoadException;

import java.util.HashMap;
import java.util.HashSet;

public class AdvancedSearch { //needs fixing
	// CHANGE THIS PATH BELOW
	private static final String owlPath = "C:\\Users\\JR\\Desktop\\OntoMarine.owl";
	protected HashMap<String, String> properties;
	protected HashSet<String> resultMarOrg;
	protected HashSet<String> resultHabitat;
	
	protected OntologyQuery q;
	
	protected String location;
	protected String commonName;
	
	protected String family;
	protected String genus;
	protected String species;
	
	protected String effect;
	protected String factor;
	
	protected String effectVal;
	protected String factorVal;
	
	
	public AdvancedSearch(HashMap<String, String> props) throws OntologyLoadException {
		this.properties = props;
		resultMarOrg = new HashSet<String>();
		resultHabitat = new HashSet<String>();

		q = new OntologyQuery(owlPath);
		
		location = properties.get("location");
		commonName = properties.get("commonName");
		
		family = properties.get("family");
		genus = properties.get("genus");
		species = properties.get("species");
		
		effect = properties.get("effect");
		factor = properties.get("factor");


		effectVal = properties.get("effectVal");
		factorVal = properties.get("factorVal");
	}
	
	public void startSearch() {

		}
	
	public void habitatSearch() {

	}

	public HashSet<String> getResultMarOrg() {
		return resultMarOrg;
	}

	public void setResultMarOrg(HashSet<String> resultMarOrg) {
		this.resultMarOrg = resultMarOrg;
	}

	public HashSet<String> getResultHabitat() {
		return resultHabitat;
	}

	public void setResultHabitat(HashSet<String> resultHabitat) {
		this.resultHabitat = resultHabitat;
	}
}
