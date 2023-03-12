package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class MarineOrganism {
    String marineOrg;
    String commName;
    String genus;
    String family;
    String document;
    String habitat;
    List <String> locations;
    Genus genusObj;

    public MarineOrganism(String marineOrg){
        this.marineOrg = StringUtils.capitalize(marineOrg);
    }

    public String getMarineOrg() {
        return marineOrg;
    }

    public void setMarineOrg(String marineOrg) {
        this.marineOrg = marineOrg;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Genus getGenusObj() {
        return genusObj;
    }

    public void setGenus(Genus genusObj) {
        this.genusObj = genusObj;
    }

    public String getCommName() {
    	return commName;
    }
    
    public void setCommName(String commName) {
    	this.commName = commName;
    }

    public String getHabitat() {
    	return habitat;
    }
    
    public void setHabitat(String habitat) {
    	this.habitat = habitat;
    }
    
    public List <String> getLocations() {
    	return locations;
    }
    
    public void setLocations(List <String> locations) {
    	this.locations = locations;
    }

}
