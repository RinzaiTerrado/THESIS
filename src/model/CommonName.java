package model;

import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;

public class CommonName {
    String commonName;
    String genus;
    String family;
    String document;
    Genus genusObj;
    String marineOrg;
    public CommonName(String commonName){
        this.marineOrg = StringUtils.capitalize(marineOrg);
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
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
}
