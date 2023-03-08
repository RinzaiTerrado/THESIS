package service;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    OWLObjectProperty isLocatedIn, isAffectedBy, belongsToGenus, hasCommonName, livesIn, belongsToFamily, causedBy,
    effectValueIs, factorValueIs;

    private String owlPath;

    /*
     *
     *
     *
     * IMPORTANT!!!!!!!!!!!! CHECK PATH BELOW
     *
     *
     *
     */
    public OntologyManager(String owlPath) throws OWLOntologyCreationException, OWLOntologyStorageException {
        owlManager = OWLManager.createOWLOntologyManager();
        owlFile = new File("C:\\Users\\JR\\Desktop\\OntoCCOS.owl");
        this.owlPath = "C:\\Users\\JR\\Desktop\\OntoCCOS.owl";
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

    public void changeNameIndividual(String oldVal, String newVal) throws IOException {
        Path path = Paths.get(owlPath);
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);

        /*
         * str1 =
         * "<datatypeProperty_Compound rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">"
         * +oldVal+"</datatypeProperty_Compound>"; str2 =
         * "<datatypeProperty_Compound rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">"
         * +newVal+"</datatypeProperty_Compound>"; content = content.replaceAll(str1,
         * str2);
         *
         * str1 = "<datatypeProperty_Compound>"+oldVal+"</datatypeProperty_Compound>";
         * str2 = "<datatypeProperty_Compound>"+newVal+"</datatypeProperty_Compound>";
         * content = content.replaceAll(str1, str2);
         */

        // Common Name
        String str1 = "<hasCommonName rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl" + cleanString(oldVal)
                + "\"/>";
        String str2 = "<hasCommonName rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl" + cleanString(newVal)
                + "\"/>";
        content = content.replaceAll(str1, str2);

        // Genus
        str1 = "<belongsToGenus rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(oldVal)
                + "\"/>";
        str2 = "<belongsToGenus rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(newVal)
                + "\"/>";
        content = content.replaceAll(str1, str2);

        // Family
        str1 = "<belongsToFamily rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(oldVal)
                + "\"/>";
        str2 = "<belongsToFamily rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(newVal)
                + "\"/>";
        content = content.replaceAll(str1, str2);

        // Location
        str1 = "<isLocatedIn rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(oldVal)
                + "\"/>";
        str2 = "<isLocatedIn rdf:resource=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(newVal)
                + "\"/>";
        content = content.replaceAll(str1, str2);

        str1 = "<owl:NamedIndividual rdf:about=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(oldVal)
                + "\">";
        str2 = "<owl:NamedIndividual rdf:about=\"http://www.owl-ontologies.com/CCOS.owl#" + cleanString(newVal)
                + "\">";
        content = content.replaceAll(str1, str2);

        Files.write(path, content.getBytes(charset));
    }

    public void removeObjectPropertyValue(String indivName, String objPropName, String oldVal)
            throws OWLOntologyStorageException {
        if (oldVal != null) {
            OWLIndividual indiv = owlFactory.getOWLNamedIndividual("#" + indivName.trim(), pm);
            OWLIndividual oldIndiv = owlFactory.getOWLNamedIndividual("#" + oldVal.trim(), pm);
            // OWLObjectProperty objProp = owlFact.getOWLObjectProperty("#" + objPropName,
            // pm);

            OWLObjectPropertyExpression objProp = owlFactory.getOWLObjectProperty("#" + objPropName, pm);

            OWLObjectPropertyAssertionAxiom opa = owlFactory.getOWLObjectPropertyAssertionAxiom(objProp, indiv, oldIndiv);
            RemoveAxiom r = new RemoveAxiom(owlOntology, opa);
            owlManager.applyChange(r);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }

    public void removeDataPropertyValue(String indivName, String datapropName, String oldVal)
            throws OWLOntologyStorageException {
        if (oldVal != null) {
            OWLIndividual indiv = owlFactory.getOWLNamedIndividual("#" + indivName.trim(), pm);
            OWLDataProperty dataprop = owlFactory.getOWLDataProperty("#" + datapropName, pm);

            OWLDataPropertyAssertionAxiom dpa = owlFactory.getOWLDataPropertyAssertionAxiom(dataprop, indiv,
                    oldVal.trim());
            RemoveAxiom r = new RemoveAxiom(owlOntology, dpa);
            owlManager.applyChange(r);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }

    public void changeDataProperty(OWLClass owlClass, String indivName, String datapropName, String oldVal,
                                   String newVal) throws OWLOntologyStorageException {
        if (oldVal != null) {
            OWLIndividual indiv = owlFactory.getOWLNamedIndividual("#" + indivName.trim(), pm);
            OWLDataProperty dataprop = owlFactory.getOWLDataProperty("#" + datapropName, pm);

            if (newVal.equals("") || newVal == null) {
                removeDataPropertyValue(indivName, datapropName, oldVal);
            } else {
                OWLDataPropertyAssertionAxiom dpa = owlFactory.getOWLDataPropertyAssertionAxiom(dataprop, indiv,
                        oldVal.trim());
                RemoveAxiom r = new RemoveAxiom(owlOntology, dpa);
                owlManager.applyChange(r);

                OWLDataPropertyRangeAxiom rangeAx = owlFactory.getOWLDataPropertyRangeAxiom(dataprop, datatypeString);
                owlManager.addAxiom(owlOntology, rangeAx);

                dpa = owlFactory.getOWLDataPropertyAssertionAxiom(dataprop, indiv, newVal.trim());
                OWLAxiom aDataProp = owlFactory.getOWLDataPropertyDomainAxiom(dataprop, owlClass);
                owlManager.addAxiom(owlOntology, aDataProp);

                owlManager.addAxiom(owlOntology, dpa);
                owlManager.saveOntology(owlOntology, IRI.create(owlFile));
            }
        }
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
    public void addIndiv_Location(String location) throws OWLOntologyCreationException, OWLOntologyStorageException {
        // Creating Individual
        locationClass = owlFactory.getOWLClass("#Location", pm);
        locationIndiv = owlFactory.getOWLNamedIndividual("#" + location.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(locationClass, locationIndiv);
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
    public void addIndiv_EffectVal(String EffectVal) throws OWLOntologyStorageException{
        effValClass = owlFactory.getOWLClass("#EffectValue", pm);
        effValIndiv = owlFactory.getOWLNamedIndividual("#" + EffectVal.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(effValClass, effValIndiv);
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
    public void addIndiv_FactorVal(String FactorVal) throws OWLOntologyStorageException{
        factValClass = owlFactory.getOWLClass("#FactorValue", pm);
        factValIndiv = owlFactory.getOWLNamedIndividual("#" + FactorVal.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(factValClass, factValIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addIndiv_CommName(String CommName) throws OWLOntologyStorageException{
        commNameClass = owlFactory.getOWLClass("#CommonName", pm);
        commNameIndiv = owlFactory.getOWLNamedIndividual("#" + CommName.trim(), pm);
        classAssertion = owlFactory.getOWLClassAssertionAxiom(commNameClass, commNameIndiv);
        owlManager.addAxiom(owlOntology, classAssertion);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    //Add Datatype Property
    public void addDataPropHabitat(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_Habitat", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, habitatIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, habitatClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }

    public void addDataPropLocation(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_Location", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, locationIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, locationClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }

    public void addDataPropMarineOrganism(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_MarineOrganism", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, marOrgIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, marOrgClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }
    public void addDataPropEffect(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_Effect", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, effectIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, effectClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }
    public void addDataPropEffectVal(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_EffectValue", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, effValIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, effValClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }
    public void addDataPropFactor(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_AffectedByFactor", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, affFactIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, affFactClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }
    public void addDataPropFactorVal(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_FactorValue", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, factValIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, factValClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }
    public void addDataPropFamily(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_Family", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, familyIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, familyClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }
    public void addDataPropGenus(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_Genus", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, genusIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, genusClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }

    public void addDataPropCommName(String Value) throws OWLOntologyStorageException {
        if (!Value.equals("")) {
            // Creating Data Property, Range, and Value
            dataProperty = owlFactory.getOWLDataProperty("#datatypeProperty_CommonName", pm);
            rangeAxiom = owlFactory.getOWLDataPropertyRangeAxiom(dataProperty, datatypeString);
            owlManager.addAxiom(owlOntology, rangeAxiom);
            dataPropAssertion = owlFactory.getOWLDataPropertyAssertionAxiom(dataProperty, commNameIndiv, Value);
            axiomDataPrep = owlFactory.getOWLDataPropertyDomainAxiom(dataProperty, commNameClass);
            owlManager.addAxiom(owlOntology, axiomDataPrep);

            owlManager.addAxiom(owlOntology, dataPropAssertion);
            owlManager.saveOntology(owlOntology, IRI.create(owlFile));
        }
    }
    //Add Object Property
    public void addObjectBelongsToFamily(String genus, String family) throws OWLOntologyStorageException{
        genusIndiv = owlFactory.getOWLNamedIndividual("#" + genus, pm);
        familyIndiv = owlFactory.getOWLNamedIndividual("#" + family, pm);
        belongsToFamily = owlFactory.getOWLObjectProperty("#belongsToFamily", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(belongsToFamily, genusIndiv, familyIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectBelongsToGenus(String genus, String marineOrganism) throws OWLOntologyStorageException{
        genusIndiv = owlFactory.getOWLNamedIndividual("#" + genus, pm);
        marOrgIndiv = owlFactory.getOWLNamedIndividual("#" + marineOrganism, pm);
        belongsToGenus = owlFactory.getOWLObjectProperty("#belongsToGenus", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(belongsToGenus, genusIndiv, marOrgIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectCausedBy(String effect, String factor) throws OWLOntologyStorageException{
        effectIndiv = owlFactory.getOWLNamedIndividual("#" + effect, pm);
        affFactIndiv = owlFactory.getOWLNamedIndividual("#" + factor, pm);
        causedBy = owlFactory.getOWLObjectProperty("#causedBy", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(causedBy, effectIndiv, affFactIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectEffectValueIs(String effect, String effVal) throws OWLOntologyStorageException{
        effectIndiv = owlFactory.getOWLNamedIndividual("#" + effect, pm);
        effValIndiv = owlFactory.getOWLNamedIndividual("#" + effVal, pm);
        effectValueIs = owlFactory.getOWLObjectProperty("#effectValueIs", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(effectValueIs, effectIndiv, effValIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectFactorValueIs(String factor, String factVal) throws OWLOntologyStorageException{
        affFactIndiv = owlFactory.getOWLNamedIndividual("#" + factor, pm);
        factValIndiv = owlFactory.getOWLNamedIndividual("#" + factVal, pm);
        factorValueIs = owlFactory.getOWLObjectProperty("#factorValueIs", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(factorValueIs, affFactIndiv, factValIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectHasCommonName(String marineOrganism, String commName) throws OWLOntologyStorageException{
        marOrgIndiv = owlFactory.getOWLNamedIndividual("#" + marineOrganism, pm);
        commNameIndiv = owlFactory.getOWLNamedIndividual("#" + commName, pm);
        hasCommonName = owlFactory.getOWLObjectProperty("#hasCommonName", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(hasCommonName, marOrgIndiv, commNameIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectIsAffectedBy(String habitat, String effect) throws OWLOntologyStorageException{
        habitatIndiv = owlFactory.getOWLNamedIndividual("#" + habitat, pm);
        effectIndiv = owlFactory.getOWLNamedIndividual("#" + effect, pm);
        isAffectedBy = owlFactory.getOWLObjectProperty("#isAffectedBy", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(isAffectedBy, habitatIndiv, effectIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectIsLocatedIn(String habitat, String location) throws OWLOntologyStorageException{
        habitatIndiv = owlFactory.getOWLNamedIndividual("#" + habitat, pm);
        locationIndiv = owlFactory.getOWLNamedIndividual("#" + location, pm);
        isLocatedIn = owlFactory.getOWLObjectProperty("#isLocatedIn", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(isLocatedIn, habitatIndiv, locationIndiv);
        axiomObjPrep = new AddAxiom(owlOntology, objAssertion);
        owlManager.applyChange(axiomObjPrep);
        owlManager.saveOntology(owlOntology, IRI.create(owlFile));
    }

    public void addObjectLivesIn(String marineOrganism, String habitat) throws OWLOntologyStorageException{
        habitatIndiv = owlFactory.getOWLNamedIndividual("#" + habitat, pm);
        marOrgIndiv = owlFactory.getOWLNamedIndividual("#" + marineOrganism, pm);
        livesIn = owlFactory.getOWLObjectProperty("#livesIn", pm);
        objAssertion = owlFactory.getOWLObjectPropertyAssertionAxiom(isLocatedIn, habitatIndiv, marOrgIndiv);
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
