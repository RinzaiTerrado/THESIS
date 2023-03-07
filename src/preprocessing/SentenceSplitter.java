package preprocessing;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceSplitter {
    private String text = "";
    private String sText = "";

    public SentenceSplitter(String text) {
        System.out.println("Splitting Sentences...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        this.text = text;

        /*
        Properties props=new Properties();
        props.put("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = null;

        annotation = new Annotation(text);
        pipeline.annotate(annotation);

        //String []sent = text.split("\\. [A-Z][a-z]{2,}");

        //System.out.println(Arrays.toString("text".split("(?<=[A-Z]?[a-z]+\\. ?")));

        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            sstext +=  sentence + "\n\n";
        }


        for(String s : sent) {
        	sstext +=  s + "\n\n";
        }*/

        sText = text;

        //[A-Za-z\\)]{2,}\\. ([A-Z][a-z]+|[0-9])
        //Pattern pattern = Pattern.compile("[A-Za-z0-9\\)%]{2,}\\. (A|[A-Z][a-z]+|[0-9]|[A-Z]+)");
        Pattern pattern = Pattern.compile("[A-Za-z0-9\\)%\\.]{2,}\\. (A|[A-Z][a-z]+|[0-9]|[A-Z]+)");
        Matcher matcher = pattern.matcher(sText);
        while(matcher.find()) {
            //System.err.println(m.group());
            String str = matcher.group().replaceAll("\\.", "\\.\n\n");
            sText = sText.replaceAll(matcher.group().replaceAll("\\)", "\\\\)"), str);
        }

        sText = sText.replaceAll(" \\. ", ".\n\n");

        /* sstext = sstext.replaceFirst("^.*?[\\.!\\?](?:\\s|$)\\n", ""); */

        endTime = System.nanoTime ();
        System.err.println("[Sentence Splitter] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
    }

    /*
    public SentenceSplitter(String text, String a) {
        System.out.println("Splitting Sentences...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        this.text = text;

        String paragraph = "My 1st sentence. â€œDoes it work for questions?â€� My third sentence.";
        Reader reader = new StringReader(paragraph);
        DocumentPreprocessor dp = new DocumentPreprocessor(reader);
        List<String> sentenceList = new ArrayList<String>();

        for (List<HasWord> sentence : dp) {
            // SentenceUtils not Sentence
            String sentenceString = SentenceUtils.listToString(sentence);
            sentenceList.add(sentenceString);
        }

        for (String sentence : sentenceList) {
            System.out.println(sentence);
        }

        Properties props=new Properties();
        props.put("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = null;

        annotation = new Annotation(text);
        pipeline.annotate(annotation);

        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            sstext += "<sentence>" + sentence + "</sentence>\n\n";
        }

        endTime = System.nanoTime ();
        System.err.println("[Sentence Splitter] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
    }*/

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSplitText() {
        return sText;
    }

    public void setSplitText(String sText) {
        this.sText = sText;
    }
}