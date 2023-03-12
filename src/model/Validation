package model;

import java.util.HashSet;

public class Validation {

	String pdfFileName;
	HashSet<MarineOrganism> MarineOrganism;
	HashSet<Effect> Effect;
	HashSet<EffectValue> EffectValue;
	HashSet<Factor> Factor;
	HashSet<FactorValue> FactorValue;
	HashSet<Habitat> Habitat;
	HashSet<Location> Location;
	
	
	public Validation(String pdfFileName) {
		// TODO Auto-generated constructor stub
		this.MarineOrganism = new HashSet<MarineOrganism>();
		this.Effect = new HashSet<Effect>();		
		this.EffectValue = new HashSet<EffectValue>();		
		this.Factor = new HashSet<Factor>();
		this.FactorValue = new HashSet<FactorValue>();
		this.Habitat = new HashSet<Habitat>();
		this.Location = new HashSet<Location>();
		this.pdfFileName = pdfFileName;
	}


	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFilePath) {
		this.pdfFileName = pdfFilePath;
	}
	
	public void addMarineOrganism(MarineOrganism marineOrganism) {
		MarineOrganism.add(marineOrganism);
	}
	
	public void addEffect(Effect effect) {
		Effect.add(effect);
	}
	
	public void addEffectValue(EffectValue effectValue) {
		EffectValue.add(effectValue);
	}
	
	public void addFactor(Factor factor) {
		Factor.add(factor);
	}
	
	public void addFactorValue(FactorValue factorValue) {
		FactorValue.add(factorValue);
	}

	public void addHabitat(Habitat habitat) {
		Habitat.add(habitat);
	}
	
	public void addLocation(Location location) {
		Location.add(location);
	}
	
	public HashSet<MarineOrganism> getMarineOrganism() {
		return MarineOrganism;
	}

	public HashSet<Effect> getEffect() {
		return Effect;
	}


	public HashSet<EffectValue> getEffectValue() {
		return EffectValue;
	}	
	
	public HashSet<Factor> getFactor() {
		return Factor;
	}

	public HashSet<FactorValue> getFactorValue() {
		return FactorValue;
	}

	public HashSet<Habitat> getHabitat() {
		return Habitat;
	}
	
	public HashSet<Location> getLocation() {
		return Location;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pdfFileName == null) ? 0 : pdfFileName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Validation other = (Validation) obj;
		if (pdfFileName == null) {
			if (other.pdfFileName != null)
				return false;
		} else if (!pdfFileName.equals(other.pdfFileName))
			return false;
		return true;
	}
	

}
