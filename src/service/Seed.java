package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
//where seed is generated
public class Seed { 
    public void generateSeed(OntologyQuery q) throws OntologyLoadException, SQWRLException{
        genMarOrgWithCN(q);
        genMarOrgWithHabitat(q);
        genMarOrgWithGenus(q);
        genMarOrgWithFamily(q);
        genMarOrgWithLoc(q);
        genHabitatWithLoc(q);
        genHabitatWithEffect(q);
        genEffectWithFactor(q);
        genEffectWithEffVal(q);
        genFactorWithFactVal(q);
        genGenusWithFamily(q);
        genCNWithHabitat(q);
        genCNWithGenus(q);
        genCNWithFamily(q);
        genCNWithLoc(q);
    }

    public void genMarOrgWithCN(OntologyQuery q) throws SQWRLException {
		List<String> MarOrgs = q.getAllMarineOrg();
		List<String> CommonNames = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("MarineOrganism-CommonName.txt");
			writer.print("");
			writer.print("e1:MarineOrganism\r\n");
			writer.print("e2:CommonName\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < MarOrgs.size(); i++) {
				CommonNames = q.getAllCommonNames(MarOrgs.get(i));
				for (int j = 0; j < CommonNames.size(); j++) {
					String marOrg = removePar(MarOrgs.get(i));
					String commName = removePar(CommonNames.get(j));
					writer.write(marOrg + ";" + commName);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genMarOrgWithHabitat(OntologyQuery q) throws SQWRLException {
		List<String> MarOrgs = q.getAllMarineOrg();
		List<String> Habitats = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("MarineOrganism-Habitat.txt");
			writer.print("");
			writer.print("e1:MarineOrganism\r\n");
			writer.print("e2:Habitat\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < MarOrgs.size(); i++) {
				Habitats = q.getAllHabitats(MarOrgs.get(i));
				for (int j = 0; j < Habitats.size(); j++) {
					String marOrg = removePar(MarOrgs.get(i));
					String habitat = removePar(Habitats.get(j));
					writer.write(marOrg + ";" + habitat);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genMarOrgWithGenus(OntologyQuery q) throws SQWRLException {
		List<String> MarOrgs = q.getAllMarineOrg();
		List<String> Genus = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("MarineOrganism-Genus.txt");
			writer.print("");
			writer.print("e1:MarineOrganism\r\n");
			writer.print("e2:Genus\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < MarOrgs.size(); i++) {
				Genus = q.getAllGenus(MarOrgs.get(i));
				for (int j = 0; j < Genus.size(); j++) {
					String marOrg = removePar(MarOrgs.get(i));
					String gen = removePar(Genus.get(j));
					writer.write(marOrg + ";" + gen);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genMarOrgWithFamily(OntologyQuery q) throws SQWRLException {
		List<String> MarOrgs = q.getAllMarineOrg();
		List<String> Families = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("MarineOrganism-Family.txt");
			writer.print("");
			writer.print("e1:MarineOrganism\r\n");
			writer.print("e2:Family\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < MarOrgs.size(); i++) {
				Families = q.getAllFamily(MarOrgs.get(i));
				for (int j = 0; j < Families.size(); j++) {
					String marOrg = removePar(MarOrgs.get(i));
					String family = removePar(Families.get(j));
					writer.write(marOrg + ";" + family);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genMarOrgWithLoc(OntologyQuery q) throws SQWRLException {
		List<String> MarOrgs = q.getAllMarineOrg();
		List<String> Locations = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("MarineOrganism-Location.txt");
			writer.print("");
			writer.print("e1:MarineOrganism\r\n");
			writer.print("e2:Location\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < MarOrgs.size(); i++) {
				Locations = q.getAllLocations(MarOrgs.get(i));
				for (int j = 0; j < Locations.size(); j++) {
					String marOrg = removePar(MarOrgs.get(i));
					String loc = removePar(Locations.get(j));
					writer.write(marOrg + ";" + loc);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genHabitatWithLoc(OntologyQuery q) throws SQWRLException {
		List<String> Habitats = q.getAllHabitats();
		List<String> Locations = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("Habitat-Location.txt");
			writer.print("");
			writer.print("e1:Habitat\r\n");
			writer.print("e2:Location\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < Habitats.size(); i++) {
				Locations = q.getAllLocations(Habitats.get(i));
				for (int j = 0; j < Locations.size(); j++) {
					String habitat = removePar(Habitats.get(i));
					String loc = removePar(Locations.get(j));
					writer.write(habitat + ";" + loc);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genHabitatWithEffect(OntologyQuery q) throws SQWRLException {
		List<String> Habitats = q.getAllHabitats();
		List<String> Effects = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("Habitat-Effect.txt");
			writer.print("");
			writer.print("e1:Habitat\r\n");
			writer.print("e2:Effect\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < Habitats.size(); i++) {
				Effects = q.getAllEffects(Habitats.get(i));
				for (int j = 0; j < Effects.size(); j++) {
					String habitat = removePar(Habitats.get(i));
					String effect = removePar(Effects.get(j));
					writer.write(habitat + ";" + effect);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genEffectWithFactor(OntologyQuery q) throws SQWRLException {
		List<String> Effects = q.getAllEffects();
		List<String> Factors = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("Effect-Factor.txt");
			writer.print("");
			writer.print("e1:Effect\r\n");
			writer.print("e2:Factor\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < Effects.size(); i++) {
				Factors = q.getAllFactors(Effects.get(i));
				for (int j = 0; j < Factors.size(); j++) {
					String effect = removePar(Effects.get(i));
					String factor = removePar(Factors.get(j));
					writer.write(effect + ";" + factor);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genEffectWithEffVal(OntologyQuery q) throws SQWRLException {
		List<String> Effects = q.getAllEffects();
		List<String> EffVals = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("Effect-EffectValue.txt");
			writer.print("");
			writer.print("e1:Effect\r\n");
			writer.print("e2:EffectValue\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < Effects.size(); i++) {
				EffVals = q.getAllEffectValues(Effects.get(i));
				for (int j = 0; j < EffVals.size(); j++) {
					String effect = removePar(Effects.get(i));
					String effval = removePar(EffVals.get(j));
					writer.write(effect + ";" + effval);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genFactorWithFactVal(OntologyQuery q) throws SQWRLException {
		List<String> Factors = q.getAllFactors();
		List<String> FactVals = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("Factor-FactorValues.txt");
			writer.print("");
			writer.print("e1:Factor\r\n");
			writer.print("e2:FactorValues\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < Factors.size(); i++) {
				FactVals = q.getAllFactorValues(Factors.get(i));
				for (int j = 0; j < FactVals.size(); j++) {
					String factor = removePar(Factors.get(i));
					String factval = removePar(FactVals.get(j));
					writer.write(factor + ";" + factval);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genGenusWithFamily(OntologyQuery q) throws SQWRLException {
		List<String> Genus = q.getAllGenus();
		List<String> Families = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("Genus-Families.txt");
			writer.print("");
			writer.print("e1:Genus\r\n");
			writer.print("e2:Families\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < Genus.size(); i++) {
				Families = q.getAllFamily(Genus.get(i));
				for (int j = 0; j < Families.size(); j++) {
					String genus = removePar(Genus.get(i));
					String family = removePar(Families.get(j));
					writer.write(genus + ";" + family);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genCNWithHabitat(OntologyQuery q) throws SQWRLException {
		List<String> CommonNames = q.getAllCommonNames();
		List<String> Habitats = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("CommonName-Habitat.txt");
			writer.print("");
			writer.print("e1:CommonName\r\n");
			writer.print("e2:Habitat\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < CommonNames.size(); i++) {
				Habitats = q.getAllHabitats(CommonNames.get(i));
				for (int j = 0; j < Habitats.size(); j++) {
					String commName = removePar(CommonNames.get(i));
					String habitat = removePar(Habitats.get(j));
					writer.write(commName + ";" + habitat);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genCNWithGenus(OntologyQuery q) throws SQWRLException {
		List<String> CommonNames = q.getAllCommonNames();
		List<String> Genus = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("CommonName-Genus.txt");
			writer.print("");
			writer.print("e1:CommonName\r\n");
			writer.print("e2:Genus\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < CommonNames.size(); i++) {
				Genus = q.getAllGenus(CommonNames.get(i));
				for (int j = 0; j < Genus.size(); j++) {
					String commName = removePar(CommonNames.get(i));
					String gen = removePar(Genus.get(j));
					writer.write(commName + ";" + gen);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genCNWithFamily(OntologyQuery q) throws SQWRLException {
		List<String> CommonNames = q.getAllCommonNames();
		List<String> Families = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("CommonName-Family.txt");
			writer.print("");
			writer.print("e1:CommonName\r\n");
			writer.print("e2:Family\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < CommonNames.size(); i++) {
				Families = q.getAllFamily(CommonNames.get(i));
				for (int j = 0; j < Families.size(); j++) {
					String commName = removePar(CommonNames.get(i));
					String family = removePar(Families.get(j));
					writer.write(commName + ";" + family);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public void genCNWithLoc(OntologyQuery q) throws SQWRLException {
		List<String> CommonNames = q.getAllCommonNames();
		List<String> Locations = new ArrayList<String>();
		try {
			PrintWriter writer = new PrintWriter("CommonName-Location.txt");
			writer.print("");
			writer.print("e1:CommonName\r\n");
			writer.print("e2:Location\r\n");
			writer.write("\r\n"); 
			
			for (int i = 0; i < CommonNames.size(); i++) {
				Locations = q.getAllLocations(CommonNames.get(i));
				for (int j = 0; j < Locations.size(); j++) {
					String commName = removePar(CommonNames.get(i));
					String loc = removePar(Locations.get(j));
					writer.write(commName + ";" + loc);
					writer.write("\r\n"); 

				}
			}
			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

    public String removePar(String string) {
		String newString = string;
		newString = newString.replaceAll("\\(.+\\)", "");
		return newString;
	}
}
