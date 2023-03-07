package preprocessing;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coref {
    private String text;

    public Coref(String text){
        this.text = text;
    }

    public String findPronoun(String str, String lastEnt){
        String[] words = str.split(" ");
        for(String word: words){
            if(word.endsWith("_PRP")){
                str = str.replaceAll(word, lastEnt);
            }
        }

        return str;
    }

    public String run2() throws IOException{
        MaxentTagger mt = new MaxentTagger("insertfile");
        String subject = "";
        String corefText = "";

        String[] sentences = text.split("\n\n");

        for(String sentence: sentences){
            Pattern p = Pattern.compile("<(\\w+)>([^<]+)<\\/\\w+>");
            Matcher m = p.matcher(sentence);

            sentence = mt.tagTokenizedString(sentence.trim());

            if(!sentence.contains("_PRP")){
                while(m.find()){
                    if(m.group(1).equals("Compound") || m.group(1).equals("Synonym")){
                        subject = m.group();

                        break;
                    }
                }
                corefText += sentence.trim().replaceAll("_[A-Z$,.]{1,4}","") + "\n\n";
            }
            else{
                corefText += findPronoun(sentence, subject).trim().replaceAll("_[A-Z$,.]{1,4}", "") + "\n\n";
            }
        }
        return corefText;
    }

    public String run(){
        String[] sentences = text.split("\n\n");
        String subject = "";

        for(int i=0; i<sentences.length; i++) {

            Pattern p = Pattern.compile("<(\\w+)>([^<]+)<\\/\\w+>");
            Matcher m = p.matcher(sentences[i]);

            sentences[i] = sentences[i].trim();

            if(!sentences[i].matches("(.*)\\bIt\\b(.*)")) {
                while(m.find()) {
                    if(m.group(1).equals("Compound") || m.group(1).equals("Synonym") || m.group(1).equals("MedicinalPlant")) {
                        subject = m.group();

                        break;
                    }

                }
            } else {

                sentences[i] = sentences[i].replaceAll("\\b[Ii]t\\b", subject);

            }
        }

        return String.join("\n\n", sentences);
    }
}