package service;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import java.io.File;

public class OntologyManager {
    //Classes
    public static String CLASS_Habitat = "Habitat";
    public static String CLASS_MarOrg = "MarineOrganism";
    public static String CLASS_CommName = "CommonName";
    public static String CLASS_Location = "Location";
    public static String CLASS_Genus = "Genus";
    public static String CLASS_Family = "Family";
    public static String CLASS_Effect = "Effect";
    public static String CLASS_EffVal = "EffectValue";
    public static String CLASS_AffFact = "AffectedByFactor";
    public static String CLASS_FactVal = "FactorValue";

    //Data Properties

    //Object Properties

    private OWLOntology owlOntology;
    private OWLOntologyManager owlManager;
    private OWLDataFactory owlFactory;
    private PrefixManager pm;
    private File owlFile;
    private String base = "http://www.owl-ontologies.com/CCOS.owl";
    private IRI owlIRI;

    private OWLDatatype datatypeString;

    private OWLDataProperty dataProperty;

    private OWLClassAssertionAxiom classAssertion;
    private OWLDataPropertyAssertionAxiom dataPropAssertion;
    private OWLObjectPropertyAssertionAxiom objAssertion;

    private OWLDataPropertyRangeAxiom rangeAxiom;
    private OWLAxiom axiomDataPrep;
    private AddAxiom axiomObjPrep;

    //OWL Classes
    private OWLClass habitatClass, marOrgClass, commNameClass, locationClass, genusClass, familyClass, effectClass,
            effValClass, affFactClass, factValClass;

    //OWL Individual
    private OWLNamedIndividual habitatIndiv, marOrgIndiv, commNameIndiv, locationIndiv, genusIndiv, familyIndiv, effectIndiv,
            effValIndiv, affFactIndiv, factValIndiv;

    //OWL Obj Properties
    OWLObjectProperty isLocatedIn, isAffectedBy, belongsToGenus, hasCommonName, isPrimary, belongsToFamily, causedBy,
    effectValueIs, factorValueIs;

    private String owlPath;

    public OntologyManager(String owlPath) throws OWLOntologyCreationException, OWLOntologyStorageException {
        owlManager = OWLManager.createOWLOntologyManager();
        owlFile = new File("C:\\Users\\John Gabriel Legaspi\\Desktop\\OntoCCOS.owl");
        this.owlPath = "C:\\Users\\John Gabriel Legaspi\\Desktop\\OntoCCOS.owl";
        owlOntology = owlManager.loadOntologyFromOntologyDocument(owlFile);
        pm = new DefaultPrefixManager(base);
        owlIRI = owlManager.getOntologyDocumentIRI(owlOntology);
        owlFactory = owlManager.getOWLDataFactory();
        datatypeString = owlFactory.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());

        initClasses();
    }

    public static String cleanString(String str){
        str = str.toLowerCase();
        str = str.replaceAll("\\s+", "_");
        str = str.replaceAll(",+", ".");
        str = str.replaceAll("&#945;", "alpha");
        str = str.replaceAll("&#946;", "beta");
        str = str.replaceAll("&#947;", "gamma");
        str = str.trim();

        return str;
    }

    public void initClasses(){
        habitatClass = owlFactory.getOWLClass("#" + CLASS_Habitat, pm);
        marOrgClass = owlFactory.getOWLClass("#" + CLASS_MarOrg, pm);
        commNameClass = owlFactory.getOWLClass("#" + CLASS_CommName, pm);
        locationClass = owlFactory.getOWLClass("#" + CLASS_Location, pm);
        genusClass = owlFactory.getOWLClass("#" + CLASS_Genus, pm);
        familyClass = owlFactory.getOWLClass("#" + CLASS_Family, pm);
        effectClass = owlFactory.getOWLClass("#" + CLASS_Effect, pm);
        effValClass = owlFactory.getOWLClass("#" + CLASS_EffVal, pm);
        affFactClass = owlFactory.getOWLClass("#" + CLASS_AffFact, pm);
        factValClass = owlFactory.getOWLClass("#" + CLASS_FactVal, pm);
    }

    //Add Indiv
    public void addIndiv_Habitat(String Habitat) throws OWLOntologyStorageException{
        //Creating an Indiv
        habitatClass = owlFactory.getOWLClass("#Habitat", pm);
        habitatIndiv = owlFactory.getOWLNamedIndividual("#" + Habitat.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(habitatClass, habitatIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addIndiv_MarineOrganism(String MarineOrg) throws OWLOntologyStorageException{
        marOrgClass = owlFactory.getOWLClass("#MarineOrganism", pm);
        marOrgIndiv = owlFactory.getOWLNamedIndividual("#" + MarineOrg.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(marOrgClass, marOrgIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addIndiv_Genus(String Genus) throws OWLOntologyStorageException{
        genusClass = owlFactory.getOWLClass("#Genus", pm);
        genusIndiv = owlFactory.getOWLNamedIndividual("#" + Genus.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(genusClass, genusIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addIndiv_Family(String Family) throws OWLOntologyStorageException{
        familyClass = owlFactory.getOWLClass("#Family", pm);
        familyIndiv = owlFactory.getOWLNamedIndividual("#" + Family.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(familyClass, familyIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addIndiv_Effect(String Effect) throws OWLOntologyStorageException{
        effectClass = owlFactory.getOWLClass("#Effect", pm);
        effectIndiv = owlFactory.getOWLNamedIndividual("#" + Effect.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(effectClass, effectIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addIndiv_Factor(String Factor) throws OWLOntologyStorageException{
        affFactClass = owlFactory.getOWLClass("#AffectedByFactor", pm);
        affFactIndiv = owlFactory.getOWLNamedIndividual("#" + Factor.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(affFactClass, affFactIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    //Add Datatype Property

    //Add Object Property
    public void addObjectIsLocatedIn(String habitat, String location) throws OWLOntologyStorageException{
        habitatIndiv = owlFactory.getOWLNamedIndividual("#" + habitat, pm);
        locationIndiv = owlFactory.getOWLNamedIndividual("#" + location, pm);
        isLocatedIn = owlFactory.getOWLObjectProperty("#isLocatedIn", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(isLocatedIn, habitatIndiv, locationIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public OWLClass getHabitatClass() {
        return habitatClass;
    }

    public OWLClass getMarOrgClass() {
        return marOrgClass;
    }

    public OWLClass getCommNameClass() {
        return commNameClass;
    }

    public OWLClass getLocationClass() {
        return locationClass;
    }

    public OWLClass getGenusClass() {
        return genusClass;
    }

    public OWLClass getFamilyClass() {
        return familyClass;
    }

    public OWLClass getEffectClass() {
        return effectClass;
    }

    public OWLClass getEffValClass() {
        return effValClass;
    }

    public OWLClass getAffFactClass() {
        return affFactClass;
    }

    public OWLClass getFactValClass() {
        return factValClass;
    }

    public void setHabitatIndiv(OWLNamedIndividual habitatIndiv) {
        this.habitatIndiv = habitatIndiv;
    }

    public void setMarOrgIndiv(OWLNamedIndividual marOrgIndiv) {
        this.marOrgIndiv = marOrgIndiv;
    }

    public void setCommNameIndiv(OWLNamedIndividual commNameIndiv) {
        this.commNameIndiv = commNameIndiv;
    }

    public void setLocationIndiv(OWLNamedIndividual locationIndiv) {
        this.locationIndiv = locationIndiv;
    }

    public void setGenusIndiv(OWLNamedIndividual genusIndiv) {
        this.genusIndiv = genusIndiv;
    }

    public void setFamilyIndiv(OWLNamedIndividual familyIndiv) {
        this.familyIndiv = familyIndiv;
    }

    public void setEffectIndiv(OWLNamedIndividual effectIndiv) {
        this.effectIndiv = effectIndiv;
    }

    public void setEffValIndiv(OWLNamedIndividual effValIndiv) {
        this.effValIndiv = effValIndiv;
    }

    public void setAffFactIndiv(OWLNamedIndividual affFactIndiv) {
        this.affFactIndiv = affFactIndiv;
    }

    public void setFactValIndiv(OWLNamedIndividual factValIndiv) {
        this.factValIndiv = factValIndiv;
    }
}
