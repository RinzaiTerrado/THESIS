package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.CommonName;
import model.Effect;
import model.Factor;
import model.Family;
import model.Genus;
import model.Habitat;
import model.Location;
import model.MarineOrganism;

public class OntologyQuery {
    String uri;
    OWLModel owlModel;

    public OntologyQuery(String owlPath) throws OntologyLoadException{
        owlPath = "insert link to directory";
        owlPath = owlPath.replace("\\", "/");
        this.owlModel = ProtegeOWL.createJenaOWLModelFromURI("file:///" + owlPath);
    }

    //Get all

    public List<String> getAllMarineOrg(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_MarineOrg");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("MarineOrganism")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }

   

    public List<String> getAllHabitats(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_Habitat");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Habitat")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }

    public List<String> getAllCommonNames(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_CommonNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonNames")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }

    public List<String> getAllGenus(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_Genus");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Genus")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }

    public List<String> getAllFamily(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_Family");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Family")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }

    public List<String> getAllLocations(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_Locations");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Locations")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }

    public List<String> getAllEffects(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_Effect");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Effect")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }

    public List<String> getAllEffectValues(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_EffectValues");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("EffectValues")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_MarineOrg).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }
    public List<String> getAllFactors(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_Factor = owlModel.getRDFProperty("datatypeProperty_Factor");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("EffectValues")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_Factor).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }
    public List<String> getAllFactorValues(){
        List<String> values = new ArrayList<String>();

        RDFProperty datatypeProperty_FactorValue = owlModel.getRDFProperty("datatypeProperty_FactorValue");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("EffectValues")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        values.add(individual.getPropertyValue(datatypeProperty_FactorValue).toString());
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return values;
    }
    public String getMarOrgCN(String marOrg){
        String result = "";
        String marOrgCN = null;
        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_CommonName");
        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (marOrg.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_MarineOrg)
                                .toString().toLowerCase())) {
                            marOrgCN = individual.getBrowserText();
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public String getMarOrgHabitat(String marOrg){
        String result = "";
        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_MarineOrg");
        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Habitat")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (marOrg.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_MarineOrg)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public String getMarOrgGenus(String marOrg){
        String result = "";
        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_MarineOrg");
        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Genus")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (marOrg.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_MarineOrg)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public String getMarOrgFamily(String marOrg){
        String result = "";
        RDFProperty datatypeProperty_MarineOrg = owlModel.getRDFProperty("datatypeProperty_MarineOrg");
        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("Family")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (marOrg.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_MarineOrg)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getMarOrgs(String cName){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_MarineOrganism = owlModel.getRDFProperty("datatypeProperty_MarineOrganism");
        RDFProperty datatypeProperty_CommonName = owlModel.getRDFProperty("datatypeProperty_CommonName");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (cName.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_CommonName)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_MarineOrganism).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_MarineOrganism).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getHabitats(String cName){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Habitat = owlModel.getRDFProperty("datatypeProperty_Habitat");
        RDFProperty datatypeProperty_CommonName = owlModel.getRDFProperty("datatypeProperty_CommonName");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (cName.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_CommonName)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Habitat).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Habitat).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getCNGenus(String cName){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
        RDFProperty datatypeProperty_CommonName = owlModel.getRDFProperty("datatypeProperty_CommonName");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (cName.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_CommonName)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Genus).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Genus).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getCNFamily(String cName){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
        RDFProperty datatypeProperty_CommonName = owlModel.getRDFProperty("datatypeProperty_CommonName");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (cName.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_CommonName)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Family).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Family).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getGenusFamily(String genus){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
        RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (genus.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Genus)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Family).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Family).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getFamilyGenus(String family){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
        RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (family.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Family)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Genus).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Genus).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getLocationCN(String loc){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_CommonName = owlModel.getRDFProperty("datatypeProperty_CommonName");
        RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (loc.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_CommonName)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Location).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Location).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getHabitatEff(String habitat){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Effect = owlModel.getRDFProperty("datatypeProperty_Effect");
        RDFProperty datatypeProperty_Habitat = owlModel.getRDFProperty("datatypeProperty_Habitat");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (habitat.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Effect)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Habitat).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Habitat).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getEffectFact(String habitat){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Factor = owlModel.getRDFProperty("datatypeProperty_Factor");
        RDFProperty datatypeProperty_Effect = owlModel.getRDFProperty("datatypeProperty_Effect");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (habitat.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Factor)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Effect).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Effect).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }
    public List<String> getHabitatLoc(String habitat){
        List<String> result = new ArrayList<String>();
        RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");
        RDFProperty datatypeProperty_Habitat = owlModel.getRDFProperty("datatypeProperty_Habitat");
        RDFProperty isLocatedIn = owlModel.getRDFProperty("isLocatedIn"); //double check

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for(Iterator it = classes.iterator(); it.hasNext();){
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if(cls.getBrowserText().contentEquals("CommonName")){
                for(Iterator jt = instances.iterator(); jt.hasNext();){
                    try{
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (habitat.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Habitat)
                                .toString().toLowerCase())) {
                            Collection locations = individual.getPropertyValues(isLocatedIn);
                            for (Iterator kt = locations.iterator(); kt.hasNext();) {
                                OWLIndividual locIndiv = (OWLIndividual) kt.next();
                                result.add(locIndiv.getPropertyValue(datatypeProperty_Location).toString()
                                        .substring(0, 1).toUpperCase()
                                        + locIndiv.getPropertyValue(datatypeProperty_Location).toString().substring(1));
                            }
                        }
                    } catch (Exception e){
                        System.out.println("Exception here");
                    }
                }
            }
        }
        return result;
    }

	public List<MarineOrganism> searchMarineOrganism(String MarineOrganism) {
		List<MarineOrganism> values = new ArrayList<MarineOrganism>();
		RDFProperty datatypeProperty_MarineOrganism = owlModel.getRDFProperty("datatypeProperty_MarineOrganism");
		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			if (cls.getBrowserText().contentEquals("MarineOrganism")) {
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					try {
						OWLIndividual individual = (OWLIndividual) jt.next();
						String MarineOrganismIndiv = individual.getPropertyValue(datatypeProperty_MarineOrganism).toString();
						// EDIT THIS CODE FOR OPTIMAL SEARCH FUNCTION
						if (MarineOrganismIndiv.toLowerCase().contains(MarineOrganism.toLowerCase())) {
//							System.err.println(medPlantIndiv);
							System.out.println(individual.getBrowserText());
							MarineOrganism mp = new MarineOrganism(MarineOrganismIndiv);

							String tempRes ="";
////							 get genus
							try {
								tempRes=(getMarOrgGenus(MarineOrganism));
								mp.setGenus(tempRes);
							} catch (Exception e) {
								System.err.println(e);
							}

							try {
								// get family
								tempRes=(getMarOrgFamily(MarineOrganism));
								mp.setFamily(tempRes);
							} catch (Exception e) {

							}

							values.add(mp);
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return values;
	}

	public List<CommonName> searchCommonName(String CommonName) {
		List<CommonName> values = new ArrayList<CommonName>();

		RDFProperty datatypeProperty_CommonName = owlModel.getRDFProperty("datatypeProperty_CommonName");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			for (Iterator jt = instances.iterator(); jt.hasNext();) {
				try {
					OWLIndividual cNameIndiv = (OWLIndividual) jt.next();
					String cNameValue = cNameIndiv.getPropertyValue(datatypeProperty_CommonName).toString();
					if (cNameValue.toLowerCase().contains(CommonName.toLowerCase())) {
						CommonName cNameClass = new CommonName(cNameValue);
						values.add(cNameClass);
					}
				} catch (Exception e) {
//						System.out.println("Exception here");
				}
			}

		}
		return values;
	}
	
	public List<Habitat> searchHabitat(String Habitat) {
		List<Habitat> values = new ArrayList<Habitat>();

		RDFProperty datatypeProperty_Habitat = owlModel.getRDFProperty("datatypeProperty_Habitat");

		Collection classes = owlModel.getUserDefinedOWLNamedClasses();
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			Collection instances = cls.getInstances(false);
			for (Iterator jt = instances.iterator(); jt.hasNext();) {
				try {
					OWLIndividual cNameIndiv = (OWLIndividual) jt.next();
					String cNameValue = cNameIndiv.getPropertyValue(datatypeProperty_Habitat).toString();
					if (cNameValue.toLowerCase().contains(Habitat.toLowerCase())) {
						Habitat cNameClass = new Habitat(cNameValue);
						values.add(cNameClass);
					}
				} catch (Exception e) {
//						System.out.println("Exception here");
				}
			}

		}
		return values;
	}
    public List<Genus> searchGenus(String genus) {
        List<Genus> values = new ArrayList<Genus>();

        RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
        RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
        RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("Genus")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual genusIndiv = (OWLIndividual) jt.next();
                        String genusValue = genusIndiv.getPropertyValue(datatypeProperty_Genus).toString();
                        if (genusValue.toLowerCase().contains(genus.toLowerCase())) {
                            Genus genusClass = new Genus(genusValue);
                            try {
                                OWLIndividual familyIndiv = (OWLIndividual) genusIndiv
                                        .getPropertyValue(belongsToFamily);
                                String familyValue = familyIndiv.getPropertyValue(datatypeProperty_Family).toString();
                                Family familyClass = new Family(familyValue);
                                genusClass.setFamily(familyClass);
                            } catch (Exception e) {
                            }
                            values.add(genusClass);
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return values;
    }
    public List<Family> searchFamily(String family) {
        List<Family> values = new ArrayList<Family>();

        RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
        RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
        RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("Family")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual familyIndiv = (OWLIndividual) jt.next();
                        String familyValue = familyIndiv.getPropertyValue(datatypeProperty_Family).toString();
                        if (familyValue.toLowerCase().contains(family.toLowerCase())) {
                            Family familyClass = new Family(familyValue);
                            values.add(familyClass);
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return values;
    }
    public List<String> searchLocations(String locs) throws SQWRLException {
        List<String> values = new ArrayList<String>();
        List<String> locations = getAllLocations();
        for (String loc : locations) {
            if (loc.toLowerCase().contains(locs.toLowerCase())) {
                // if(!values.contains(loc.toLowerCase()))
                values.add(loc);
            }
        }
        return values;
    }
    //return
    public ArrayList<MarineOrganism> getMarineOrganismList(OWLIndividual MarineOrganism) {
        RDFProperty datatypeProperty_MarineOrganism = owlModel.getRDFProperty("datatypeProperty_MarineOrganism");
        RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
        RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
        RDFProperty datatypeProperty_Document = owlModel.getRDFProperty("datatypeProperty_Document");

        RDFProperty hasScientificName = owlModel.getRDFProperty("hasScientificName");
        RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToGenus");
        RDFProperty belongsToFamily = owlModel.getRDFProperty("belongsToFamily");
        RDFProperty foundFromDocument = owlModel.getRDFProperty("foundFromDocument");

        ArrayList<MarineOrganism> marOrg = new ArrayList<MarineOrganism>();

        // This is for synonyms as objects
        Collection speciesCol2 = MarineOrganism.getPropertyValues(hasScientificName);
        for (Iterator it = speciesCol2.iterator(); it.hasNext();) {
            OWLIndividual sciNameIndiv = (OWLIndividual) it.next();
            MarineOrganism sp = new MarineOrganism(sciNameIndiv.getPropertyValue(datatypeProperty_MarineOrganism).toString());
            try {
                // get genus
                OWLIndividual genus = (OWLIndividual) sciNameIndiv.getPropertyValue(belongsToGenus);
                sp.setGenus(genus.getPropertyValue(datatypeProperty_Genus).toString());
                try {
                    // get family
                    OWLIndividual family = (OWLIndividual) genus.getPropertyValue(belongsToFamily);
                    sp.setFamily(family.getPropertyValue(datatypeProperty_Family).toString());
                } catch (Exception e) {
                    System.err.println("No Family");
                }
            } catch (Exception e) {
                System.err.println("No Genus");
            }

            try {
                OWLIndividual doc = (OWLIndividual) sciNameIndiv.getPropertyValue(foundFromDocument);
                sp.setDocument(doc.getPropertyValue(datatypeProperty_Document).toString());
            } catch (Exception e) {
                System.err.println("No Document");
            }
//				System.out.println(sp.getSpecie());
            marOrg.add(sp);
        }
        // If there is no synonym, create empty object
        if (marOrg.size() == 0) {
            marOrg.add(new MarineOrganism(""));
        }
        return marOrg;
    }
    //return
    public ArrayList<Location> getHabitatList(OWLIndividual Habitat) {
        RDFProperty datatypeProperty_Habitat = owlModel.getRDFProperty("datatypeProperty_Habitat");
        RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");

        RDFProperty belongsToGenus = owlModel.getRDFProperty("belongsToLocation");

        ArrayList<Location> locations = new ArrayList<Location>();

        Collection habitatCol = Habitat.getPropertyValues(datatypeProperty_Habitat);
        // This is for entities from OntoPHerb, synonyms as datatype prop
        for (Iterator it = habitatCol.iterator(); it.hasNext();) {
            String synonym = it.next().toString();
            try {
                if (!synonym.isEmpty()) {
                    Location loc = new Location(synonym);
                    locations.add(loc);
                    //				System.out.println(synonym);
                }
            } catch (Exception e) {
                System.err.println("No Locations");
            }
        }
//				System.out.println(sp.getSpecie());

        // If there is no synonym, create empty object
        if (locations.size() == 0) {
            locations.add(new Location(""));
        }
        return locations;
    }
    public ArrayList<Effect> getEffectList(OWLIndividual Effect) {
        RDFProperty datatypeProperty_Effect = owlModel.getRDFProperty("datatypeProperty_Effect");
        RDFProperty datatypeProperty_EffectValue = owlModel.getRDFProperty("datatypeProperty_EffectValue");
        //find out propertyvalue
        RDFProperty hasEffectValue = owlModel.getRDFProperty("hasEffectValue");

        ArrayList<Effect> effects = new ArrayList<Effect>();

        // This is for synonyms as objects
        Collection speciesCol2 = Effect.getPropertyValues(hasEffectValue);
        for (Iterator it = speciesCol2.iterator(); it.hasNext();) {
            OWLIndividual sciNameIndiv = (OWLIndividual) it.next();
            Effect sp = new Effect(sciNameIndiv.getPropertyValue(datatypeProperty_EffectValue).toString());
            try {
                // get effect value
                OWLIndividual genus = (OWLIndividual) sciNameIndiv.getPropertyValue(hasEffectValue);
                sp.setEffVal(genus.getPropertyValue(datatypeProperty_EffectValue).toString());

            } catch (Exception e) {
                System.err.println("No Effect Value");
            }
//				System.out.println(sp.getSpecie());
            effects.add(sp);
        }
        // If there is no synonym, create empty object
        if (effects.size() == 0) {
            effects.add(new Effect(""));
        }
        return effects;
    }
    public ArrayList<Factor> getFactorList(OWLIndividual Factor) {
        RDFProperty datatypeProperty_Factor = owlModel.getRDFProperty("datatypeProperty_Factor");
        RDFProperty datatypeProperty_FactorValue = owlModel.getRDFProperty("datatypeProperty_FactorValue");
        //find out propertyvalue
        RDFProperty hasFactorValue = owlModel.getRDFProperty("hasFactorValue");

        ArrayList<Factor> factors = new ArrayList<Factor>();

        // This is for synonyms as objects
        Collection speciesCol2 = Factor.getPropertyValues(hasFactorValue);
        for (Iterator it = speciesCol2.iterator(); it.hasNext();) {
            OWLIndividual sciNameIndiv = (OWLIndividual) it.next();
            Factor sp = new Factor(sciNameIndiv.getPropertyValue(datatypeProperty_FactorValue).toString());
            try {
                // get effect value
                OWLIndividual genus = (OWLIndividual) sciNameIndiv.getPropertyValue(datatypeProperty_Factor);
                sp.setFactVal(genus.getPropertyValue(datatypeProperty_FactorValue).toString());

            } catch (Exception e) {
                System.err.println("No Factor Value");
            }
//				System.out.println(sp.getSpecie());
            factors.add(sp);
        }
        // If there is no synonym, create empty object
        if (factors.size() == 0) {
            factors.add(new Factor(""));
        }
        return factors;
    }
    public String getMarOrgIndivName(String marOrgName) throws SQWRLException {
        RDFProperty datatypeProperty_MarineOrganism = owlModel.getRDFProperty("datatypeProperty_MarineOrganism");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (marOrgName.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_MarineOrganism)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }
    public String getCommNameIndivName(String CommonName) throws SQWRLException {
        RDFProperty datatypeProperty_CommonName = owlModel.getRDFProperty("datatypeProperty_CommonName");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (CommonName.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_CommonName)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }
    public String getHabitatIndivName(String Habitat) throws SQWRLException {
        RDFProperty datatypeProperty_Habitat = owlModel.getRDFProperty("datatypeProperty_Habitat");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (Habitat.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Habitat)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }
    public String getGenusIndivName(String Genus) throws SQWRLException {
        RDFProperty datatypeProperty_Genus = owlModel.getRDFProperty("datatypeProperty_Genus");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (Genus.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Genus)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }
    public String getFamilyIndivName(String Family) throws SQWRLException {
        RDFProperty datatypeProperty_Family = owlModel.getRDFProperty("datatypeProperty_Family");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (Family.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Family)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }
    public String getLocIndivName(String Loc) throws SQWRLException {
        RDFProperty datatypeProperty_Location = owlModel.getRDFProperty("datatypeProperty_Location");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (Loc.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Location)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }
    public String getEffectIndivName(String Effect) throws SQWRLException {
        RDFProperty datatypeProperty_Effect = owlModel.getRDFProperty("datatypeProperty_Effect");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (Effect.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Effect)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }
    public String getFactorIndivName(String Factor) throws SQWRLException {
        RDFProperty datatypeProperty_Factor = owlModel.getRDFProperty("datatypeProperty_Factor");
        String result = null;
//		System.out.println("Entered Get All PlantNames");

        Collection classes = owlModel.getUserDefinedOWLNamedClasses();
        for (Iterator it = classes.iterator(); it.hasNext();) {
            OWLNamedClass cls = (OWLNamedClass) it.next();
            Collection instances = cls.getInstances(false);
            if (cls.getBrowserText().contentEquals("MarineOrganism")) {
                for (Iterator jt = instances.iterator(); jt.hasNext();) {
                    try {
                        OWLIndividual individual = (OWLIndividual) jt.next();
                        if (Factor.equalsIgnoreCase(individual.getPropertyValue(datatypeProperty_Factor)
                                .toString().toLowerCase())) {
                            result = individual.getBrowserText();
                        }
                    } catch (Exception e) {
//						System.out.println("Exception here");
                    }
                }
            }

        }
        return result;
    }

}
