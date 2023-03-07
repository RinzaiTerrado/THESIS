package preprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class FamilyTagger extends LookUpEntityTagger {
    private Set<String> commonWords;

    public FamilyTagger(String tag, String text, String filename, String filename2) throws IOException{
        super(tag, text,filename);

        commonWords = new ReadLexiconFile(filename2).getContentsInHash();
    }

    public String run() throws IOException{
        HashSet<String> stopwords = new HashSet<>();
        stopwords.add("in");
        stopwords.add("and");
        stopwords.add("from");

        readLexiconFile();

        setSuffix("\\s(\\b[a-z-]+)");

        compilePatterns();

        patterns.add(Pattern.compile("[A-Z]\\.\\s([a-z]){4,}"));

        patterns.add(Pattern.compile("\\s([A-Z]{2})\\s"));

        hideTagged();
        findEntities();

        for (String i : map.keySet()) {
            //System.out.println("value: " + map.get(i) + "\tkey: " + i);
            if(i.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$") && i.length()==2) { // Removes Roman Numeral found as species
                getFound().remove(i);
                System.out.println("Removed: "+ i);
            } else if(i.split(" ").length==2 && (stopwords.contains(i.split(" ")[1]) || commonWords.contains(i.split(" ")[1]))) {
                getFound().remove(i);
                System.out.println("Removed: "+ i);
            }
        }

        tagEntities();
        resolveHiddenEntities();

        printEntityFrequencyCount();

        return text;
    }
}
