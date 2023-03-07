package model;

public class Genus {
    String genus;
    Family family;

    public Genus(String genus){
        this.genus = genus;
    }

    public String getGenus(){
        return genus;
    }

    public void setGenus(String genus){
        this.genus = genus;
    }

    public Family getFamily(){
        return family;
    }

    public void setFamily(Family family){
        this.family = family;
    }
}
