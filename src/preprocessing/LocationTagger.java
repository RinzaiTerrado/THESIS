package preprocessing;

import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

public class LocationTagger extends EntityTagger{
    private String classifier;

    public LocationTagger(String tag, String text, String filename){
        super(tag, text);

        this.classifier = filename;
    }

    public String run() throws ClassCastException, ClassNotFoundException, IOException{
        hideTagged();

        String serializedClassifier = classifier;

        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
        List<Triple<String, Integer, Integer>> triples = classifier.classifyToCharacterOffsets(text);

        for(Triple<String, Integer, Integer> trip: triples){
            if(trip.first.equals("LOCATION")){
                found.add(text.substring(trip.second, trip.third));

                Integer n = map.get(text.substring(trip.second, trip.third));
                n = (n == null) ? 1 : ++n;
                map.put(text.substring(trip.second, trip.third), n);
            }
            else if(trip.first.equals("PERSON")){

            }
        }

        tagEntities();
        resolveHiddenEntities();
        removeOverlappingTags();

        printEntityFrequencyCount();
        return text;
    }
}