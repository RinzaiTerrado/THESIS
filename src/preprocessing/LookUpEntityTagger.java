package preprocessing;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LookUpEntityTagger extends EntityTagger{
    protected String filename;
    protected List<String> lexicon;
    protected List<Pattern> patterns;
    protected String prefix;
    protected String suffix;

    public LookUpEntityTagger(String tag, String text, String filename){
        super(tag, text);

        this.filename = filename;

        lexicon = new ArrayList<String>();
        patterns = new ArrayList<Pattern>();
        prefix = "";
        suffix = "";
    }

    public LookUpEntityTagger readLexiconFile() throws IOException{
        lexicon = new ReadLexiconFile(filename).getContents();

        return this;
    }

    public LookUpEntityTagger sortLexiconFile() throws IOException{
        new SortbyStringLength(filename);

        return this;
    }

    public LookUpEntityTagger findEntities(){
        for(Pattern pattern : patterns){
            Matcher matcher = pattern.matcher(text);
            while(matcher.find()){
                if(!found.contains(matcher.group().trim())){
                    found.add(matcher.group().trim());
                }
                Integer n = map.get(matcher.group().trim());
                n = (n == null) ? 1 : ++n;
                map.put(matcher.group().trim(), n);
            }
        }

        return this;
    }

    public LookUpEntityTagger findEntities_Insensitive(){
        for(Pattern pattern : patterns){
            Matcher matcher = pattern.matcher(text);

            while(matcher.find()){
                if(!found.contains(matcher.group().toLowerCase().trim())){
                    found.add(matcher.group().toLowerCase().trim());
                }
                Integer n = map.get(matcher.group().toLowerCase().trim());
                n = (n == null) ? 1 : ++n;
                map.put(matcher.group().trim().toLowerCase(), n);
            }
        }

        return this;
    }

    public LookUpEntityTagger compilePatterns() throws IOException {
        System.out.println("Compiling " + filename + " Patterns...");
        long startTime, endTime;
        startTime = System.nanoTime();

        patterns = new ArrayList<>();

        for(String d : lexicon) {
            patterns.add(Pattern.compile(prefix + d + suffix));
        }

        endTime = System.nanoTime();
        System.err.println("[Compiled "+filename+" Patterns] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");

        return this;
    }

    public LookUpEntityTagger compilePatternsInsensitive() throws IOException {
        System.out.println("Compiling " + filename + " Patterns...");
        long startTime, endTime;
        startTime = System.nanoTime();

        patterns = new ArrayList<>();

        for(String d : lexicon) {
            patterns.add(Pattern.compile(prefix + d + suffix, Pattern.CASE_INSENSITIVE));
        }

        endTime = System.nanoTime();
        System.err.println("[Compiled "+filename+" Patterns] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");

        return this;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getLexicon() {
        return lexicon;
    }

    public void setLexicon(List<String> lexicon) {
        this.lexicon = lexicon;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}