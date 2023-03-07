package preprocessing;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import service.OntologyQuery;
import service.Seed;

public class driver {
    private static final String owlPath = "C:\\OntoMarine.owl";
    public static void main(String[] args) throws OntologyLoadException, SQWRLException {
        // TODO Auto-generated method stub
        OntologyQuery q = new OntologyQuery(owlPath);
        Seed s = new Seed();
        s.generateSeed(q);
    }
}
