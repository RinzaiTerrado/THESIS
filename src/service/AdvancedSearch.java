package service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.BiologicalActivity; //change to models
import model.Compound;
import model.MedicinalPlant;
import model.Preparation;
import model.Species;
import model.SpeciesPart;

public class AdvancedSearch { //needs fixing
	private static final String owlPath = "C:\\OntoMarine.owl";
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
		resultPlant = new HashSet<String>();
		resultCompound = new HashSet<String>();
		compounds = null;
		
		q = new OntoQuery(owlPath);
		
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

		List<CommonName> cns = null;
		
		if(commonName == null)
			mos = q.searchCommonName("");
		else if(commonName != null)
			mps = q.searchCommonName(commonName);
		
		for (CommonName cn : cns) {

			List<String> locs = cn.getLocations();
			
			Boolean temp = false;
			
			if((locs.size() == 0 || locs == null) && location != null) {
				continue;
			} 
			if((locs.size() != 0 || locs != null) && location != null) {
				for(String loc : locs) {
					if(loc.toLowerCase().contains(location.toLowerCase())) {
						temp = true;
					}
				}
				if(!temp)
					continue;
			}
						
			temp = false;
			
		
			
			if(family == null && genus == null && species == null) {
				resultMarOrg.add(cn.getResultMarOrg());
			}
			
			
			List<Species> sps = mp.getSpecies();
			for (Species sp : sps) {
				System.err.println("##### " + sp.getSpecie());
				
				if (sp.getSpecie() != null && species != null)
					if (!sp.getSpecie().toLowerCase().contains(species.toLowerCase()))
						continue;
				if (sp.getSpecie() == null && species != null)
					continue;
				
				if(family == null && genus == null) {
					resultMarOrg.add(cn.getResultMarOrg());
				}
				
				
				if (sp.getSpecie() != null && genus != null)
					if (!sp.getSpecie().toLowerCase().contains(genus.toLowerCase()))
						continue;
				if (sp.getSpecie() == null && genus != null)
					continue;
				
				if(family == null && species == null) {
					resultMarOrg.add(cn.getResultMarOrg());
				}


				if (sp.getFamily() != null && family != null)
					if (!sp.getFamily().toLowerCase().contains(family.toLowerCase()))
						continue;
				if (sp.getFamily() == null && family != null)
					continue;
				
				if(genus == null) {
					resultMarOrg.add(cn.getResultMarOrg());
				}

				if (sp.getGenus() != null && genus != null)
					if (!sp.getGenus().toLowerCase().contains(genus.toLowerCase()))
						continue;
				if (sp.getGenus() == null && genus != null)
					continue;
				
				}
			}
		}
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
