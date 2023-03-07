package model;

import java.util.ArrayList;

public class Habitat {
    String habitat;
    ArrayList<Location> locations;

    public Habitat(String habitat){
        this.habitat = habitat;
    }

    public String getHabitat(){
        return habitat;
    }

    public void setHabitat(String habitat){
        this.habitat = habitat;
    }
    public ArrayList<Location> getLocations(){
    	return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
    	this.locations = locations;
    }
}
